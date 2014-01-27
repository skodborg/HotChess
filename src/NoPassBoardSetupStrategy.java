import java.util.Map;

/*
Movement Testing Chess Board Layout:
   A B C D E F G H
  -----------------
8 |x x x x x x x x|
7 |x x x x x x x x|
6 |x x x p x x x x|
5 |x x x x x x x x|
4 |x p x r x x P x|
3 |x x x x x x x x|
2 |x x x x x r x x|
1 |x x x P x x x x|
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

public class NoPassBoardSetupStrategy implements BoardSetupStrategy {

    @Override
    public void setupPieces(Map<BoardPosition, Piece> pieceMap) {
        pieceMap.put(BoardPosition.D4, new PieceImpl(GameConstants.ROOK, Color.WHITE, new RookNoPassMoveRuleStrategy()));
        pieceMap.put(BoardPosition.F2, new PieceImpl(GameConstants.ROOK, Color.WHITE, new RookNoPassMoveRuleStrategy()));
        pieceMap.put(BoardPosition.D6, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.B4, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.D1, new PieceImpl(new BlackPawnPieceFactory()));
        pieceMap.put(BoardPosition.G4, new PieceImpl(new BlackPawnPieceFactory()));
    }
}
