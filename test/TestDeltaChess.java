import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marc on 07/12/13.
 */
public class TestDeltaChess {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new SimpleBoardSetupStrategy(), new AttacksAllowedMovingStrategy());
    }

    @Test
    public void shouldLetWhitePawnAttackBlackPawn() {
        Piece whitePiece = _game.getPieceAtPosition(BoardPosition.A2);

        assertTrue("white pawn should be allowed to attack black pawn",
                _game.movePiece(BoardPosition.A2, BoardPosition.A7));
        assertEquals("white should now be at the position of the defeated black pawn",
                whitePiece, _game.getPieceAtPosition(BoardPosition.A7));
    }
}
