import Production.*;
import Production.Strategies.BoardSetup.SimpleBoardSetupStrategy;
import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Utility.GameConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAlphaChess {

    private Game _game;


    @Before
    public void setup() {
        _game = new GameImpl(new SimpleBoardSetupStrategy());
    }

    @Test
    public void shouldLetWhitePlayerStartTheGame() {
        assertEquals("White player is in turn first in every game of chess",
                Color.WHITE, _game.getPlayerInTurn());

    }

    @Test
    public void shouldLetBlackPlayerBeInTurnWhenWhiteHasMoved() {
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);
        assertEquals("Black player should be in turn when white has moved",
                Color.BLACK, _game.getPlayerInTurn());
    }

    @Test
    public void shouldLetWhitePlayerBeInTurnWhenBlackHasMoved() {
        // White player moves his pawn from A2 to A3
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);
        // Black player moves his pawn from A7 to A6
        _game.movePiece(BoardPosition.A7, BoardPosition.A6);

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
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);
        // Black player moves his pawn from A7 to A6
        _game.movePiece(BoardPosition.A7, BoardPosition.A6);
        // 1 round passed

        // White player moves his pawn from B2 to B3
        _game.movePiece(BoardPosition.B2, BoardPosition.B3);
        // Black player moves his pawn from B7 to B6
        _game.movePiece(BoardPosition.B7, BoardPosition.B6);
        // 2 rounds passed

        // White player moves his pawn from C2 to C3
        _game.movePiece(BoardPosition.C2, BoardPosition.C3);
        // Black player moves his pawn from C7 to C6
        _game.movePiece(BoardPosition.C7, BoardPosition.C6);
        // 3 rounds passed

        // White player moves his pawn from D2 to D3
        _game.movePiece(BoardPosition.D2, BoardPosition.D3);
        // Black player moves his pawn from D7 to D6
        _game.movePiece(BoardPosition.D7, BoardPosition.D6);
        // 4 rounds passed

        assertEquals("No winner should be found in 4 rounds",
                Color.NONE, _game.getWinner());
    }


    @Test
    public void shouldHaveWhitePawnAtPosA2() {
        assertEquals("should have piece of type PAWN at A2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.A2).getType());
        assertEquals("should be a white piece at A2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.A2).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosA7() {
        assertEquals("should have piece of type PAWN at A7",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.A7).getType());
        assertEquals("should be a black piece at A7",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.A7).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosB2() {
        assertEquals("should have piece of type PAWN at B2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.B2).getType());
        assertEquals("should be a white piece at B2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.B2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosC2() {
        assertEquals("should have piece of type PAWN at C2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.C2).getType());
        assertEquals("should be a white piece at C2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.C2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosD2() {
        assertEquals("should have piece of type PAWN at D2",
                GameConstants.PAWN, _game.getPieceAtPosition(BoardPosition.D2).getType());
        assertEquals("should be a white piece at D2",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.D2).getColor());
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
    public void shouldHaveWhiteQueenAtD1() {
        assertEquals("should have piece of type QUEEN at D1",
                GameConstants.QUEEN, _game.getPieceAtPosition(BoardPosition.D1).getType());
        assertEquals("should be a white piece at D1",
                Color.WHITE, _game.getPieceAtPosition(BoardPosition.D1).getColor());
    }

    @Test
    public void shouldLetWhiteMovePawnFromA2ToA3successfully() {
        Piece pieceAtFrom = _game.getPieceAtPosition(BoardPosition.A2);
        assertNull("there should be no pieces at target position A3",
                _game.getPieceAtPosition(BoardPosition.A3));
        assertTrue("should let white move the pawn at A2 to A3",
                _game.movePiece(BoardPosition.A2, BoardPosition.A3));
        assertEquals("piece should have moved successfully from A2 to A3",
                pieceAtFrom, _game.getPieceAtPosition(BoardPosition.A3));
    }

    @Test
    public void shouldNotLetWhiteMoveBlackPawn() {
        assertEquals("check that white is in turn",
                Color.WHITE, _game.getPlayerInTurn());
        assertEquals("check that piece is black",
                Color.BLACK, _game.getPieceAtPosition(BoardPosition.A7).getColor());
        assertFalse("should not let white move a black pawn",
                _game.movePiece(BoardPosition.A7, BoardPosition.A6));
    }

    @Test
    public void shouldNotLetBlackMoveWhitePawn() {
        // perform white move to let black be in turn
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);

        assertFalse("should not let black move a white pawn",
                _game.movePiece(BoardPosition.B2, BoardPosition.B3));
    }

    @Test
    public void shouldNotLetWhiteMoveToFieldOccupiedByAnotherWhitePiece() {
        BoardPosition whitePawnPos = BoardPosition.B2;
        BoardPosition otherWhitePawnPos = BoardPosition.A2;
        assertFalse("Should not let white move a piece to an occupied field",
                _game.movePiece(whitePawnPos, otherWhitePawnPos));
    }

    @Test
    public void shouldNotLetWhiteMoveToFieldOccupiedByBlackPiece() {
        BoardPosition whitePawnPos = BoardPosition.B2;
        BoardPosition blackPawnPos = BoardPosition.A7;
        assertFalse("Should not let white piece attack a black one",
                _game.movePiece(whitePawnPos, blackPawnPos));
    }

    @Test
    public void shouldLetBlackMoveBlackPawnAtA7ToA6() {
        // perform white move to let black be in turn
        _game.movePiece(BoardPosition.A2, BoardPosition.A3);

        Piece pieceAtFrom = _game.getPieceAtPosition(BoardPosition.A7);
        assertNull("there should be no pieces at target position A6",
                _game.getPieceAtPosition(BoardPosition.A6));
        assertTrue("should let black move the pawn at A7 to A6",
                _game.movePiece(BoardPosition.A7, BoardPosition.A6));
        assertEquals("piece should have moved successfully from A7 to A6",
                pieceAtFrom, _game.getPieceAtPosition(BoardPosition.A6));
    }

}
