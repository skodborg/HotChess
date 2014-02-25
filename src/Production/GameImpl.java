package Production;


import java.util.*;

/**
 * Created by marc on 07/12/13.
 */
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

    private void performPieceMove(BoardPosition from, BoardPosition to) {
        // TODO: Handle attack
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
