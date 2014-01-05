import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
public class SimpleBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<BoardPosition, Piece> pieceMap) {

        pieceMap.put(BoardPosition.A2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(BoardPosition.B2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(BoardPosition.C2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(BoardPosition.D2, new PieceImpl(GameConstants.PAWN, Color.WHITE));
        pieceMap.put(BoardPosition.A7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(BoardPosition.B7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(BoardPosition.C7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(BoardPosition.D7, new PieceImpl(GameConstants.PAWN, Color.BLACK));
        pieceMap.put(BoardPosition.D1, new PieceImpl(GameConstants.QUEEN, Color.WHITE));
    }
}
