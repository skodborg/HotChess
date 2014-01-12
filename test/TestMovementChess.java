import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMovementChess {

    Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new MovementTestingBoardSetupStrategy());
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

    @Test
    public void shouldLetWhiteRookMoveTwoPositionsVertically() {
        assertTrue("white rook should be allowed to move two positions vertically forward",
                _game.movePiece(BoardPosition.B2, BoardPosition.B4));
    }

    @Test
    public void shouldLetWhiteRookMoveThreePositionsVertically() {
        assertTrue("white rook should be allowed to move three positions vertically forward",
                _game.movePiece(BoardPosition.B2, BoardPosition.B5));
    }

    @Test
    public void shouldLetRookMoveTwoPositionsHorizontally() {
        assertTrue("white rook should be allowed to move two positions horizontally",
                _game.movePiece(BoardPosition.B2, BoardPosition.D2));
    }

    @Test
    public void shouldLetRookMoveThreePositionsHorizontally() {
        assertTrue("white rook should be allowed to move three positions horizontally",
                _game.movePiece(BoardPosition.B2, BoardPosition.E2));
    }

    @Test
    public void shouldLetBlackRookMoveTwoPositionsVertically() {
        // white makes an initial move
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);

        // black is now in turn
        assertTrue("black rook should be allowed to move two positions vertically forward",
                _game.movePiece(BoardPosition.B7, BoardPosition.B5));
    }

    @Test
    public void shouldLetBlackRookMoveTwoPositionsHorizontally() {
        // white makes an initial move
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);

        // black is now in turn
        assertTrue("black rook should be allowed to move two positions horizontally",
                _game.movePiece(BoardPosition.B7, BoardPosition.D7));
    }

    @Test
    public void shouldLetWhiteRookMoveBackwardsVertically() {
        assertTrue("should let white rook move backwards",
                _game.movePiece(BoardPosition.B2, BoardPosition.B1));
    }

    @Test
    public void shouldNotLetWhiteRookMoveDiagonally() {
        assertFalse("should not let white rook move diagonally",
                _game.movePiece(BoardPosition.B2, BoardPosition.C3));
    }

}
