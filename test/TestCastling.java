import Production.*;
import org.junit.Before;
import org.junit.Test;

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
        assertTrue(_game.movePiece(BoardPosition.E1, BoardPosition.G1));
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


}
