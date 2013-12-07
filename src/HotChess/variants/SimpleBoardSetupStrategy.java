package HotChess.variants;

import HotChess.*;

import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
public class SimpleBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<Position, Piece> pieceMap) {

        pieceMap.put(Position.A2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.B2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.C2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.D2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(Position.A7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.B7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.C7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.D7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(Position.D1, new PieceImpl(GameConstants.QUEEN, Color.WHITE));
    }
}
