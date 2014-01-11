import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
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
    }
}
