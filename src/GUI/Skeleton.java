package GUI;

import Production.BoardPosition;
import Production.FullBoardSetupStrategy;
import Production.Game;
import Production.GameImpl;

import javax.swing.*;

public class Skeleton extends JFrame {

    public static final int BOARD_SIZE = 480;
    public static final int OSX_Y_OFFSET = 23;

    public Skeleton()  {
        Game game = new GameImpl(new FullBoardSetupStrategy());
        add(new Board(game));
        setTitle("Skeleton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_SIZE, BOARD_SIZE + OSX_Y_OFFSET);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        /*
        try {
            Thread.sleep(1000);
            game.movePiece(BoardPosition.D2, BoardPosition.D3);
            repaint();
            Thread.sleep(1000);
            game.movePiece(BoardPosition.C7, BoardPosition.C6);
            repaint();
            Thread.sleep(1000);
            game.movePiece(BoardPosition.C1, BoardPosition.F4);
            repaint();
            Thread.sleep(1000);
            game.movePiece(BoardPosition.G8, BoardPosition.F7);
            repaint();
            Thread.sleep(1000);
            game.movePiece(BoardPosition.G8, BoardPosition.F6);
            repaint();
            Thread.sleep(1000);
            game.movePiece(BoardPosition.F4, BoardPosition.C7);
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

    }

    public static void main(String[] args) {
        new Skeleton();
    }
}
