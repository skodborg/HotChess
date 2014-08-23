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

            // TODO: finish
            // print out isCheck() result every move
            isCheck();

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
    public boolean isCheckMate() {
        TreeSet<BoardPosition> blackCoveredPositions = new TreeSet<BoardPosition>();
        TreeSet<BoardPosition> whiteCoveredPositions = new TreeSet<BoardPosition>();

        Piece blackKing = null;
        BoardPosition blackKingPos = null;
        Piece whiteKing = null;
        BoardPosition whiteKingPos = null;

        for (int i = 0; i < BoardPosition.indexToEnum.length; i++) {
            BoardPosition currPos = BoardPosition.indexToEnum[i];
            Piece currPiece = getPieceAtPosition(currPos);

            if (currPiece != null) {
                // we found a position with a piece
                Iterator<BoardPosition> it = currPiece.possibleMovingPositions(currPos, this);
                if (currPiece.getColor() == Color.BLACK) {
                    if (currPiece.getType().equals(GameConstants.KING)) {
                        blackKing = currPiece;
                        blackKingPos = currPos;
                    }
                    while (it.hasNext()) {
                        blackCoveredPositions.add(it.next());
                    }
                } else {
                    if (currPiece.getType().equals(GameConstants.KING)) {
                        whiteKing = currPiece;
                        whiteKingPos = currPos;
                    }
                    while (it.hasNext()) {
                        whiteCoveredPositions.add(it.next());
                    }
                }
            }
        }


        boolean isWhiteInCheckMate = true;
        boolean isBlackInCheckMate = true;

        if (blackKing != null && whiteKing != null) {
            // both kings still exist, check if one of them is check mated
            Iterator<BoardPosition> whiteIter = whiteKing.possibleMovingPositions(whiteKingPos, this);
            Iterator<BoardPosition> blackIter = blackKing.possibleMovingPositions(blackKingPos, this);

            while (whiteIter.hasNext() && isWhiteInCheckMate) {
                BoardPosition nextWhiteKingPos = whiteIter.next();
                if (!blackCoveredPositions.contains(nextWhiteKingPos)) {
                    // one possible spot exists, no check mate for white!
                    isWhiteInCheckMate = false;
                }
            }

            while (blackIter.hasNext() && isBlackInCheckMate) {
                BoardPosition nextBlackKingPos = blackIter.next();
                if (!whiteCoveredPositions.contains(nextBlackKingPos)) {
                    // one possible spot exists, no check mate for black!
                    isBlackInCheckMate = false;
                }
            }

            return isWhiteInCheckMate || isBlackInCheckMate;
        }

        return false;
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
