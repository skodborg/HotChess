package Production.AiBot;

import Production.Game;
import Production.Piece;
import Production.Utility.AlgorithmUtility;
import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Utility.GameConstants;

import java.util.*;

public class CleverAiBot {

    private double _weightAtt = 1.0;
    private double _weightDef = 1.1;
    private double _weightCoveredPos = 0.1;
    private double _weightCheckingEnemyKing = 0.5;

    private Color _botColor;
    private int _randomMoveAttemptCount;
    private int _moveAttemptLimit;
    private int _nrPreviousBoardStatesToTrack;
    private List<Map<BoardPosition, Piece>> _previousBoardStates;

    private Map<BoardPosition, Piece> myPieces;
    private Map<BoardPosition, Piece> enemyPieces;

    private boolean _useNewFeature;

    public CleverAiBot(Color playerColor, boolean useNewFeature) {
        _botColor = playerColor;
        _randomMoveAttemptCount = 1;
        _moveAttemptLimit = 200;
        _nrPreviousBoardStatesToTrack = 10;
        _previousBoardStates = new ArrayList<Map<BoardPosition, Piece>>();
        _useNewFeature = useNewFeature;
    }

    // called whenever the bot is to make a move in game
    public void act(Game argGame) throws Exception {

        Map<BoardPosition, Piece> pieceMap = argGame.getPieceMap();
        myPieces = new HashMap<BoardPosition, Piece>();
        enemyPieces = new HashMap<BoardPosition, Piece>();

        // fill above hashmaps
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            BoardPosition pos = entry.getKey();
            Piece piece = entry.getValue();
            if (piece.getColor().equals(_botColor)) {
                myPieces.put(pos, piece);
            }
            else {
                enemyPieces.put(pos, piece);
            }
        }

        // iterate and evaluate all possible moves; keep track of current best
        BoardPosition from = null;
        BoardPosition to = null;
        double currMaxMoveValue = 0.0;

        for (Map.Entry<BoardPosition, Piece> entry : myPieces.entrySet()) {
            BoardPosition pos = entry.getKey();
            Piece piece = entry.getValue();
            Iterator<BoardPosition> it = piece.possibleMovingPositions(pos, argGame, pieceMap);
            while (it.hasNext()) {
                BoardPosition next = it.next();
                if (argGame.isMoveValid(pos, next)) {
                    double currValue = evaluateMove(pos, next, argGame, pieceMap);
                    if (currValue > currMaxMoveValue) {
                        from = pos;
                        to = next;
                        currMaxMoveValue = currValue;
                    }
                }
            }
        }

        // perform move
        if (from != null && to != null) {
            if (!argGame.movePiece(from, to)) {
                randomMove(argGame, 0);
            } else {
                // record board state prior to moving
                _previousBoardStates.add(argGame.getPieceMap());
                // keep size at or below the number of states we want to track
                if (_previousBoardStates.size() > _nrPreviousBoardStatesToTrack) {
                    _previousBoardStates.remove(0);
                }
            }
        }
        else {
            randomMove(argGame, 0);
        }

    }

    private double evaluateMove(BoardPosition from, BoardPosition to, Game argGame, Map<BoardPosition, Piece> argMap) {

        double result = 0;

        // ------------------------------------------------------------------
        // evaluate the attack value of current move
        // ------------------------------------------------------------------
        Piece targetPiece = argMap.get(to);
        int targetPieceMaterialValue = getPieceMaterialValue(targetPiece);
        result += targetPieceMaterialValue * _weightAtt;



        Piece friendlyMovingPiece = argMap.get(from);
        int friendlyMovingPieceMaterialValue = getPieceMaterialValue(friendlyMovingPiece);

        // ------------------------------------------------------------------
        // check if opponent threatens current piece, compensate if so
        // ------------------------------------------------------------------
        Color enemyColor = (_botColor == Color.WHITE ? Color.BLACK : Color.WHITE);
        List<BoardPosition> enemyCoveredPositions = AlgorithmUtility.coveredPositions(argMap, argGame, enemyColor);

        // if moving away from an attack, reward
        if (enemyCoveredPositions.contains(from)) {
            result += friendlyMovingPieceMaterialValue * _weightDef;
        }

        // ------------------------------------------------------------------
        // simulate move to evaluate the board following the move
        // ------------------------------------------------------------------
        Map<BoardPosition, Piece> argMap_postMove = new HashMap<BoardPosition, Piece>(argMap);
        Piece movingPiece = argMap_postMove.get(from);
        if (movingPiece != null) {
            argMap_postMove.put(to, movingPiece);
            argMap_postMove.remove(from);
        }

        // bail out with very low value if new board state has happened within last five moves
        if (_previousBoardStates.contains(argMap_postMove)) {
            return -500;
        }

        List<BoardPosition> newEnemyCoveredPositions = AlgorithmUtility.coveredPositions(argMap_postMove, argGame, enemyColor);

        // if enemy covers this new position, we exposed ourselves; punish
        if (newEnemyCoveredPositions.contains(to)) {
            result -= friendlyMovingPieceMaterialValue * _weightDef;
        }

        // ------------------------------------------------------------------
        // assign small bonus to playing in the center of the board
        // ------------------------------------------------------------------
        if (to.equals(BoardPosition.D4) || to.equals(BoardPosition.D5) ||
                to.equals(BoardPosition.E4) || to.equals(BoardPosition.E5)) {
            result += 2 * _weightCoveredPos;
        }
        else if (to.equals(BoardPosition.C3) || to.equals(BoardPosition.C4) ||
                to.equals(BoardPosition.C5) || to.equals(BoardPosition.C6) ||
                to.equals(BoardPosition.D3) || to.equals(BoardPosition.D6) ||
                to.equals(BoardPosition.E3) || to.equals(BoardPosition.E6) ||
                to.equals(BoardPosition.F3) || to.equals(BoardPosition.F4) ||
                to.equals(BoardPosition.F5) || to.equals(BoardPosition.F6)) {
            result += 1 * _weightCoveredPos;
        }

        if (_useNewFeature) {

            // ------------------------------------------------------------------
            // assign small bonus to checking the opposing king
            // ------------------------------------------------------------------
            List<BoardPosition> newCoveredPositions = AlgorithmUtility.coveredPositions(argMap_postMove, argGame, _botColor);
            BoardPosition enemyKingPos = null;
            for (Map.Entry<BoardPosition, Piece> entry : argMap.entrySet()) {
                Piece p = entry.getValue();
                BoardPosition bp = entry.getKey();
                if (p.getColor().equals(enemyColor) && p.getType().equals(GameConstants.KING)) {
                    enemyKingPos = bp;
                }
            }
            if (newCoveredPositions.contains(enemyKingPos)) {
                result += (1 * _weightCheckingEnemyKing);
            }

        }

        return result;
    }

    private int getPieceMaterialValue(Piece p) {
        if (p == null) {
            return 0;
        } else if (p.getType().equals(GameConstants.PAWN)) {
            return 1;
        } else if (p.getType().equals(GameConstants.KNIGHT)) {
            return 3;
        } else if (p.getType().equals(GameConstants.BISHOP)) {
            return 3;
        } else if (p.getType().equals(GameConstants.ROOK)) {
            return 5;
        } else if (p.getType().equals(GameConstants.QUEEN)) {
            return 9;
        } else if (p.getType().equals(GameConstants.KING)) {
            return 100;
        } else {
            return 0;
        }
    }

    private void randomMove(Game argGame, int cntAttempt) throws Exception {

        if (cntAttempt > _moveAttemptLimit) {
            throw new Exception("Attempted to move randomly "+ _randomMoveAttemptCount +" times without success; terminating");
        }

        Map<BoardPosition, List<BoardPosition>> possibleMoves = new HashMap<BoardPosition, List<BoardPosition>>();
        Map<BoardPosition, Piece> pieceMap = argGame.getPieceMap();

        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPiecePos = entry.getKey();
            if (currPiece.getColor().equals(_botColor)) {
                Iterator<BoardPosition> currPiecePossibleMoves = currPiece.possibleMovingPositions(currPiecePos, argGame, pieceMap);
                if (currPiecePossibleMoves.hasNext()) {
                    List<BoardPosition> tmpList = new ArrayList<BoardPosition>();
                    while (currPiecePossibleMoves.hasNext()) {
                        tmpList.add(currPiecePossibleMoves.next());
                    }
                    possibleMoves.put(currPiecePos, tmpList);
                }
            }
        }

        // select random possible move for random piece
        Random rnd = new Random();
        List<BoardPosition> possibleFromPositionsList = new ArrayList<BoardPosition>(possibleMoves.keySet());
        int rnd_idx = rnd.nextInt(possibleFromPositionsList.size());
        BoardPosition randomFromPosition = possibleFromPositionsList.get(rnd_idx);

        List<BoardPosition> possibleToPositionsList = possibleMoves.get(randomFromPosition);
        int rnd_idxx = rnd.nextInt(possibleToPositionsList.size());
        BoardPosition randomToPosition = possibleToPositionsList.get(rnd_idxx);

        if (!argGame.movePiece(randomFromPosition, randomToPosition)) {
            cntAttempt = cntAttempt+1;
            randomMove(argGame, cntAttempt);
        }
    }

}

