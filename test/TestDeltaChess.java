import org.junit.Before;

/**
 * Created by marc on 07/12/13.
 */
public class TestDeltaChess {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new SimpleBoardSetupStrategy());
    }
}
