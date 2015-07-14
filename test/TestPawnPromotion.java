import Production.BoardPosition;
import Production.FullBoardSetupStrategy;
import Production.Game;
import Production.GameImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPawnPromotion {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new FullBoardSetupStrategy());
    }

    @Test
    public void shouldLetPawnMoveToOppositeBackRow() {
        _game.movePiece(BoardPosition.H2, BoardPosition.H4);
        _game.movePiece(BoardPosition.G7, BoardPosition.G5);
        _game.movePiece(BoardPosition.H4, BoardPosition.G5);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.G5, BoardPosition.G6);
        _game.movePiece(BoardPosition.F6, BoardPosition.D5);
        _game.movePiece(BoardPosition.G6, BoardPosition.G7);
        _game.movePiece(BoardPosition.D5, BoardPosition.F6);
        assertTrue(_game.movePiece(BoardPosition.G7, BoardPosition.G8));
    }
}
