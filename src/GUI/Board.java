package GUI;

import Production.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Board extends JPanel {

    public static final int FIELD_SIZE = Skeleton.BOARD_SIZE / 8;

    // BLACK PIECE IMAGES
    private final String blackPawnPath = "../Resources/BPawn.png";
    private final Image blackPawnImg = new ImageIcon(this.getClass().getResource(blackPawnPath)).getImage();
    private final String blackRookPath = "../Resources/BRook.png";
    private final Image blackRookImg = new ImageIcon(this.getClass().getResource(blackRookPath)).getImage();
    private final String blackBishopPath = "../Resources/BBishop.png";
    private final Image blackBishopImg = new ImageIcon(this.getClass().getResource(blackBishopPath)).getImage();
    private final String blackKnightPath = "../Resources/BKnight.png";
    private final Image blackKnightImg = new ImageIcon(this.getClass().getResource(blackKnightPath)).getImage();
    private final String blackQueenPath = "../Resources/BQueen.png";
    private final Image blackQueenImg = new ImageIcon(this.getClass().getResource(blackQueenPath)).getImage();
    private final String blackKingPath = "../Resources/BKing.png";
    private final Image blackKingImg = new ImageIcon(this.getClass().getResource(blackKingPath)).getImage();

    // WHITE PIECE IMAGES
    private final String whitePawnPath = "../Resources/WPawn.png";
    private final Image whitePawnImg = new ImageIcon(this.getClass().getResource(whitePawnPath)).getImage();
    private final String whiteRookPath = "../Resources/WRook.png";
    private final Image whiteRookImg = new ImageIcon(this.getClass().getResource(whiteRookPath)).getImage();
    private final String whiteBishopPath = "../Resources/WBishop.png";
    private final Image whiteBishopImg = new ImageIcon(this.getClass().getResource(whiteBishopPath)).getImage();
    private final String whiteKnightPath = "../Resources/WKnight.png";
    private final Image whiteKnightImg = new ImageIcon(this.getClass().getResource(whiteKnightPath)).getImage();
    private final String whiteQueenPath = "../Resources/WQueen.png";
    private final Image whiteQueenImg = new ImageIcon(this.getClass().getResource(whiteQueenPath)).getImage();
    private final String whiteKingPath = "../Resources/WKing.png";
    private final Image whiteKingImg = new ImageIcon(this.getClass().getResource(whiteKingPath)).getImage();

    private final Game _game;
    private char[][] boardState;
    private ViewUtility _viewUtil;

    public Board(Game game) {
        _game = game;
        _viewUtil = new ViewUtility();
        boardState = _viewUtil.describeBoardState(game);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // update game state before repainting
        boardState = _viewUtil.describeBoardState(_game);

        paintBoardSquares(g2);
        paintPieces(g2);

    }

    private void paintBoardSquares(Graphics2D g2) {
        Color lightColor = new Color(255, 190, 80);
        Color darkColor = new Color(160, 100, 0);
        boolean whiteFieldNext = true;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (whiteFieldNext) {
                    g2.setColor(lightColor);
                } else {
                    g2.setColor(darkColor);
                }
                Rectangle2D rect = new Rectangle2D.Double(i * FIELD_SIZE, j * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
                g2.draw(rect);
                g2.fill(rect);
                // alternate black/white fields painted
                whiteFieldNext = (whiteFieldNext) ? false : true;
            }
            // alternate black/white fields painted
            whiteFieldNext = (whiteFieldNext) ? false : true;
        }
    }

    private void paintPieces(Graphics2D g2) {

        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {
                switch (boardState[i][j]) {
                    case 'p' :
                        g2.drawImage(whitePawnImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'P' :
                        g2.drawImage(blackPawnImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'r' :
                        g2.drawImage(whiteRookImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'R' :
                        g2.drawImage(blackRookImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'b' :
                        g2.drawImage(whiteBishopImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'B' :
                        g2.drawImage(blackBishopImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'n' :
                        g2.drawImage(whiteKnightImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'N' :
                        g2.drawImage(blackKnightImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'q' :
                        g2.drawImage(whiteQueenImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'Q' :
                        g2.drawImage(blackQueenImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'k' :
                        g2.drawImage(whiteKingImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    case 'K' :
                        g2.drawImage(blackKingImg, FIELD_SIZE * i, FIELD_SIZE * (7 - j), null);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
