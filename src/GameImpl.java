/**
 * Created by marc on 07/12/13.
 */
public class GameImpl implements Game {
    private Color _playerInTurn;

    public GameImpl() {
        _playerInTurn = Color.WHITE;
    }

    @Override
    public Color getWinner() {
        return Color.NONE;
    }

    @Override
    public Color getPlayerInTurn() {
        return _playerInTurn;
    }

    @Override
    public Piece getPieceAtPosition(Position p) {
        return null;
    }

    @Override
    public boolean movePiece(Position from, Position to) {
        if (_playerInTurn == Color.BLACK) {
            _playerInTurn = Color.WHITE;
        } else {
            _playerInTurn = Color.BLACK;
        }
        return false;
    }
}
