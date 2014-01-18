public class AsciiGameView implements Observer {

    private Game _game;
    private char[][] board;

    public AsciiGameView(Game game) {
        _game = game;
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
        Piece piece = _game.getPieceAtPosition(pos);
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
        Game game = new GameImpl(new FullBoardSetupStrategy());
        AsciiGameView asciiView = new AsciiGameView(game);
        game.movePiece(BoardPosition.A2, BoardPosition.A3);
        asciiView.update();
        game.movePiece(BoardPosition.C7, BoardPosition.C6);
        asciiView.update();
    }
}
