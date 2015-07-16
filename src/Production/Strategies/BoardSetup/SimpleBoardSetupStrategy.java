package Production.Strategies.BoardSetup;

import Production.*;
import Production.Factories.BlackKingPieceFactory;
import Production.Factories.BlackPawnPieceFactory;
import Production.Factories.WhiteKingPieceFactory;
import Production.Factories.WhitePawnPieceFactory;
import Production.Strategies.MoveRules.NullMoveRuleStrategy;
import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Utility.GameConstants;

import java.util.Map;

public class SimpleBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<BoardPosition, Piece> pieceMap) {

        pieceMap.put(BoardPosition.A2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.B2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.C2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.D2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.A7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.B7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.C7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.D7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.D1, new PieceImpl(GameConstants.QUEEN, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.H7, new PieceImpl(new WhiteKingPieceFactory()));
        pieceMap.put(BoardPosition.H3, new PieceImpl(new BlackKingPieceFactory()));
    }
}
