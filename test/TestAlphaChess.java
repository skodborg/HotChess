import org.junit.Before;
import org.junit.Test;
import sun.jvm.hotspot.code.Location;

import java.util.WeakHashMap;

import static org.junit.Assert.*;

/**
 * Created by marc on 07/12/13.
 */
public class TestAlphaChess {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl();
    }

    @Test
    public void shouldLetWhitePlayerStartTheGame() {
        assertEquals("White player is in turn first in every game of chess",
                Color.WHITE, _game.getPlayerInTurn());

    }

    @Test
    public void shouldLetBlackPlayerBeInTurnWhenWhiteHasMoved() {
        _game.movePiece(Position.A2, Position.A3);
        assertEquals("Black player should be in turn when white has moved",
                Color.BLACK, _game.getPlayerInTurn());
    }

    @Test
    public void shouldLetWhitePlayerBeInTurnWhenBlackHasMoved() {
        // White player moves his pawn from A2 to A3
        _game.movePiece(Position.A2, Position.A3);
        // Black player moves his pawn from A7 to A6
        _game.movePiece(Position.A7, Position.A6);

        assertEquals("White player should be in turn when black has moved",
                Color.WHITE, _game.getPlayerInTurn());
    }

    @Test
    public void shouldNotFindAWinnerInRoundOne() {
        assertEquals("No winner should have been found in round one",
                Color.NONE, _game.getWinner());
    }
}
