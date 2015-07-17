package Production.Strategies.BoardSetup;

import Production.*;
import Production.Factories.*;
import Production.Utility.BoardPosition;

import java.util.Map;

public class FullBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<BoardPosition, Piece> pieceMap) {

        // pawns
        pieceMap.put(BoardPosition.A2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.B2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.C2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.D2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.E2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.F2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.G2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.H2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.A7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.B7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.C7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.D7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.E7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.F7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.G7, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.H7, new PieceImpl(new BlackPawnPieceFactory()));

        // rooks
        pieceMap.put(BoardPosition.A1, new StatePieceImpl(new WhiteRookPieceFactory()));
        pieceMap.put(BoardPosition.H1, new StatePieceImpl(new WhiteRookPieceFactory()));
        pieceMap.put(BoardPosition.A8, new StatePieceImpl(new BlackRookPieceFactory()));
        pieceMap.put(BoardPosition.H8, new StatePieceImpl(new BlackRookPieceFactory()));

        // knights
        pieceMap.put(BoardPosition.B1, new PieceImpl(new WhiteKnightPieceFactory()));
        pieceMap.put(BoardPosition.G1, new PieceImpl(new WhiteKnightPieceFactory()));
        pieceMap.put(BoardPosition.B8, new PieceImpl(new BlackKnightPieceFactory()));
        pieceMap.put(BoardPosition.G8, new PieceImpl(new BlackKnightPieceFactory()));

        // bishops
        pieceMap.put(BoardPosition.C1, new PieceImpl(new WhiteBishopPieceFactory()));
        pieceMap.put(BoardPosition.F1, new PieceImpl(new WhiteBishopPieceFactory()));
        pieceMap.put(BoardPosition.C8, new PieceImpl(new BlackBishopPieceFactory()));
        pieceMap.put(BoardPosition.F8, new PieceImpl(new BlackBishopPieceFactory()));

        // queens
        pieceMap.put(BoardPosition.D1, new PieceImpl(new WhiteQueenPieceFactory()));
        pieceMap.put(BoardPosition.D8, new PieceImpl(new BlackQueenPieceFactory()));

        // kings
        pieceMap.put(BoardPosition.E1, new StatePieceImpl(new WhiteKingPieceFactory()));
        pieceMap.put(BoardPosition.E8, new StatePieceImpl(new BlackKingPieceFactory()));
    }
}
