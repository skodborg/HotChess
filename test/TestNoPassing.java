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

    /*
    ----- Rook
     */

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

    @Test
    public void shouldLetRookMoveToBoardEnd() {
        assertTrue(_game.movePiece(BoardPosition.F2, BoardPosition.F7));
    }

    /*
    ----- Bishop
     */

    @Test
    public void shouldLetBishopMoveSouthEast() {
        // white is in turn
        _game.movePiece(BoardPosition.F2, BoardPosition.G2);

        // black is now in turn
        assertTrue(_game.movePiece(BoardPosition.E2, BoardPosition.F1));
    }

    @Test
    public void shouldLetBishopMoveNorthWest() {
        // white is in turn
        _game.movePiece(BoardPosition.F2, BoardPosition.G2);

        // black is now in turn
        assertTrue(_game.movePiece(BoardPosition.E2, BoardPosition.A6));
    }

    @Test
    public void shouldLetBishopMoveNextToFriendlyPiece() {
        // white is in turn
        _game.movePiece(BoardPosition.F2, BoardPosition.G2);

        // black is now in turn
        assertTrue(_game.movePiece(BoardPosition.E2, BoardPosition.F3));
    }

    @Test
    public void shouldNotLetBishopMoveOntoFriendlyPiece() {
        // white is in turn
        _game.movePiece(BoardPosition.F2, BoardPosition.G2);

        // black is now in turn
        assertFalse(_game.movePiece(BoardPosition.E2, BoardPosition.G4));
    }

    @Test
    public void shouldNotLetBishopMoveThroughFriendlyPiece() {
        // white is in turn
        _game.movePiece(BoardPosition.F2, BoardPosition.G2);

        // black is now in turn
        assertFalse(_game.movePiece(BoardPosition.E2, BoardPosition.H5));
    }
}
