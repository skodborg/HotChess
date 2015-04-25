import Production.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestWhiteCheckMate {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new FullBoardSetupStrategy());
    }

    @Test
    public void shouldOnlyLetWhiteBeCheckMatedWhenKingCannotFlee() {
        _game.movePiece(BoardPosition.G2, BoardPosition.G3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E6);
        _game.movePiece(BoardPosition.F2, BoardPosition.F3);
        _game.movePiece(BoardPosition.D8, BoardPosition.F6);
        _game.movePiece(BoardPosition.E1, BoardPosition.F2);
        _game.movePiece(BoardPosition.F6, BoardPosition.G6);
        _game.movePiece(BoardPosition.F2, BoardPosition.G2);
        _game.movePiece(BoardPosition.G6, BoardPosition.G5);
        _game.movePiece(BoardPosition.G2, BoardPosition.H3);
        _game.movePiece(BoardPosition.B8, BoardPosition.C6);
        _game.movePiece(BoardPosition.F1, BoardPosition.G2);
        _game.movePiece(BoardPosition.G5, BoardPosition.H5);

        assertTrue(_game.isPlayerInCheckMate() == Color.WHITE);
    }

    @Test
    public void shouldNotLetWhiteBeCheckMatedWhenKingCanFlee_JustChecked() {
        _game.movePiece(BoardPosition.G2, BoardPosition.G3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E5);
        _game.movePiece(BoardPosition.F2, BoardPosition.F3);
        _game.movePiece(BoardPosition.D8, BoardPosition.H4);
        _game.movePiece(BoardPosition.E2, BoardPosition.E3);
        _game.movePiece(BoardPosition.H4, BoardPosition.G3);

        assertFalse(_game.isPlayerInCheckMate() == Color.WHITE);

        assertTrue(_game.isCheck());
    }

    @Test
    public void shouldNotLetWhiteBeCheckMatedWhenThreateningPieceCanBeAttacked() {
        _game.movePiece(BoardPosition.B1, BoardPosition.A3);
        _game.movePiece(BoardPosition.B8, BoardPosition.C6);
        _game.movePiece(BoardPosition.A3, BoardPosition.B1);
        _game.movePiece(BoardPosition.C6, BoardPosition.D4);
        _game.movePiece(BoardPosition.B1, BoardPosition.A3);
        _game.movePiece(BoardPosition.D4, BoardPosition.F3);

        assertFalse(_game.isPlayerInCheckMate() == Color.WHITE);

        assertTrue(_game.isCheck());
    }

    @Test
    public void shouldNotLetWhiteBeCheckMatedWhenAnotherPieceCanInterveneTheAttack() {
        _game.movePiece(BoardPosition.D2, BoardPosition.D4);
        _game.movePiece(BoardPosition.C7, BoardPosition.C5);
        _game.movePiece(BoardPosition.D4, BoardPosition.C5);
        _game.movePiece(BoardPosition.D8, BoardPosition.A5);

        assertFalse(_game.isPlayerInCheckMate() == Color.WHITE);
        assertTrue(_game.isCheck());
    }


}
