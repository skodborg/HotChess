import Production.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCastling {
    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new FullBoardSetupStrategy());
    }

    @Test
    public void shouldLetWhiteCastleShort() {
        // initial setup movement
        _game.movePiece(BoardPosition.E2, BoardPosition.E4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.F1, BoardPosition.C4);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);

        // castling move by moving king
        boolean result = _game.movePiece(BoardPosition.E1, BoardPosition.G1);
        assertTrue(result);
    }

    @Test
    public void shouldMoveRookWhenWhiteCastlesShort() {
        // initial setup movement
        _game.movePiece(BoardPosition.E2, BoardPosition.E4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.F1, BoardPosition.C4);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);

        // castling move by moving king
        _game.movePiece(BoardPosition.E1, BoardPosition.G1);

        Piece rook = _game.getPieceAtPosition(BoardPosition.F1);
        assertTrue(rook.getType().equals(GameConstants.ROOK));
    }

    @Test
    public void shouldLetWhiteCastleLong() {
        // initial setup movement
        _game.movePiece(BoardPosition.D2, BoardPosition.D4);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.D1, BoardPosition.D3);
        _game.movePiece(BoardPosition.F6, BoardPosition.G8);
        _game.movePiece(BoardPosition.C1, BoardPosition.F4);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.B1, BoardPosition.C3);
        _game.movePiece(BoardPosition.F6, BoardPosition.G8);

        // castling move by moving king
        assertTrue(_game.movePiece(BoardPosition.E1, BoardPosition.C1));
    }

    @Test
    public void shouldMoveRookWhenWhiteCastlesLong() {
        // initial setup movement
        _game.movePiece(BoardPosition.D2, BoardPosition.D4);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.D1, BoardPosition.D3);
        _game.movePiece(BoardPosition.F6, BoardPosition.G8);
        _game.movePiece(BoardPosition.C1, BoardPosition.F4);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.B1, BoardPosition.C3);
        _game.movePiece(BoardPosition.F6, BoardPosition.G8);

        // castling move by moving king
        _game.movePiece(BoardPosition.E1, BoardPosition.C1);

        Piece rook = _game.getPieceAtPosition(BoardPosition.D1);
        assertTrue(rook.getType().equals(GameConstants.ROOK));
    }

    @Test
    public void shouldLetBlackCastleShort() {
        // initial setup movement
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E5);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.F8, BoardPosition.C5);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);

        // castling move by moving king
        assertTrue(_game.movePiece(BoardPosition.E8, BoardPosition.G8));
    }

    @Test
    public void shouldMoveRookWhenBlackCastlesShort() {
        // initial setup movement
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E5);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.F8, BoardPosition.C5);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);

        // castling move by moving king
        _game.movePiece(BoardPosition.E8, BoardPosition.G8);

        Piece rook = _game.getPieceAtPosition(BoardPosition.F8);
        assertTrue(rook.getType().equals(GameConstants.ROOK));
    }

    @Test
    public void shouldLetBlackCastleLong() {
        // initial setup movement
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.D7, BoardPosition.D5);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.C8, BoardPosition.F5);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.D8, BoardPosition.D6);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.B8, BoardPosition.C6);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);

        // castling move by moving king
        assertTrue(_game.movePiece(BoardPosition.E8, BoardPosition.C8));
    }

    @Test
    public void shouldMoveRookWhenBlackCastlesLong() {
        // initial setup movement
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.D7, BoardPosition.D5);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.C8, BoardPosition.F5);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.D8, BoardPosition.D6);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.B8, BoardPosition.C6);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);

        // castling move by moving king
        _game.movePiece(BoardPosition.E8, BoardPosition.C8);

        Piece rook = _game.getPieceAtPosition(BoardPosition.D8);
        assertTrue(rook.getType().equals(GameConstants.ROOK));
    }

    @Test
    public void shouldNotAllowCastlingWhenWhiteKingHasMoved() {
        _game.movePiece(BoardPosition.E2, BoardPosition.E4);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.E1, BoardPosition.E2);
        _game.movePiece(BoardPosition.F6, BoardPosition.H5);
        _game.movePiece(BoardPosition.E2, BoardPosition.E3);
        _game.movePiece(BoardPosition.H5, BoardPosition.F6);

        assertFalse(_game.movePiece(BoardPosition.E3, BoardPosition.G3));
    }

    @Test
    public void shouldNotAllowCastlingWhenBlackKingHasMoved() {
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E5);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.E8, BoardPosition.E7);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E6);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);

        assertFalse(_game.movePiece(BoardPosition.E6, BoardPosition.G6));
    }

    @Test
    public void shouldNotAllowCastlingWhenKingHasPreviouslyMoved() {
        _game.movePiece(BoardPosition.G1, BoardPosition.H3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.E2, BoardPosition.E4);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.F1, BoardPosition.C4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.E1, BoardPosition.E2);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.E2, BoardPosition.E1);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);

        assertFalse(_game.movePiece(BoardPosition.E1, BoardPosition.G1));
    }

    @Test
    public void shouldNotAllowCastlingWhenRookHasMoved() {
        _game.movePiece(BoardPosition.G1, BoardPosition.H3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.E2, BoardPosition.E4);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.F1, BoardPosition.C4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.H1, BoardPosition.F1);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);

        assertFalse(_game.movePiece(BoardPosition.E1, BoardPosition.G1));
    }

    @Test
    public void shouldNotAllowCastlingWhenRookHasPreviouslyMoved() {
        _game.movePiece(BoardPosition.G1, BoardPosition.H3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.E2, BoardPosition.E4);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.F1, BoardPosition.C4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.H1, BoardPosition.F1);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.F1, BoardPosition.H1);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);

        assertFalse(_game.movePiece(BoardPosition.E1, BoardPosition.G1));
    }

    @Test
    public void shouldNotAllowBlackCastlingShortWhenRookHasPreviouslyMoved() {
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.E7, BoardPosition.E5);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.F8, BoardPosition.C5);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.G8, BoardPosition.F6);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);
        _game.movePiece(BoardPosition.H8, BoardPosition.F8);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.F8, BoardPosition.H8);
        _game.movePiece(BoardPosition.F3, BoardPosition.G1);

        assertFalse(_game.movePiece(BoardPosition.E8, BoardPosition.G8));
    }

    @Test
    public void shouldNotAllowCastlingShortThroughOtherPieces() {
        _game.movePiece(BoardPosition.G1, BoardPosition.H3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);

        // white castling short through the bishop
        assertFalse(_game.movePiece(BoardPosition.E1, BoardPosition.G1));
    }

    @Test
    public void shouldNotAllowCastlingLongThroughOtherPieces() {
        _game.movePiece(BoardPosition.D2, BoardPosition.D4);
        _game.movePiece(BoardPosition.B8, BoardPosition.A6);
        _game.movePiece(BoardPosition.D1, BoardPosition.D3);
        _game.movePiece(BoardPosition.A6, BoardPosition.B8);
        _game.movePiece(BoardPosition.C1, BoardPosition.F4);
        _game.movePiece(BoardPosition.B8, BoardPosition.A6);

        // white castling long through the knight
        assertFalse(_game.movePiece(BoardPosition.E1, BoardPosition.C1));
    }

    @Test
    public void shouldNotAllowCastlingIfNotWithRook() {
        _game.movePiece(BoardPosition.G2, BoardPosition.G4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.F1, BoardPosition.G2);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.H2, BoardPosition.H4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.H1, BoardPosition.H3);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.G2, BoardPosition.H1);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.G1, BoardPosition.F3);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);

        // white attempting short castling with bishop on rooks position
        assertFalse(_game.movePiece(BoardPosition.E1, BoardPosition.G1));
    }

    @Test
    public void shouldNotAllowCastlingIfNotWithKing() {
        _game.movePiece(BoardPosition.E2, BoardPosition.E4);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.F1, BoardPosition.C4);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.G1, BoardPosition.H3);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.E1, BoardPosition.E2);
        _game.movePiece(BoardPosition.H6, BoardPosition.G8);
        _game.movePiece(BoardPosition.D1, BoardPosition.E1);
        _game.movePiece(BoardPosition.G8, BoardPosition.H6);
        _game.movePiece(BoardPosition.E1, BoardPosition.G1);

        // rook should not have moved as we castled with the queen, not the king
        Piece rook = _game.getPieceAtPosition(BoardPosition.H1);
        assertTrue(rook != null);
    }

}
