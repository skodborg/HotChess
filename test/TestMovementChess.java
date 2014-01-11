import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMovementChess {

    Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new FullBoardSetupStrategy());
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
        assertFalse("should not let white pawn move 2 steps forward in one move",
                _game.movePiece(BoardPosition.A2, BoardPosition.A4));
    }

    @Test
    public void shouldNotLetWhitePawnMoveBackwards() {
        // move white pawn forwards
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);

        // black is in turn, moving a black pawn
        _game.movePiece(BoardPosition.A7, BoardPosition.A6);

        // attempts to move white pawn backwards to original position
        assertFalse("should not let white pawn move backwards",
                _game.movePiece(BoardPosition.A3, BoardPosition.A2));
    }

    @Test
    public void shouldNotLetWhitePawnMoveDiagonally() {
        // attempts to move pawn diagonally forward
        assertFalse("should not let white pawn move diagonally",
                _game.movePiece(BoardPosition.A2, BoardPosition.B3));
    }
}
