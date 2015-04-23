package GUI;

import Production.FullBoardSetupStrategy;
import Production.Game;
import Production.GameImpl;

import javax.swing.*;
import java.awt.*;

public class Skeleton extends JFrame {

    public static final int BOARD_SIZE = 480;
    public static final int OSX_Y_OFFSET = 23;

    private int infoPanelHeight = 0;
    private boolean showInfoPanel = true;

    public Skeleton()  {
        Game game = new GameImpl(new FullBoardSetupStrategy());
        add(new Board(game), BorderLayout.CENTER);

        if (showInfoPanel) {
            InfoPanel infoPanel = new InfoPanel(game);
            add(infoPanel, BorderLayout.SOUTH);
            infoPanelHeight = infoPanel.HEIGHT;
        }

        setTitle("Skeleton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_SIZE, BOARD_SIZE + OSX_Y_OFFSET + infoPanelHeight);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    public static void main(String[] args) {
        new Skeleton();
    }
}
