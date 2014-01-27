import Production.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by marc on 07/12/13.
 */
public class TestBoardPieceSetup {

    private Game _game;

    @Before
    public void setup() {
        _game = new GameImpl(new FullBoardSetupStrategy());
    }

    @Test
    public void shouldHaveBlackPawnAtPosA7() {
        assertEquals("should have piece of type PAWN at A7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.A7).getType());
        assertEquals("should be a black piece at A7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.A7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosB7() {
        assertEquals("should have piece of type PAWN at B7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.B7).getType());
        assertEquals("should be a black piece at B7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.B7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosC7() {
        assertEquals("should have piece of type PAWN at C7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.C7).getType());
        assertEquals("should be a black piece at C7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.C7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosD7() {
        assertEquals("should have piece of type PAWN at D7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.D7).getType());
        assertEquals("should be a black piece at D7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.D7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosE7() {
        assertEquals("should have piece of type PAWN at E7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.E7).getType());
        assertEquals("should be a black piece at E7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.E7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosF7() {
        assertEquals("should have piece of type PAWN at F7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.F7).getType());
        assertEquals("should be a black piece at F7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.F7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosG7() {
        assertEquals("should have piece of type PAWN at G7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.G7).getType());
        assertEquals("should be a black piece at G7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.G7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosH7() {
        assertEquals("should have piece of type PAWN at H7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.H7).getType());
        assertEquals("should be a black piece at H7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.H7).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosA2() {
        assertEquals("should have piece of type PAWN at A2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.A2).getType());
        assertEquals("should be a White piece at A2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.A2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosB2() {
        assertEquals("should have piece of type PAWN at B2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.B2).getType());
        assertEquals("should be a White piece at B2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.B2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosC2() {
        assertEquals("should have piece of type PAWN at C2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.C2).getType());
        assertEquals("should be a White piece at C2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.C2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosD2() {
        assertEquals("should have piece of type PAWN at D2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.D2).getType());
        assertEquals("should be a White piece at D2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.D2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosE2() {
        assertEquals("should have piece of type PAWN at E2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.E2).getType());
        assertEquals("should be a White piece at E2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.E2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosF2() {
        assertEquals("should have piece of type PAWN at F2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.F2).getType());
        assertEquals("should be a White piece at F2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.F2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosG2() {
        assertEquals("should have piece of type PAWN at G2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.G2).getType());
        assertEquals("should be a White piece at G2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.G2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosH2() {
        assertEquals("should have piece of type PAWN at H2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.H2).getType());
        assertEquals("should be a White piece at H2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.H2).getColor());
    }

    @Test
    public void shouldHaveWhiteRookAtPosA1() {
        assertEquals("should have piece of type ROOK at A1",
                GameConstants.ROOK, _game.getPieceAtPosition(BoardPosition.A1).getType());
        assertEquals("should be a White piece at A1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.A1).getColor());
    }

    @Test
    public void shouldHaveWhiteRookAtPosH1() {
        assertEquals("should have piece of type ROOK at H1",
                GameConstants.ROOK, _game.getPieceAtPosition(BoardPosition.H1).getType());
        assertEquals("should be a White piece at H1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.H1).getColor());
    }

    @Test
    public void shouldHaveBlackRookAtPosA8() {
        assertEquals("should have piece of type ROOK at A8",
                GameConstants.ROOK, _game.getPieceAtPosition(BoardPosition.A8).getType());
        assertEquals("should be a Black piece at A8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.A8).getColor());
    }

    @Test
    public void shouldHaveBlackRookAtPosH8() {
        assertEquals("should have piece of type ROOK at H8",
                GameConstants.ROOK, _game.getPieceAtPosition(BoardPosition.H8).getType());
        assertEquals("should be a Black piece at H8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.H8).getColor());
    }

    @Test
    public void shouldHaveWhiteKnightAtPosB1() {
        assertEquals("should have piece of type KNIGHT at B1",
                GameConstants.KNIGHT, _game.getPieceAtPosition(BoardPosition.B1).getType());
        assertEquals("should be a White piece at B1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.B1).getColor());
    }

    @Test
    public void shouldHaveWhiteKnightAtPosG1() {
        assertEquals("should have piece of type KNIGHT at G1",
                GameConstants.KNIGHT, _game.getPieceAtPosition(BoardPosition.G1).getType());
        assertEquals("should be a White piece at G1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.G1).getColor());
    }

    @Test
    public void shouldHaveBlackKnightAtPosB8() {
        assertEquals("should have piece of type KNIGHT at B8",
                GameConstants.KNIGHT, _game.getPieceAtPosition(BoardPosition.B8).getType());
        assertEquals("should be a Black piece at B8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.B8).getColor());
    }

    @Test
    public void shouldHaveBlackKnightAtPosG8() {
        assertEquals("should have piece of type KNIGHT at G8",
                GameConstants.KNIGHT, _game.getPieceAtPosition(BoardPosition.G8).getType());
        assertEquals("should be a Black piece at G8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.G8).getColor());
    }

    @Test
    public void shouldHaveWhiteBishopAtPosC1() {
        assertEquals("should have piece of type BISHOP at C1",
                GameConstants.BISHOP, _game.getPieceAtPosition(BoardPosition.C1).getType());
        assertEquals("should be a White piece at C1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.C1).getColor());
    }

    @Test
    public void shouldHaveWhiteBishopAtPosF1() {
        assertEquals("should have piece of type BISHOP at F1",
                GameConstants.BISHOP, _game.getPieceAtPosition(BoardPosition.F1).getType());
        assertEquals("should be a White piece at F1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.F1).getColor());
    }

    @Test
    public void shouldHaveBlackBishopAtPosC8() {
        assertEquals("should have piece of type BISHOP at C8",
                GameConstants.BISHOP, _game.getPieceAtPosition(BoardPosition.C8).getType());
        assertEquals("should be a Black piece at C8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.C8).getColor());
    }

    @Test
    public void shouldHaveBlackBishopAtPosF8() {
        assertEquals("should have piece of type BISHOP at F8",
                GameConstants.BISHOP, _game.getPieceAtPosition(BoardPosition.F8).getType());
        assertEquals("should be a Black piece at F8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.F8).getColor());
    }

    @Test
    public void shouldHaveWhiteQueenAtPosD1() {
        assertEquals("should have piece of type QUEEN at D1",
                GameConstants.QUEEN, _game.getPieceAtPosition(BoardPosition.D1).getType());
        assertEquals("should be a White piece at D1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.D1).getColor());
    }

    @Test
    public void shouldHaveWhiteKingAtPosE1() {
        assertEquals("should have piece of type KING at E1",
                GameConstants.KING, _game.getPieceAtPosition(BoardPosition.E1).getType());
        assertEquals("should be a White piece at E1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.E1).getColor());
    }

    @Test
    public void shouldHaveBlackQueenAtPosD8() {
        assertEquals("should have piece of type QUEEN at D8",
                GameConstants.QUEEN, _game.getPieceAtPosition(BoardPosition.D8).getType());
        assertEquals("should be a Black piece at D8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.D8).getColor());
    }

    @Test
    public void shouldHaveBlackKingAtPosE8() {
        assertEquals("should have piece of type KING at E8",
                GameConstants.KING, _game.getPieceAtPosition(BoardPosition.E8).getType());
        assertEquals("should be a Black piece at E8",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.E8).getColor());
    }


}