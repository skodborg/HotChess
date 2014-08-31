package Production;


import java.util.*;
import java.util.Map.*;


public class GameImpl implements Game, Observable{

    private static final int TURNS_PLAYED_IN_ROUND_5 = 10;

    private Color _playerInTurn;
    private int _turnsPlayed;
    private Map<BoardPosition, Piece> _pieceMap;
    private BoardSetupStrategy _boardSetupStrategy;
    private Set<Observer> _observers;

    public GameImpl(BoardSetupStrategy boardSetupStrategy) {
        _boardSetupStrategy = boardSetupStrategy;
        _playerInTurn = Color.WHITE;
        _turnsPlayed = 0;
        _observers = new HashSet<Observer>();
        setupPieces();
    }

    private void setupPieces() {
        _pieceMap = new HashMap<BoardPosition, Piece>();

        _boardSetupStrategy.setupPieces(_pieceMap);
    }

    @Override
    public Color getWinner() {
        if (_turnsPlayed >= TURNS_PLAYED_IN_ROUND_5) {
            return Color.WHITE;
        }
        return Color.NONE;
    }

    @Override
    public Color getPlayerInTurn() {
        return _playerInTurn;
    }

    @Override
    public Piece getPieceAtPosition(BoardPosition p) {
        return _pieceMap.get(p);
    }

    // returns a shallow copy of the _pieceMap
    @Override
    public Map<BoardPosition, Piece> getPieceMap() {
        return new HashMap<BoardPosition, Piece>(_pieceMap);
    }

    @Override
    public boolean movePiece(BoardPosition from, BoardPosition to) {
        if (isMoveValid(from, to)) {
            performPieceMove(from, to);
            swapPlayerTurn();
            _turnsPlayed++;

            // notify observers of changes
            notifyObservers();

            return true;
        }
        else {
            return false;
        }
    }

    public boolean isMoveValid(BoardPosition from, BoardPosition to) {
        Piece movingPiece = _pieceMap.get(from);

        // bail out
        if (movingPiece == null) return false;

        Iterator<BoardPosition> it = movingPiece.possibleMovingPositions(from, this, _pieceMap);
        boolean match = false;
        while (it.hasNext()) {
            BoardPosition currPos = it.next();
            if (currPos == to) {
                match = true;
            }
        }

        // if the player in turn is moving his own piece and
        // the target position is not already occupied, the move
        // is legal
        if (movingPiece.getColor() == getPlayerInTurn() &&
                match) {
            return true;
        }
        // check piece move rules
        return false;
    }



    // returns true if a player is in check
    public boolean isCheck() {
        return AlgorithmUtility.isCheck(this, _pieceMap);
    }

    // TODO: REFACTOR!!!
    @Override
    public boolean isWhiteInMate() {
        BoardPosition whiteKingPos = null;
        Piece whiteKingPiece = null;

        // find the positions of the white king
        for (Entry<BoardPosition, Piece> entry : _pieceMap.entrySet()) {
            BoardPosition currPos = entry.getKey();
            Piece currPiece = entry.getValue();
            if (currPiece.getType().equals(GameConstants.KING)
                    && currPiece.getColor() == Color.WHITE) {
                whiteKingPos = currPos;
                whiteKingPiece = currPiece;
            }
        }

        TreeSet<BoardPosition> whiteKing_PossibleMoves = new TreeSet<BoardPosition>();

        // fill the above set with white kings possible moving destinations
        Iterator<BoardPosition> it = whiteKingPiece.possibleMovingPositions(whiteKingPos, this, _pieceMap);
        while (it.hasNext()) {
            whiteKing_PossibleMoves.add(it.next());
        }

        // temporarily remove the white king
        //_pieceMap.remove(whiteKingPos);


        Set<BoardPosition> black_CoveredPositions = new TreeSet<BoardPosition>();
        Set<BoardPosition> white_CoveredPositions = new TreeSet<BoardPosition>();
        Map<Piece, List<BoardPosition>> blackPieceMappings = new HashMap<Piece, List<BoardPosition>>();
        Map<Piece, List<BoardPosition>> whitePieceMappings = new HashMap<Piece, List<BoardPosition>>();

        // fill the above set of possible black moving positions
        for (Entry<BoardPosition, Piece> entry : _pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPos = entry.getKey();
            if (currPiece.getColor().equals(Color.BLACK)) {
                ArrayList<BoardPosition> tempPosList = new ArrayList<BoardPosition>();
                it = currPiece.possibleMovingPositions(currPos, this, _pieceMap);
                while (it.hasNext()) {
                    BoardPosition itPos = it.next();
                    black_CoveredPositions.add(itPos);
                    tempPosList.add(itPos);
                }
                blackPieceMappings.put(currPiece, tempPosList);
            } else {
                ArrayList<BoardPosition> tempPosList = new ArrayList<BoardPosition>();
                it = currPiece.possibleMovingPositions(currPos, this, _pieceMap);
                while (it.hasNext()) {
                    BoardPosition itPos = it.next();
                    white_CoveredPositions.add(itPos);
                    tempPosList.add(itPos);
                }
                whitePieceMappings.put(currPiece, tempPosList);
            }
        }

        Set<BoardPosition> black_CoveredPositions_noWhiteKing = new TreeSet<BoardPosition>();
        // remove white king temporarily to check that it is not blocking its own fleeing positions
        _pieceMap.remove(whiteKingPos);
        for (Entry<BoardPosition, Piece> entry : _pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPos = entry.getKey();
            if (currPiece.getColor().equals(Color.BLACK)) {
                it = currPiece.possibleMovingPositions(currPos, this, _pieceMap);
                while (it.hasNext()) {
                    black_CoveredPositions_noWhiteKing.add(it.next());
                }
            }
        }
        // restore white king
        _pieceMap.put(whiteKingPos, whiteKingPiece);

        TreeSet<BoardPosition> temp_whiteKing_possibleMoves = new TreeSet<BoardPosition>();
        temp_whiteKing_possibleMoves.addAll(whiteKing_PossibleMoves);

        // find the white king moves not covered by black, if any
        temp_whiteKing_possibleMoves.removeAll(black_CoveredPositions_noWhiteKing);

        // if white king has possible moves, return false with no further analysis
        if (!temp_whiteKing_possibleMoves.isEmpty()) {
            System.out.print("king can flee to ");
            for (BoardPosition bp : temp_whiteKing_possibleMoves) {
                System.out.print(bp + " ");
            }
            System.out.println();
            return false;
        }


        // check which pieces threatens the white king

        Set<Piece> black_threateningPieces = new HashSet<Piece>();
        Set<BoardPosition> black_threateningPiecePositions = new HashSet<BoardPosition>();

        for (Entry<Piece, List<BoardPosition>> entry : blackPieceMappings.entrySet()) {
            if (entry.getValue().contains(whiteKingPos)) {
                black_threateningPieces.add(entry.getKey());
                for (Entry<BoardPosition, Piece> e : _pieceMap.entrySet()) {
                    if (e.getValue().equals(entry.getKey())) {
                        black_threateningPiecePositions.add(e.getKey());
                    }
                }
            }
        }

        // check that white is still in check if the threatening black piece is attacked;
        //      if not in check post-attack, return false as this is a possible move to survive; white is not mated
        for (Entry<Piece, List<BoardPosition>> entry : whitePieceMappings.entrySet()) {
            Piece currWhitePiece = entry.getKey();
            List<BoardPosition> currWhitePiecePossMoves = entry.getValue();

            ArrayList<BoardPosition> temp_blkThreatPos = new ArrayList<BoardPosition>(black_threateningPiecePositions);
            temp_blkThreatPos.retainAll(currWhitePiecePossMoves);

            if (!temp_blkThreatPos.isEmpty()) {
                // denne brik dækker en eller flere af de sorte truende brikker
                // find denne briks BoardPosition
                for (BoardPosition blackBP : temp_blkThreatPos) {
                    for (BoardPosition bp : BoardPosition.values()) {
                        if (_pieceMap.get(bp) == currWhitePiece
                                && currWhitePiecePossMoves.contains(blackBP)) {
                            // simulate move and check if still in check; if not, return false as this move is valid
                            Map<BoardPosition, Piece> tempPieceMap = new HashMap<BoardPosition, Piece>(_pieceMap);
                            tempPieceMap.put(blackBP, currWhitePiece);
                            tempPieceMap.remove(bp);
                            if (!AlgorithmUtility.isCheck(this, tempPieceMap)) {
                                System.out.println("Attack possible with " + _pieceMap.get(bp));
                                return false;
                            }

                        }
                    }
                }
            }
        }

        // check, for all possible moving positions for all threatening black pieces,
        // if we are still checked (isCheck()) in another instance of the chess board if
        // one of our pieces could intervene (stand on one of these positions of the black piece)

        for (Piece blkThreateningPiece : black_threateningPieces) {
            // find pos of black piece
            BoardPosition blkThreateningPiecePos = null;
            for (BoardPosition bp : BoardPosition.values()) {
                if (_pieceMap.get(bp) == blkThreateningPiece) {
                    blkThreateningPiecePos = bp;
                }
            }

            // collect possible moves of all threatening pieces in one set to check
            Set<BoardPosition> blkPossMovesToCheck = new TreeSet<BoardPosition>();
            it = blkThreateningPiece.possibleMovingPositions(blkThreateningPiecePos, this, _pieceMap);
            while (it.hasNext()) {
                blkPossMovesToCheck.add(it.next());
            }

            // run through all white pieces
            // - if current piece has a possible move that is contained in blkPossMovesToCheck
            //   - move this piece to this position and check if still in check. if not, return false

            for (Entry<Piece, List<BoardPosition>> entry : whitePieceMappings.entrySet()) {
                Piece currPiece = entry.getKey();
                List<BoardPosition> currPiecePossMoves = entry.getValue();
                BoardPosition currPiecePos = null;

                if (currPiece == whiteKingPiece) continue;

                // find white piece pos
                for (BoardPosition bp : BoardPosition.values()) {
                    if (_pieceMap.get(bp) == currPiece) {
                        currPiecePos = bp;
                    }
                }

                // save the intersection of the threatening black piece possible movements, and this current
                // white piece possible movements
                Set<BoardPosition> tmp_currWhiteRelevantMoves = new HashSet<BoardPosition>(currPiecePossMoves);
                tmp_currWhiteRelevantMoves.retainAll(blkPossMovesToCheck);

                for (BoardPosition possMove : tmp_currWhiteRelevantMoves) {
                    // create a temporary new instance of the game in which this possible move has happened
                    // and check if white is still mated; return false if not, as this is a possible move
                    Map<BoardPosition, Piece> tmp_pieceMap = new HashMap<BoardPosition, Piece>(_pieceMap);
                    tmp_pieceMap.put(possMove, currPiece);
                    tmp_pieceMap.remove(currPiecePos);
                    boolean result = AlgorithmUtility.isCheck(this, tmp_pieceMap);
                    if (!result) {
                        System.out.print("moved new piece " + currPiece + " from " + currPiecePos + " ");
                        System.out.print("to " + possMove + " ");
                        System.out.print("with result: " + result);
                        System.out.println();
                        return false;
                    }
                }
            }
        }

        // if this statement is reached, white is mated and game is over
        return true;
    }

    private void performPieceMove(BoardPosition from, BoardPosition to) {
        Piece pieceToMove = _pieceMap.get(from);
        _pieceMap.put(to, pieceToMove);
        _pieceMap.remove(from);
    }

    private void swapPlayerTurn() {
        if (_playerInTurn == Color.BLACK) {
            _playerInTurn = Color.WHITE;
        } else {
            _playerInTurn = Color.BLACK;
        }
    }

    @Override
    public void addObserver(Observer o) {
        _observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        _observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : _observers) {
            o.update();
        }
    }
}
