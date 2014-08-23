package GUI;

import Production.Game;
import Production.Observer;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements Observer {

    public static final int HEIGHT = 100;

    private Game _game;
    private JLabel lblPlayerInTurn;
    private JLabel lblIsWhiteInCheck;

    public InfoPanel(Game game) {
        _game = game;

        // add the info panel to the list of Observers in Game
        _game.addObserver(this);

        setBackground(Color.RED);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(Skeleton.BOARD_SIZE, HEIGHT));
        setVisible(true);

        initialize();
    }

    private void initialize() {
        lblPlayerInTurn = new JLabel("In turn: " + _game.getPlayerInTurn());
        add(lblPlayerInTurn);
        lblIsWhiteInCheck = new JLabel();
        add(lblIsWhiteInCheck);
    }

    @Override
    public void update() {
        lblPlayerInTurn.setText("In turn: " + _game.getPlayerInTurn());
        if (_game.isCheck()) {
            lblIsWhiteInCheck.setText("White is in check");
        } else {
            lblIsWhiteInCheck.setText("");
        }
    }
}
