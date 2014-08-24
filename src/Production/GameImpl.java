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
        Piece movingPiece = getPieceAtPosition(from);

        // bail out
        if (movingPiece == null) return false;

        Iterator<BoardPosition> it = movingPiece.possibleMovingPositions(from, this);
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

    // TODO: Does not work
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
        Iterator<BoardPosition> it = whiteKingPiece.possibleMovingPositions(whiteKingPos, this);
        while (it.hasNext()) {
            whiteKing_PossibleMoves.add(it.next());
        }

        // temporarily remove the white king
        _pieceMap.remove(whiteKingPos);


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
                it = currPiece.possibleMovingPositions(currPos, this);
                while (it.hasNext()) {
                    BoardPosition itPos = it.next();
                    black_CoveredPositions.add(itPos);
                    tempPosList.add(itPos);
                }
                blackPieceMappings.put(currPiece, tempPosList);
            } else {
                ArrayList<BoardPosition> tempPosList = new ArrayList<BoardPosition>();
                it = currPiece.possibleMovingPositions(currPos, this);
                while (it.hasNext()) {
                    BoardPosition itPos = it.next();
                    white_CoveredPositions.add(itPos);
                    tempPosList.add(itPos);
                }
                whitePieceMappings.put(currPiece, tempPosList);
            }
        }

        // restore white king
        _pieceMap.put(whiteKingPos, whiteKingPiece);

        TreeSet<BoardPosition> temp_whiteKing_possibleMoves = new TreeSet<BoardPosition>();
        temp_whiteKing_possibleMoves.addAll(whiteKing_PossibleMoves);

        // find the white king moves not covered by black, if any
        temp_whiteKing_possibleMoves.removeAll(black_CoveredPositions);

        // if white king has possible moves, return false with no further analysis
        if (!temp_whiteKing_possibleMoves.isEmpty()) { return false; }


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
            ArrayList<BoardPosition> temp_blkThreatPos = new ArrayList<BoardPosition>();
            temp_blkThreatPos.addAll(black_threateningPiecePositions);
            temp_blkThreatPos.retainAll(entry.getValue());

            if (!temp_blkThreatPos.isEmpty()) {
                // denne brik dækker en eller flere af de sorte truende brikker
                // find denne briks BoardPosition
                for (BoardPosition blackBP : temp_blkThreatPos) {
                    for (BoardPosition bp : BoardPosition.values()) {
                        if (getPieceAtPosition(bp) == entry.getKey()
                                && entry.getValue().contains(blackBP)) {
                            // simulate move and check if still in check; if not, return false as this move is valid
                            Map<BoardPosition, Piece> tempPieceMap = new HashMap<BoardPosition, Piece>();
                            tempPieceMap.putAll(_pieceMap);
                            if (!AlgorithmUtility.isCheck(this, tempPieceMap)) {
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


        return temp_whiteKing_possibleMoves.isEmpty();
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
