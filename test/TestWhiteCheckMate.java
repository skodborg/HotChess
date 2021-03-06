import Production.*;
import Production.Strategies.BoardSetup.FullBoardSetupStrategy;
import Production.Utility.BoardPosition;
import Production.Utility.Color;
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

        assertTrue(_game.isCheck() == Color.WHITE);
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

        assertTrue(_game.isCheck() == Color.WHITE); // equal
    }

    @Test
    public void shouldNotLetWhiteBeCheckMatedWhenAnotherPieceCanInterveneTheAttack() {
        _game.movePiece(BoardPosition.D2, BoardPosition.D4);
        _game.movePiece(BoardPosition.C7, BoardPosition.C5);
        _game.movePiece(BoardPosition.D4, BoardPosition.C5);
        _game.movePiece(BoardPosition.D8, BoardPosition.A5);

        assertFalse(_game.isPlayerInCheckMate() == Color.WHITE);
        assertTrue(_game.isCheck() == Color.WHITE); // equal
    }

    @Test
    public void shouldLetBlackBeCheckMatedWhenAttackedAndCannotAct() {
        _game.movePiece(BoardPosition.D2, BoardPosition.D3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E6);
        _game.movePiece(BoardPosition.D1, BoardPosition.D2);
        _game.movePiece(BoardPosition.E8, BoardPosition.E7);
        _game.movePiece(BoardPosition.G2, BoardPosition.G3);
        _game.movePiece(BoardPosition.E7, BoardPosition.D6);
        _game.movePiece(BoardPosition.F1, BoardPosition.H3);
        _game.movePiece(BoardPosition.D6, BoardPosition.D5);
        _game.movePiece(BoardPosition.H3, BoardPosition.E6);
        _game.movePiece(BoardPosition.D5, BoardPosition.D4);
        _game.movePiece(BoardPosition.E6, BoardPosition.B3);
        _game.movePiece(BoardPosition.D4, BoardPosition.E5);
        _game.movePiece(BoardPosition.D2, BoardPosition.B4);
        _game.movePiece(BoardPosition.E5, BoardPosition.F5);
        _game.movePiece(BoardPosition.B4, BoardPosition.B6);
        _game.movePiece(BoardPosition.F5, BoardPosition.E5);
        _game.movePiece(BoardPosition.B3, BoardPosition.A4);
        _game.movePiece(BoardPosition.E5, BoardPosition.D5);
        _game.movePiece(BoardPosition.D3, BoardPosition.D4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.A4, BoardPosition.B3);
        _game.movePiece(BoardPosition.D5, BoardPosition.E4);
        _game.movePiece(BoardPosition.G1, BoardPosition.H3);
        _game.movePiece(BoardPosition.E4, BoardPosition.F5);
        _game.movePiece(BoardPosition.B3, BoardPosition.A4);
        _game.movePiece(BoardPosition.F5, BoardPosition.E4);
        _game.movePiece(BoardPosition.B6, BoardPosition.A6);
        _game.movePiece(BoardPosition.E4, BoardPosition.D5);
        _game.movePiece(BoardPosition.A6, BoardPosition.B6);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.F2, BoardPosition.F3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.A4, BoardPosition.B3);

        assertTrue(_game.isPlayerInCheckMate().equals(Color.BLACK));

    }

    @Test
    public void shouldNotLetKingMoveIntoBeingCheckedWhenAlreadyChecked() {
        _game.movePiece(BoardPosition.H2, BoardPosition.H4);
        _game.movePiece(BoardPosition.B8, BoardPosition.A6);
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);
        _game.movePiece(BoardPosition.H7, BoardPosition.H6);
        _game.movePiece(BoardPosition.A1, BoardPosition.A2);
        _game.movePiece(BoardPosition.D7, BoardPosition.D6);
        _game.movePiece(BoardPosition.A2, BoardPosition.A1);
        _game.movePiece(BoardPosition.A8, BoardPosition.B8);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.B7, BoardPosition.B6);
        _game.movePiece(BoardPosition.D2, BoardPosition.D3);
        _game.movePiece(BoardPosition.H6, BoardPosition.H5);
        _game.movePiece(BoardPosition.A1, BoardPosition.A2);
        _game.movePiece(BoardPosition.B8, BoardPosition.A8);
        _game.movePiece(BoardPosition.D3, BoardPosition.D4);
        _game.movePiece(BoardPosition.E8, BoardPosition.D7);
        _game.movePiece(BoardPosition.D4, BoardPosition.D5);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.G2, BoardPosition.G4);
        _game.movePiece(BoardPosition.H5, BoardPosition.G4);
        _game.movePiece(BoardPosition.A3, BoardPosition.A4);
        _game.movePiece(BoardPosition.G4, BoardPosition.G3);
        _game.movePiece(BoardPosition.F1, BoardPosition.G2);
        _game.movePiece(BoardPosition.E7, BoardPosition.E6);
        _game.movePiece(BoardPosition.C1, BoardPosition.E3);
        _game.movePiece(BoardPosition.G3, BoardPosition.F2);
        _game.movePiece(BoardPosition.E1, BoardPosition.F1);
        _game.movePiece(BoardPosition.C8, BoardPosition.B7);
        _game.movePiece(BoardPosition.G2, BoardPosition.H3);
        _game.movePiece(BoardPosition.F8, BoardPosition.E7);
        _game.movePiece(BoardPosition.A2, BoardPosition.A1);
        _game.movePiece(BoardPosition.D7, BoardPosition.E8);
        _game.movePiece(BoardPosition.H1, BoardPosition.H2);
        _game.movePiece(BoardPosition.E8, BoardPosition.F8);
        _game.movePiece(BoardPosition.E3, BoardPosition.B6);
        _game.movePiece(BoardPosition.A7, BoardPosition.B6);
        _game.movePiece(BoardPosition.A1, BoardPosition.A3);
        _game.movePiece(BoardPosition.D8, BoardPosition.C8);
        _game.movePiece(BoardPosition.H4, BoardPosition.H5);
        _game.movePiece(BoardPosition.F8, BoardPosition.E8);
        _game.movePiece(BoardPosition.H2, BoardPosition.F2);
        _game.movePiece(BoardPosition.G7, BoardPosition.G6);
        _game.movePiece(BoardPosition.F3, BoardPosition.E1);
        _game.movePiece(BoardPosition.E7, BoardPosition.D8);
        _game.movePiece(BoardPosition.H5, BoardPosition.G6);
        _game.movePiece(BoardPosition.B7, BoardPosition.C6);
        _game.movePiece(BoardPosition.B2, BoardPosition.B4);
        _game.movePiece(BoardPosition.F7, BoardPosition.G6);
        _game.movePiece(BoardPosition.B1, BoardPosition.C3);
        _game.movePiece(BoardPosition.C6, BoardPosition.D7);
        _game.movePiece(BoardPosition.F2, BoardPosition.F3);
        _game.movePiece(BoardPosition.D7, BoardPosition.B5);
        _game.movePiece(BoardPosition.A3, BoardPosition.A2);
        _game.movePiece(BoardPosition.B5, BoardPosition.E2);
        _game.movePiece(BoardPosition.F1, BoardPosition.G2);
        _game.movePiece(BoardPosition.H8, BoardPosition.H7);
        _game.movePiece(BoardPosition.B4, BoardPosition.B5);
        _game.movePiece(BoardPosition.C7, BoardPosition.C6);
        _game.movePiece(BoardPosition.G2, BoardPosition.F2);
        _game.movePiece(BoardPosition.G6, BoardPosition.G5);
        _game.movePiece(BoardPosition.A2, BoardPosition.B2);
        _game.movePiece(BoardPosition.F6, BoardPosition.E4);
        _game.movePiece(BoardPosition.F2, BoardPosition.E3);
        _game.movePiece(BoardPosition.E6, BoardPosition.D5);
        _game.movePiece(BoardPosition.H3, BoardPosition.C8);
        _game.movePiece(BoardPosition.E4, BoardPosition.C3);
        _game.movePiece(BoardPosition.B5, BoardPosition.C6);
        _game.movePiece(BoardPosition.D8, BoardPosition.C7);
        _game.movePiece(BoardPosition.D1, BoardPosition.A1);
        _game.movePiece(BoardPosition.H7, BoardPosition.H5);
        _game.movePiece(BoardPosition.E1, BoardPosition.D3);
        _game.movePiece(BoardPosition.G5, BoardPosition.G4);
        _game.movePiece(BoardPosition.A1, BoardPosition.A3);
        _game.movePiece(BoardPosition.B6, BoardPosition.B5);
        _game.movePiece(BoardPosition.D3, BoardPosition.C5);
        _game.movePiece(BoardPosition.D6, BoardPosition.C5);
        _game.movePiece(BoardPosition.B2, BoardPosition.B3);
        _game.movePiece(BoardPosition.A8, BoardPosition.C8);
        _game.movePiece(BoardPosition.A4, BoardPosition.A5);
        _game.movePiece(BoardPosition.A6, BoardPosition.B4);
        _game.movePiece(BoardPosition.A3, BoardPosition.C1);
        _game.movePiece(BoardPosition.B4, BoardPosition.D3);
        _game.movePiece(BoardPosition.B3, BoardPosition.B5);
        _game.movePiece(BoardPosition.D3, BoardPosition.F2);
        _game.movePiece(BoardPosition.F3, BoardPosition.F8);
        _game.movePiece(BoardPosition.E8, BoardPosition.E7);
        _game.movePiece(BoardPosition.B5, BoardPosition.B3);
        _game.movePiece(BoardPosition.C7, BoardPosition.A5);
        _game.movePiece(BoardPosition.C1, BoardPosition.G1);
        _game.movePiece(BoardPosition.C3, BoardPosition.E4);
        _game.movePiece(BoardPosition.F8, BoardPosition.E8);
        _game.movePiece(BoardPosition.E7, BoardPosition.E6);
        _game.movePiece(BoardPosition.C2, BoardPosition.C3);

        // Both E6 and E5 are covered by a white Rook, should not let black King move from one to the other when checked
        assertFalse(_game.movePiece(BoardPosition.E6, BoardPosition.E5));
    }

}
