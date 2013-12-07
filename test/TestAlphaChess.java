import HotChess.*;
import HotChess.variants.factories.AlphaChessGameFactory;
import HotChess.variants.NoAttackingMovingStrategy;
import HotChess.variants.SimpleBoardSetupStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marc on 07/12/13.
 */
public class TestAlphaChess {

    private Game _game;


    @Before
    public void setup() {
        _game = new GameImpl(new SimpleBoardSetupStrategy(), new NoAttackingMovingStrategy(), new AlphaChessGameFactory());
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

    @Test
    public void shouldHaveBlackPawnAtPosA7() {
        assertEquals("should have piece of type PAWN at A7",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.A7).getType());
        assertEquals("should be a black piece at A7",
                Color.BLACK, _game.getPieceAtPosition(Position.A7).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosB2() {
        assertEquals("should have piece of type PAWN at B2",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.B2).getType());
        assertEquals("should be a white piece at B2",
                Color.WHITE, _game.getPieceAtPosition(Position.B2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosC2() {
        assertEquals("should have piece of type PAWN at C2",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.C2).getType());
        assertEquals("should be a white piece at C2",
                Color.WHITE, _game.getPieceAtPosition(Position.C2).getColor());
    }

    @Test
    public void shouldHaveWhitePawnAtPosD2() {
        assertEquals("should have piece of type PAWN at D2",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.D2).getType());
        assertEquals("should be a white piece at D2",
                Color.WHITE, _game.getPieceAtPosition(Position.D2).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosB7() {
        assertEquals("should have piece of type PAWN at B7",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.B7).getType());
        assertEquals("should be a black piece at B7",
                Color.BLACK, _game.getPieceAtPosition(Position.B7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosC7() {
        assertEquals("should have piece of type PAWN at C7",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.C7).getType());
        assertEquals("should be a black piece at C7",
                Color.BLACK, _game.getPieceAtPosition(Position.C7).getColor());
    }

    @Test
    public void shouldHaveBlackPawnAtPosD7() {
        assertEquals("should have piece of type PAWN at D7",
                GameConstants.PAWN, _game.getPieceAtPosition(Position.D7).getType());
        assertEquals("should be a black piece at D7",
                Color.BLACK, _game.getPieceAtPosition(Position.D7).getColor());
    }

    @Test
    public void shouldHaveWhiteQueenAtD1() {
        assertEquals("should have piece of type QUEEN at D1",
                GameConstants.QUEEN, _game.getPieceAtPosition(Position.D1).getType());
        assertEquals("should be a white piece at D1",
                Color.WHITE, _game.getPieceAtPosition(Position.D1).getColor());
    }

    @Test
    public void shouldLetWhiteMovePawnFromA2ToA3successfully() {
        Piece pieceAtFrom = _game.getPieceAtPosition(Position.A2);
        assertNull("there should be no pieces at target position A3",
                _game.getPieceAtPosition(Position.A3));
        assertTrue("should let white move the pawn at A2 to A3",
                _game.movePiece(Position.A2, Position.A3));
        assertEquals("piece should have moved successfully from A2 to A3",
                pieceAtFrom, _game.getPieceAtPosition(Position.A3));
    }

    @Test
    public void shouldNotLetWhiteMoveBlackPawn() {
        assertEquals("check that white is in turn",
                Color.WHITE, _game.getPlayerInTurn());
        assertEquals("check that piece is black",
                Color.BLACK, _game.getPieceAtPosition(Position.A7).getColor());
        assertFalse("should not let white move a black pawn",
                _game.movePiece(Position.A7, Position.A6));
    }

    @Test
    public void shouldLetBlackMoveBlackPawnAtA7ToA6() {
        // perform white move to let black be in turn
        _game.movePiece(Position.A2, Position.A3);

        Piece pieceAtFrom = _game.getPieceAtPosition(Position.A7);
        assertNull("there should be no pieces at target position A6",
                _game.getPieceAtPosition(Position.A6));
        assertTrue("should let black move the pawn at A7 to A6",
                _game.movePiece(Position.A7, Position.A6));
        assertEquals("piece should have moved successfully from A7 to A6",
                pieceAtFrom, _game.getPieceAtPosition(Position.A6));
    }

    @Test
    public void shouldNotLetBlackMoveWhitePawn() {
        // perform white move to let black be in turn
        _game.movePiece(Position.A2, Position.A3);

        assertFalse("should not let black move a white pawn",
                _game.movePiece(Position.B2, Position.B3));
    }

    @Test
    public void shouldNotLetWhiteMoveToFieldOccupiedByAnotherWhitePiece() {
        Position whitePawnPos = Position.B2;
        Position otherWhitePawnPos = Position.A2;
        assertFalse("Should not let white move a piece to an occupied field",
                _game.movePiece(whitePawnPos, otherWhitePawnPos));
    }

    @Test
    public void shouldNotLetWhiteMoveToFieldOccupiedByBlackPiece() {
        Position whitePawnPos = Position.B2;
        Position blackPawnPos = Position.A7;
        assertFalse("Should not let white piece attack a black one",
                _game.movePiece(whitePawnPos, blackPawnPos));
    }

}
