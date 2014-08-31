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

    @Override
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

        return false;
    }

    public BoardPosition getPositionOfPiece(Piece piece) {
        for (BoardPosition bp : BoardPosition.values()) {
            if (getPieceAtPosition(bp) == piece) { return bp; }
        }
        return null;
    }

    // returns true if a player is in check
    public boolean isCheck() {
        return AlgorithmUtility.isCheck(this, _pieceMap);
    }

    @Override
    public boolean isWhiteInMate() {

        return AlgorithmUtility.isWhiteCheckMated(this, _pieceMap);
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
