package GUI;

import Production.*;

public class ViewUtility {

    /*
    returns a char[8][8] array describing the state of the board
    on the game given as parameter
     */
    public char[][] describeBoardState(Game game) {
        char[][] boardState = new char[8][8];
        updateBoardInfo(boardState, game);
        return boardState;
    }

    private void updateBoardInfo(char[][] boardState, Game game) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardState[i][j] = identifyPiece(BoardPosition.indexToEnum[8 * (7 - i) + j], game);
            }
        }
    }

    private char identifyPiece(BoardPosition pos, Game game) {
        Piece piece = game.getPieceAtPosition(pos);
        char pieceChar = 'x';

        if (piece == null) {
            // bail out
            return 'x';
        } else if (piece.getType().equals(GameConstants.PAWN)) {
            pieceChar = 'p';
        } else if (piece.getType().equals(GameConstants.ROOK)) {
            pieceChar = 'r';
        } else if (piece.getType().equals(GameConstants.BISHOP)) {
            pieceChar = 'b';
        } else if (piece.getType().equals(GameConstants.QUEEN)) {
            pieceChar = 'q';
        } else if (piece.getType().equals(GameConstants.KING)) {
            pieceChar = 'k';
        } else if (piece.getType().equals(GameConstants.KNIGHT)) {
            pieceChar = 'n';
        } else {
            // leave it at 'x'
        }

        // capitalize Char if the piece is Black, don't if the piece is White
        boolean blackPiece = piece.getColor().equals(Color.BLACK);

        if (blackPiece) {
            return Character.toUpperCase(pieceChar);
        } else {
            return pieceChar;
        }
    }
}
