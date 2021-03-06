package Production.GUI;

import Production.*;
import Production.AiBot.CleverAiBot;
import Production.Utility.BoardPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board extends JPanel implements MouseListener, MouseMotionListener, Observer {

    public static final int FIELD_SIZE = Skeleton.BOARD_SIZE / 8;

    // BLACK PIECE IMAGES
    private final String blackPawnPath = "/Resources/BPawn.png";
    private final Image blackPawnImg = new ImageIcon(this.getClass().getResource(blackPawnPath)).getImage();
    private final String blackRookPath = "/Resources/BRook.png";
    private final Image blackRookImg = new ImageIcon(this.getClass().getResource(blackRookPath)).getImage();
    private final String blackBishopPath = "/Resources/BBishop.png";
    private final Image blackBishopImg = new ImageIcon(this.getClass().getResource(blackBishopPath)).getImage();
    private final String blackKnightPath = "/Resources/BKnight.png";
    private final Image blackKnightImg = new ImageIcon(this.getClass().getResource(blackKnightPath)).getImage();
    private final String blackQueenPath = "/Resources/BQueen.png";
    private final Image blackQueenImg = new ImageIcon(this.getClass().getResource(blackQueenPath)).getImage();
    private final String blackKingPath = "/Resources/BKing.png";
    private final Image blackKingImg = new ImageIcon(this.getClass().getResource(blackKingPath)).getImage();

    // WHITE PIECE IMAGES
    private final String whitePawnPath = "/Resources/WPawn.png";
    private final Image whitePawnImg = new ImageIcon(this.getClass().getResource(whitePawnPath)).getImage();
    private final String whiteRookPath = "/Resources/WRook.png";
    private final Image whiteRookImg = new ImageIcon(this.getClass().getResource(whiteRookPath)).getImage();
    private final String whiteBishopPath = "/Resources/WBishop.png";
    private final Image whiteBishopImg = new ImageIcon(this.getClass().getResource(whiteBishopPath)).getImage();
    private final String whiteKnightPath = "/Resources/WKnight.png";
    private final Image whiteKnightImg = new ImageIcon(this.getClass().getResource(whiteKnightPath)).getImage();
    private final String whiteQueenPath = "/Resources/WQueen.png";
    private final Image whiteQueenImg = new ImageIcon(this.getClass().getResource(whiteQueenPath)).getImage();
    private final String whiteKingPath = "/Resources/WKing.png";
    private final Image whiteKingImg = new ImageIcon(this.getClass().getResource(whiteKingPath)).getImage();

    private final Game _game;
    private char[][] boardState;
    private ViewUtility _viewUtil;
    private BoardPosition selectedPosition;
    private List<BoardPosition> validMovePositions;

    private CleverAiBot _ai;
    private CleverAiBot _ai2;

    public Board(Game game) {
        _game = game;
        _viewUtil = new ViewUtility();
        boardState = _viewUtil.describeBoardState(game.getPieceMap());
        validMovePositions = new ArrayList<BoardPosition>();

        addMouseListener(this);
        addMouseMotionListener(this);

        _ai = new CleverAiBot(Production.Utility.Color.WHITE, true);
        _ai2 = new CleverAiBot(Production.Utility.Color.BLACK, false);

        _game.addObserver(this);

        //startBots();
    }

    public void startBots() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean running = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (running && _game.getWinner().equals(Production.Utility.Color.NONE)) {
                    try {
                        Thread.sleep(100);
                        _ai.act(_game);
                        Thread.sleep(100);
                        _ai2.act(_game);
                    } catch (InterruptedException e) {
                        running = false;
                        e.printStackTrace();
                    } catch (Exception e) {
                        running = false;
                        // e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // update game state before repainting
        boardState = _viewUtil.describeBoardState(_game.getPieceMap());

        paintBoardSquares(g2);
        paintPieces(g2);
        paintValidMoves(g2);
        // paintWinningText(g2);

    }

    private void paintWinningText(Graphics2D g2) {
        Production.Utility.Color winner = _game.getWinner();
        if (winner != Production.Utility.Color.NONE) {
            g2.setColor(Color.CYAN);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("Game over, " + winner.toString().toLowerCase() + " won", 200, 200);
        }
        if (_game.isRemis()) {
            g2.setColor(Color.CYAN);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("Game over, ended in Remis", 200, 200);
        }
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
                whiteFieldNext = !whiteFieldNext;
            }
            // alternate black/white fields painted
            whiteFieldNext = !whiteFieldNext;
        }
    }

    private void paintValidMoves(Graphics2D g2) {

        Color validMove = new Color(105, 200, 80);

        // paint highlights on valid moving positions if any
        if (validMovePositions != null && validMovePositions.size() != 0) {
            for (BoardPosition bp : validMovePositions) {
                if (bp != null) {
                    int bp_X = bp.getIndex() % 8;
                    int bp_Y = 7 - (bp.getIndex() - (bp.getIndex() % 8)) / 8;
                    g2.setColor(validMove);
                    // alternative: cornered rectangle commented out below
                    // Rectangle2D rect = new Rectangle2D.Double(bp_X * FIELD_SIZE, bp_Y * FIELD_SIZE, 10, 10);
                    Rectangle2D rect = new Rectangle2D.Double(
                            bp_X * FIELD_SIZE + (FIELD_SIZE / 2 - (10 / 2)),
                            bp_Y * FIELD_SIZE + (FIELD_SIZE / 2 - (10 / 2)),
                            10,
                            10);
                    g2.draw(rect);
                    g2.fill(rect);
                }
            }
        }
    }

    private void paintPieces(Graphics2D g2) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (boardState[i][j]) {
                    case 'p' :
                        g2.drawImage(whitePawnImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'P' :
                        g2.drawImage(blackPawnImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'r' :
                        g2.drawImage(whiteRookImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'R' :
                        g2.drawImage(blackRookImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'b' :
                        g2.drawImage(whiteBishopImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'B' :
                        g2.drawImage(blackBishopImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'n' :
                        g2.drawImage(whiteKnightImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'N' :
                        g2.drawImage(blackKnightImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'q' :
                        g2.drawImage(whiteQueenImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'Q' :
                        g2.drawImage(blackQueenImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'k' :
                        g2.drawImage(whiteKingImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    case 'K' :
                        g2.drawImage(blackKingImg, FIELD_SIZE * j, FIELD_SIZE * i, null);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private BoardPosition getBoardPositionFromCoordinates(int x, int y) {
        int row = (Skeleton.BOARD_SIZE - 1 - (y - (y % FIELD_SIZE))) / FIELD_SIZE;
        int col = (x - (x % FIELD_SIZE)) / FIELD_SIZE;

        int boardPositionIndex = (8 * row) + col;
        return BoardPosition.indexToEnum[boardPositionIndex];
    }

    private Image getClickedImage(int x, int y) {
        // separate the board using FIELD_SIZE and modulo
        int row = (Skeleton.BOARD_SIZE - 1 - (y - (y % FIELD_SIZE))) / FIELD_SIZE;
        int col = (x - (x % FIELD_SIZE)) / FIELD_SIZE;

        // determine which entry in boardState[][] is clicked
        char piece = boardState[row][col];

        // use switch from paintPieces, but return Image field variable instead
        Image pieceImg = null;
        switch (piece) {
            case 'p' :
                pieceImg = whitePawnImg;
                break;
            case 'P' :
                pieceImg = blackPawnImg;
                break;
            case 'r' :
                pieceImg = whiteRookImg;
                break;
            case 'R' :
                pieceImg = blackRookImg;
                break;
            case 'b' :
                pieceImg = whiteBishopImg;
                break;
            case 'B' :
                pieceImg = blackBishopImg;
                break;
            case 'n' :
                pieceImg = whiteKnightImg;
                break;
            case 'N' :
                pieceImg = blackKnightImg;
                break;
            case 'q' :
                pieceImg = whiteQueenImg;
                break;
            case 'Q' :
                pieceImg = blackQueenImg;
                break;
            case 'k' :
                pieceImg = whiteKingImg;
                break;
            case 'K' :
                pieceImg = blackKingImg;
                break;
            default:
                break;
        }
        return pieceImg;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        BoardPosition clickedPosition = getBoardPositionFromCoordinates(x, y);

        Piece p;
        if ((p = _game.getPieceAtPosition(clickedPosition)) != null) {
            validMovePositions.clear();
            Iterator<BoardPosition> it = p.possibleMovingPositions(clickedPosition, _game, _game.getPieceMap());
            while (it.hasNext()) {
                BoardPosition nextPossiblePos = it.next();
                if (_game.isMoveValid(clickedPosition, nextPossiblePos)) {
                    validMovePositions.add(nextPossiblePos);
                }
            }
        } else {
            validMovePositions.clear();
        }


        if (p == null) {
            if (selectedPosition != null) {
                _game.movePiece(selectedPosition, clickedPosition);
                // TODO: REMOVE BELOW TRY-CATCH
                try {
                    _ai2.act(_game);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                selectedPosition = null;
            }
        } else {
            if (selectedPosition != null) {
                Piece selectedPiece = _game.getPieceAtPosition(selectedPosition);
                Production.Utility.Color selectedPieceColor = selectedPiece.getColor();
                if (selectedPieceColor != p.getColor()) {
                    // different piece colors, try and attack!
                    if (_game.movePiece(selectedPosition, clickedPosition)) {
                        // TODO: REMOVE BELOW TRY-CATCH
                        try {
                            _ai2.act(_game);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // move successful, reset selectedPosition
                        selectedPosition = null;
                    }
                    selectedPosition = null;
                } else {
                    // same colors, update state
                    selectedPosition = clickedPosition;
                }
            } else {
                if (p.getColor() == _game.getPlayerInTurn()) {
                    selectedPosition = clickedPosition;
                }
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void update() {
        repaint();
    }
}
