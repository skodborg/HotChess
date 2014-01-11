import java.util.Map;

/*
Movement Testing Chess Board Layout:

-----------------
|x x x x x x x x|
|x P R x x x x x|
|x x x x x x x x|
|x x x x x x x x|
|x x x x x x x x|
|x x x x x x x x|
|x p r x x x x x|
|x x x x x x x x|
-----------------

BIG letters = Black piece
small letters = White piece

Pawn = P/p
Rook = R/r

 */

public class MovementTestingBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<BoardPosition, Piece> pieceMap) {
        // pawns
        pieceMap.put(BoardPosition.A2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.A7, new PieceImpl(new BlackPawnPieceFactory()));

        // rooks
        pieceMap.put(BoardPosition.B2, new PieceImpl(new WhiteRookPieceFactory()));
        pieceMap.put(BoardPosition.B7, new PieceImpl(new BlackRookPieceFactory()));

    }
}
