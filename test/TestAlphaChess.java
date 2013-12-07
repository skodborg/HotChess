import org.junit.Before;
import org.junit.Test;
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
}
