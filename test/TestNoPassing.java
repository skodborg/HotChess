import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestNoPassing {
    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new NoPassBoardSetupStrategy());
    }

    @Test
    public void shouldLetRookMoveVertically() {
        assertTrue(_game.movePiece(BoardPosition.D4, BoardPosition.D2));
    }

    @Test
    public void shouldLetRookMoveHorizontally() {
        assertTrue(_game.movePiece(BoardPosition.D4, BoardPosition.F4));
    }

    @Test
    public void shouldNotLetRookMoveThroughOtherFriendlyPiecesVertically() {
        assertFalse("should not let white rook move past friendly piece vertically",
                _game.movePiece(BoardPosition.D4, BoardPosition.D7));
    }

    @Test
    public void shouldNotLetRookMoveThroughOtherFriendlyPiecesHorizontally() {
        assertFalse("should not let white rook move past friendly piece horizontally",
                _game.movePiece(BoardPosition.D4, BoardPosition.A4));
    }
}
