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
    public void shouldNotLetWhitePawnMoveTwoFieldsForwards() {
        // white moves
        _game.movePiece(Position.A2, Position.A3);

        // black moves
        _game.movePiece(Position.A7, Position.A6);

        assertFalse("Should not let white pawn at A3 move to A5",
                _game.movePiece(Position.A3, Position.A5));
    }



}
