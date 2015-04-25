package Production;

import java.util.Map;

/*
Movement Testing Chess Board Layout:
   A B C D E F G H
  -----------------
8 |K x x x x x x k|
7 |x x x x x x x x|
6 |x x x p x x x x|
5 |x x x x x x x x|
4 |x p x r x x P x|
3 |x x x x x x x x|
2 |x x x x B r x x|
1 |x x x x x x x x|
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

        // kings
        pieceMap.put(BoardPosition.H8, new PieceImpl(new WhiteKingPieceFactory()));
        pieceMap.put(BoardPosition.A8, new PieceImpl(new BlackKingPieceFactory()));

        // rooks
        pieceMap.put(BoardPosition.D4, new PieceImpl(GameConstants.ROOK, Color.WHITE, new RookNoPassMoveRuleStrategy()));
        pieceMap.put(BoardPosition.F2, new PieceImpl(GameConstants.ROOK, Color.WHITE, new RookNoPassMoveRuleStrategy()));

        // bishop
        pieceMap.put(BoardPosition.E2, new PieceImpl(GameConstants.BISHOP, Color.BLACK, new BishopNoPassMoveRuleStrategy()));

        // pawns
        pieceMap.put(BoardPosition.D6, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.B4, new PieceImpl(new WhitePawnPieceFactory()));
        pieceMap.put(BoardPosition.G4, new PieceImpl(new BlackPawnPieceFactory()));
    }
}
