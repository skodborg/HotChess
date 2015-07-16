package Production.GUI;

import Production.*;
import Production.Strategies.BoardSetup.FullBoardSetupStrategy;
import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Utility.GameConstants;

import java.util.HashMap;
import java.util.Map;

public class AsciiGameView implements Observer {

    //private Game _game;
    private Map<BoardPosition, Piece> _pieceMap;

    private char[][] board;

    public AsciiGameView(Map<BoardPosition, Piece> pieceMap) {
        //_game = game;
        _pieceMap = pieceMap;
        board = new char[8][8];
        update();
    }

    public void update() {
        updateBoardInfo();
        paintBoard();
    }

    private void updateBoardInfo() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = identifyPiece(BoardPosition.indexToEnum[8 * i + j]);
            }
        }
    }

    private char identifyPiece(BoardPosition pos) {
        Piece piece = _pieceMap.get(pos);
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

    private void paintBoard() {
        System.out.println("-------------------");

        for (int i = 7; i >= 0; i--) {
            System.out.print("| ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        FullBoardSetupStrategy strat = new FullBoardSetupStrategy();
        Map<BoardPosition, Piece> newPieceMap = new HashMap<BoardPosition, Piece>();
        strat.setupPieces(newPieceMap);
        AsciiGameView asciiView = new AsciiGameView(newPieceMap);
    }
}
