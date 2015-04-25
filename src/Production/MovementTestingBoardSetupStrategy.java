package Production;

import java.util.Map;

/*
Movement Testing Chess Board Layout:
   A B C D E F G H
  -----------------
8 |x x x B x Q x x|
7 |P R x x x x x K|
6 |x x x x x x x x|
5 |x x x n x x x x|
4 |x x x b x x x N|
3 |x x x x x q x x|
2 |p x x k x x x x|
1 |x r x x x x x x|
  -----------------

BIG letters = Black piece
small letters = White piece

Pawn = P/p
Rook = R/r
Bishop = B/b
Queen = Q/q
King = K/k
Knight = N/n

 */

public class MovementTestingBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<BoardPosition, Piece> pieceMap) {
        // pawns
        pieceMap.put(BoardPosition.A2, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.A7, new PieceImpl(new BlackPawnPieceFactory()));

        // rooks
        pieceMap.put(BoardPosition.B1, new PieceImpl(new WhiteRookPieceFactory()));
        pieceMap.put(BoardPosition.B7, new PieceImpl(new BlackRookPieceFactory()));

        // bishops
        pieceMap.put(BoardPosition.D4, new PieceImpl(new WhiteBishopPieceFactory()));
        pieceMap.put(BoardPosition.D8, new PieceImpl(new BlackBishopPieceFactory()));

        // queens
        pieceMap.put(BoardPosition.F3, new PieceImpl(new WhiteQueenPieceFactory()));
        pieceMap.put(BoardPosition.F8, new PieceImpl(new BlackQueenPieceFactory()));

        // kings
        pieceMap.put(BoardPosition.D2, new PieceImpl(new WhiteKingPieceFactory()));
        pieceMap.put(BoardPosition.H7, new PieceImpl(new BlackKingPieceFactory()));

        // knights
        pieceMap.put(BoardPosition.D5, new PieceImpl(new WhiteKnightPieceFactory()));
        pieceMap.put(BoardPosition.H4, new PieceImpl(new BlackKnightPieceFactory()));

    }
}
