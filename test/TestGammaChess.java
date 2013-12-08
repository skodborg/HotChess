import HotChess.Game;
import HotChess.GameImpl;
import HotChess.Position;
import HotChess.variants.factories.GammaChessGameFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGammaChess {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new GammaChessGameFactory());
    }

    @Test
    public void shouldLetWhitePawnMoveOneFieldForwards() {
        assertTrue("Should let white pawn at A2 move to A3",
                _game.movePiece(Position.A2, Position.A3));
    }


    @Test
    public void shouldLetBlackPawnMoveOneFieldForwards() {
        // white moves
        _game.movePiece(Position.A2, Position.A3);

        assertTrue("Should let black pawn at A7 move to A6",
                _game.movePiece(Position.A7, Position.A6));
    }

    @Test
    public void shouldNotLetWhitePawnMoveBackwards() {
        // white moves
        _game.movePiece(Position.A2, Position.A3);

        // black moves
        _game.movePiece(Position.A7, Position.A6);

        assertFalse("Should not let white pawn at A3 move to A2",
                _game.movePiece(Position.A3, Position.A2));
    }

    @Test
    public void shouldNotLetWhitePawnMoveTwoFieldsForwards() {
        // white moves
        _game.movePiece(Position.A2, Position.A3);

        // black moves
        _game.movePiece(Position.A7, Position.A6);

        assertFalse("Should not let white pawn at A3 move to A5",
                _game.movePiece(Position.A3, Position.A5));
    }

    @Test
    public void shouldNotLetWhitePawnMoveSideways() {
        // white moves
        _game.movePiece(Position.A2, Position.A3);

        // black moves
        _game.movePiece(Position.A7, Position.A6);

        assertFalse("should not let white pawn move sideways",
                _game.movePiece(Position.A3, Position.B3));
    }

    @Test
    public void shouldNotLetWhitePawnMoveDiagonally() {
        assertFalse("should not let white pawn move diagonally",
                _game.movePiece(Position.A2, Position.B3));
    }

    @Test
    public void shouldNotLetWhitePawnAttackBlackPawn() {
        // white moves
        _game.movePiece(Position.A2, Position.A3);

        // black moves
        _game.movePiece(Position.A7, Position.A6);

        // white moves
        _game.movePiece(Position.A3, Position.A4);

        // black moves
        _game.movePiece(Position.A6, Position.A5);

        assertFalse("should not let white pawn attack black pawn",
                _game.movePiece(Position.A4, Position.A5));
    }

    @Test
    public void shouldLetRooksMoveTwoFieldsForwardInOneMove() {
        // white pawn moves
        _game.movePiece(Position.A2, Position.A3);

        // black pawn moves
        _game.movePiece(Position.B7, Position.B6);

        // white pawn moves again
        _game.movePiece(Position.A3, Position.A4);

        // black pawn moves
        _game.movePiece(Position.B6, Position.B5);

        // white rook moves 2 fields forward
        assertTrue("rook should be able to move 2 fields forwards",
                _game.movePiece(Position.A1, Position.A3));
    }

    @Test
    public void shouldLetRooksMoveFourFieldsSidewaysInOneMove() {
        // white pawn moves
        _game.movePiece(Position.A2, Position.A3);

        //black pawn moves
        _game.movePiece(Position.B7, Position.B6);

        // white pawn moves
        _game.movePiece(Position.A3, Position.A4);

        // black pawn moves
        _game.movePiece(Position.B6, Position.B5);

        // white rook moves ahead 1 field
        _game.movePiece(Position.A1, Position.A3);

        // black pawn moves
        _game.movePiece(Position.B5, Position.B4);

        // white rook should be able to move 4 fields sideways
        _game.movePiece(Position.A3, Position.E3);
    }

}
