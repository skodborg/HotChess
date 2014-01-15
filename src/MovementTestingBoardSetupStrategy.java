import java.util.Map;

/*
Movement Testing Chess Board Layout:
   A B C D E F G H
  -----------------
8 |x x x B x x x x|
7 |P R x x x x x x|
6 |x x x x x x x x|
5 |x x x x x x x x|
4 |x x x x x x x x|
3 |x x x b x x x x|
2 |p r x x x x x x|
1 |x x x x x x x x|
  -----------------

BIG letters = Black piece
small letters = White piece

Pawn = P/p
Rook = R/r
Bishop = B/b

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

        // bishops
        pieceMap.put(BoardPosition.D3, new PieceImpl(new WhiteBishopPieceFactory()));
        pieceMap.put(BoardPosition.D8, new PieceImpl(new BlackBishopPieceFactory()));

    }
}
