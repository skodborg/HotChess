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

    @Test
    public void shouldLetBlackPawnMove1StepForwardInitially() {
        // white moves initially
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);
        assertTrue("should let black pawn move 1 step forward after white has moved once",
                _game.movePiece(BoardPosition.A7, BoardPosition.A6));
    }

    @Test
    public void shouldNotLetWhitePawnMove2StepsForwardInOneMove() {
        // white moves initially
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);
        // black moves
        _game.movePiece(BoardPosition.A7, BoardPosition.A6);
        assertFalse("should not let white move distance two with a pawn in one move",
                _game.movePiece(BoardPosition.A3, BoardPosition.A5));
    }
}
