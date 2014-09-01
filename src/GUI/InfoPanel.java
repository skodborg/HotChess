package GUI;

import Production.Game;
import Production.Observer;
import Production.Color;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements Observer {

    public static final int HEIGHT = 50;

    private Game _game;
    private JLabel lblPlayerInTurn;
    private JLabel lblWhiteCheckMateStatus;

    public InfoPanel(Game game) {
        _game = game;

        // add the info panel to the list of Observers in Game
        _game.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(Skeleton.BOARD_SIZE, HEIGHT));
        setVisible(true);

        initialize();
    }

    private void initialize() {
        lblPlayerInTurn = new JLabel("In turn: " + _game.getPlayerInTurn());
        add(lblPlayerInTurn);
        lblWhiteCheckMateStatus = new JLabel();
        add(lblWhiteCheckMateStatus);
    }

    @Override
    public void update() {
        lblPlayerInTurn.setText("In turn: " + _game.getPlayerInTurn());
        if (_game.isCheck()) {
            Color checkMatedPlayer;
            if ((checkMatedPlayer = _game.isPlayerInCheckMate()) != Color.NONE) {
                lblWhiteCheckMateStatus.setText(checkMatedPlayer.name() + " is in Check Mate!");
            } else {
                lblWhiteCheckMateStatus.setText("White is in Check");
            }
        } else {
            lblWhiteCheckMateStatus.setText("");
        }
    }
}
