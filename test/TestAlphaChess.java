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

    @Test
    public void shouldNotFindAWinnerAfterRoundFour() {
        // White player moves his pawn from A2 to A3
        _game.movePiece(Position.A2, Position.A3);
        // Black player moves his pawn from A7 to A6
        _game.movePiece(Position.A7, Position.A6);
        // 1 round passed

        // White player moves his pawn from B2 to B3
        _game.movePiece(Position.B2, Position.B3);
        // Black player moves his pawn from B7 to B6
        _game.movePiece(Position.B7, Position.B6);
        // 2 rounds passed

        // White player moves his pawn from C2 to C3
        _game.movePiece(Position.C2, Position.C3);
        // Black player moves his pawn from C7 to C6
        _game.movePiece(Position.C7, Position.C6);
        // 3 rounds passed

        // White player moves his pawn from D2 to D3
        _game.movePiece(Position.D2, Position.D3);
        // Black player moves his pawn from D7 to D6
        _game.movePiece(Position.D7, Position.D6);
        // 4 rounds passed

        assertEquals("No winner should be found in 4 rounds",
                Color.NONE, _game.getWinner());
    }

    @Test
    public void shouldLetWhiteBeWinnerAfterRoundFive() {
        for (int i = 0; i < 2; i++) {
            // White player moves his pawn from A2 to A3
            _game.movePiece(Position.A2, Position.A3);
            // Black player moves his pawn from A7 to A6
            _game.movePiece(Position.A7, Position.A6);

            // White player moves his pawn from A3 to A2
            _game.movePiece(Position.A3, Position.A2);
            // Black player moves his pawn from A6 to A7
            _game.movePiece(Position.A6, Position.A7);
        }
        // 4 rounds has now passed

        // White player moves his pawn from A2 to A3
        _game.movePiece(Position.A2, Position.A3);
        // Black player moves his pawn from A7 to A6
        _game.movePiece(Position.A7, Position.A6);

        // 5 rounds has passed

        assertEquals("White should be the winner of the game now",
                Color.WHITE, _game.getWinner());
    }

    @Test
    public void shouldHaveWhitePawnAtPosA2() {
        assertEquals("should have piece of type PAWN at A2",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.A2).getType());
        assertEquals("should be a white piece at A2",
                Color.WHITE, _game.getPieceAtPosition(Position.A2).getColor());
    }
}
