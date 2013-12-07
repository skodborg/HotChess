import java.util.HashMap;
import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
public class GameImpl implements Game {

    private static final int TURNS_PLAYED_IN_ROUND_5 = 10;

    private Color _playerInTurn;
    private int _turnsPlayed;
    private Map<Position, Piece> _pieceMap;
    private BoardSetupStrategy _boardSetupStrategy;

    public GameImpl(BoardSetupStrategy boardSetupStrategy) {
        _boardSetupStrategy = boardSetupStrategy;
        _playerInTurn = Color.WHITE;
        _turnsPlayed = 0;
        setupPieces();
    }

    private void setupPieces() {
        _pieceMap = new HashMap<Position, Piece>();

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
    public Piece getPieceAtPosition(Position p) {
        return _pieceMap.get(p);
    }

    @Override
    public boolean movePiece(Position from, Position to) {
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

    private boolean isMoveValid(Position from, Position to) {
        return isPlayerMovingOwnPiece(from)
                && isTargetFieldFree(to);
    }

    private boolean isTargetFieldFree(Position to) {
        return _pieceMap.get(to) == null;
    }

    private boolean isPlayerMovingOwnPiece(Position from) {
        return _playerInTurn == _pieceMap.get(from).getColor();
    }

    private void performPieceMove(Position from, Position to) {
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
}
