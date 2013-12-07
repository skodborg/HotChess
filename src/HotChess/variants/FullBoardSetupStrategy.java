package HotChess.variants;

import HotChess.*;

import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
public class FullBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<Position, Piece> pieceMap) {

        // pawns
        pieceMap.put(Position.A2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.B2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.C2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.D2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.E2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.F2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.G2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.H2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.A7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.B7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.C7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.D7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.E7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.F7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.G7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.H7, new PieceImpl(GameConstants.PAWN, Color.BLACK));

        // rooks
        pieceMap.put(Position.A1, new PieceImpl(GameConstants.ROOK, Color.WHITE));
        pieceMap.put(Position.H1, new PieceImpl(GameConstants.ROOK, Color.WHITE));
        pieceMap.put(Position.A8, new PieceImpl(GameConstants.ROOK, Color.BLACK));
        pieceMap.put(Position.H8, new PieceImpl(GameConstants.ROOK, Color.BLACK));

        // knights
        pieceMap.put(Position.B1, new PieceImpl(GameConstants.KNIGHT, Color.WHITE));
        pieceMap.put(Position.G1, new PieceImpl(GameConstants.KNIGHT, Color.WHITE));
        pieceMap.put(Position.B8, new PieceImpl(GameConstants.KNIGHT, Color.BLACK));
        pieceMap.put(Position.G8, new PieceImpl(GameConstants.KNIGHT, Color.BLACK));

        // bishops
        pieceMap.put(Position.C1, new PieceImpl(GameConstants.BISHOP, Color.WHITE));
        pieceMap.put(Position.F1, new PieceImpl(GameConstants.BISHOP, Color.WHITE));
        pieceMap.put(Position.C8, new PieceImpl(GameConstants.BISHOP, Color.BLACK));
        pieceMap.put(Position.F8, new PieceImpl(GameConstants.BISHOP, Color.BLACK));

        // queens
        pieceMap.put(Position.D1, new PieceImpl(GameConstants.QUEEN, Color.WHITE));
        pieceMap.put(Position.D8, new PieceImpl(GameConstants.QUEEN, Color.BLACK));

        // kings
        pieceMap.put(Position.E1, new PieceImpl(GameConstants.KING, Color.WHITE));
        pieceMap.put(Position.E8, new PieceImpl(GameConstants.KING, Color.BLACK));
    }
}
