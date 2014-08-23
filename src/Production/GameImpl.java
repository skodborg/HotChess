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

        BoardPosition whiteKingPos = null;

        // find the positions of the white king
        for (Entry<BoardPosition, Piece> entry : _pieceMap.entrySet()) {
            if (entry.getValue().getType().equals(GameConstants.KING)
                    && entry.getValue().getColor() == Color.WHITE) {
                whiteKingPos = entry.getKey();
            }
        }

        // for each black piece, save their possible moving positions
        TreeSet<BoardPosition> possibleBlackMovingPositions = new TreeSet<BoardPosition>();
        for (Entry<BoardPosition, Piece> entry : _pieceMap.entrySet()) {
            if (entry.getValue().getColor() == Color.BLACK) {
                Piece curr = entry.getValue();
                BoardPosition currPos = entry.getKey();
                Iterator<BoardPosition> currentIt = curr.possibleMovingPositions(currPos, this);
                while (currentIt.hasNext()) {
                    possibleBlackMovingPositions.add(currentIt.next());
                }
            }
        }

        // returns whether the position of the white king is covered by the set of possible black moving targets
        return possibleBlackMovingPositions.contains(whiteKingPos);
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


        TreeSet<BoardPosition> black_CoveredPositions = new TreeSet<BoardPosition>();

        // fill the above set of possible black moving positions
        for (Entry<BoardPosition, Piece> entry : _pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPos = entry.getKey();
            if (currPiece.getColor().equals(Color.BLACK)) {
                it = currPiece.possibleMovingPositions(currPos, this);
                while (it.hasNext()) {
                    black_CoveredPositions.add(it.next());
                }
            }
        }

        // restore white king
        _pieceMap.put(whiteKingPos, whiteKingPiece);


        // find the white king moves not covered by black, if any
        whiteKing_PossibleMoves.removeAll(black_CoveredPositions);

        for (BoardPosition bp : whiteKing_PossibleMoves) {
            System.out.print(bp);
        }
        System.out.println();

        // if white king has possible moves, return false with no further analysis
        if (!whiteKing_PossibleMoves.isEmpty()) { return false; }

        // check which pieces threatens the white king
        // check if any of our pieces can remove this(these) pieces

        // check, for all possible moving positions for all threatening black pieces,
        // if we are still checked (isCheck()) in another instance of the chess board if
        // one of our pieces could intervene (stand on one of these positions of the black piece)


        return whiteKing_PossibleMoves.isEmpty();
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
