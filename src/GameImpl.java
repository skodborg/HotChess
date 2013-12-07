/**
 * Created by marc on 07/12/13.
 */
public class GameImpl implements Game {

    private static final int TURNS_PLAYED_IN_ROUND_5 = 10;

    private Color _playerInTurn;
    private int _turnsPlayed;

    public GameImpl() {
        _playerInTurn = Color.WHITE;
        _turnsPlayed = 0;
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
        return new PieceImpl(GameConstants.PAWN, Color.WHITE);
    }

    @Override
    public boolean movePiece(Position from, Position to) {
        if (_playerInTurn == Color.BLACK) {
            _playerInTurn = Color.WHITE;
        } else {
            _playerInTurn = Color.BLACK;
        }
        _turnsPlayed++;
        return false;
    }
}
