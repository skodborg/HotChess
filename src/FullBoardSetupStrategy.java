import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
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
        pieceMap.put(BoardPosition.A1, new PieceImpl(GameConstants.ROOK, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.H1, new PieceImpl(GameConstants.ROOK, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.A8, new PieceImpl(GameConstants.ROOK, Color.BLACK, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.H8, new PieceImpl(GameConstants.ROOK, Color.BLACK, new NullMoveRuleStrategy()));

        // knights
        pieceMap.put(BoardPosition.B1, new PieceImpl(GameConstants.KNIGHT, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.G1, new PieceImpl(GameConstants.KNIGHT, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.B8, new PieceImpl(GameConstants.KNIGHT, Color.BLACK, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.G8, new PieceImpl(GameConstants.KNIGHT, Color.BLACK, new NullMoveRuleStrategy()));

        // bishops
        pieceMap.put(BoardPosition.C1, new PieceImpl(GameConstants.BISHOP, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.F1, new PieceImpl(GameConstants.BISHOP, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.C8, new PieceImpl(GameConstants.BISHOP, Color.BLACK, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.F8, new PieceImpl(GameConstants.BISHOP, Color.BLACK, new NullMoveRuleStrategy()));

        // queens
        pieceMap.put(BoardPosition.D1, new PieceImpl(GameConstants.QUEEN, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.D8, new PieceImpl(GameConstants.QUEEN, Color.BLACK, new NullMoveRuleStrategy()));

        // kings
        pieceMap.put(BoardPosition.E1, new PieceImpl(GameConstants.KING, Color.WHITE, new NullMoveRuleStrategy()));
        pieceMap.put(BoardPosition.E8, new PieceImpl(GameConstants.KING, Color.BLACK, new NullMoveRuleStrategy()));
    }
}
