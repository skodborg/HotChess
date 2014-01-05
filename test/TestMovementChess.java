import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMovementChess {

    Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new FullBoardSetupStrategy(), new NoAttackingMovingStrategy());
    }

    @Test
    public void shouldLetWhitePawnMove1StepForwardInitially() {
        assertTrue("should let white pawn move 1 step forward initially",
                _game.movePiece(BoardPosition.A2, BoardPosition.A3));
    }
}
