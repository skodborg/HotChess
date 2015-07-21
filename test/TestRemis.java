import Production.Game;
import Production.GameImpl;
import Production.Strategies.BoardSetup.FullBoardSetupStrategy;
import Production.Utility.BoardPosition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRemis {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new FullBoardSetupStrategy());
    }

    @Test
    public void shouldNotBeRemisAtBeginningOfGame() {
        assertFalse(_game.isRemis());
    }

    @Test
    public void shouldBeRemisWhenStalemate() {
        _game.movePiece(BoardPosition.E2, BoardPosition.E3);
        _game.movePiece(BoardPosition.A7, BoardPosition.A5);
        _game.movePiece(BoardPosition.D1, BoardPosition.H5);
        _game.movePiece(BoardPosition.A8, BoardPosition.A6);
        _game.movePiece(BoardPosition.H5, BoardPosition.A5);
        _game.movePiece(BoardPosition.H7, BoardPosition.H5);
        _game.movePiece(BoardPosition.H2, BoardPosition.H4);
        _game.movePiece(BoardPosition.A6, BoardPosition.H6);
        _game.movePiece(BoardPosition.A5, BoardPosition.C7);
        _game.movePiece(BoardPosition.F7, BoardPosition.F6);
        _game.movePiece(BoardPosition.C7, BoardPosition.D7);
        _game.movePiece(BoardPosition.E8, BoardPosition.F7);
        _game.movePiece(BoardPosition.D7, BoardPosition.B7);
        _game.movePiece(BoardPosition.D8, BoardPosition.D3);
        _game.movePiece(BoardPosition.B7, BoardPosition.B8);
        _game.movePiece(BoardPosition.D3, BoardPosition.H7);
        _game.movePiece(BoardPosition.B8, BoardPosition.C8);
        _game.movePiece(BoardPosition.F7, BoardPosition.G6);
        _game.movePiece(BoardPosition.C8, BoardPosition.E6);

        assertTrue(_game.isRemis());
    }

    @Test
    public void shouldBeRemisWhenNoAttackOrPawnMoveHappensInFiftyMoves() {
        // moves white and black knight back and forth, 50 times each in total
        for (int i = 0; i < 25; i++) {
            _game.movePiece(BoardPosition.G1, BoardPosition.H3);
            _game.movePiece(BoardPosition.G8, BoardPosition.H6);
            _game.movePiece(BoardPosition.H3, BoardPosition.G1);
            _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        }
        assertTrue(_game.isRemis());
    }

    @Test
    public void shouldNotBeRemisAfterFiftyMovesIfPawnMovesAtSomePoint() {
        for (int i = 0; i < 24; i++) {
            _game.movePiece(BoardPosition.G1, BoardPosition.H3);
            _game.movePiece(BoardPosition.G8, BoardPosition.H6);
            _game.movePiece(BoardPosition.H3, BoardPosition.G1);
            _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        }
        // 48 moves at this point; white is in turn, moving a pawn
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);
        // performing some more moves, bringing the total to 52
        _game.movePiece(BoardPosition.B8, BoardPosition.A6);
        _game.movePiece(BoardPosition.G1, BoardPosition.H3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);

        assertFalse(_game.isRemis());
    }

    @Test
    public void shouldNotBeRemisAfterFiftyMovesIfSuccessfulAttackHappens() {
        // setting up for an attack by a white pawn but not completing it
        _game.movePiece(BoardPosition.B2, BoardPosition.B4);
        _game.movePiece(BoardPosition.A7, BoardPosition.A5);

        // performing another 46 moves, bringing the total to 48
        for (int i = 0; i < 23; i++) {
            _game.movePiece(BoardPosition.G1, BoardPosition.H3);
            _game.movePiece(BoardPosition.G8, BoardPosition.H6);
            _game.movePiece(BoardPosition.H3, BoardPosition.G1);
            _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        }

        // completing the attack; 49 attacks
        _game.movePiece(BoardPosition.B4, BoardPosition.A5);

        // doing a couple more, bringing the total to 53
        _game.movePiece(BoardPosition.B8, BoardPosition.C6);
        _game.movePiece(BoardPosition.B1, BoardPosition.C3);
        _game.movePiece(BoardPosition.C6, BoardPosition.B8);
        _game.movePiece(BoardPosition.C3, BoardPosition.B1);

        assertFalse(_game.isRemis());
    }
}
