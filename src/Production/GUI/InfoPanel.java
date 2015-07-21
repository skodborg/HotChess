package Production.GUI;

import Production.Game;
import Production.Observer;
import Production.Utility.Color;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements Observer {

    public static final int HEIGHT = 50;

    private Game _game;
    private JLabel lblPlayerInTurn;
    private JLabel lblGameStatus;

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
        lblGameStatus = new JLabel();
        add(lblGameStatus);
        update();
    }

    @Override
    public void update() {
        lblPlayerInTurn.setText("In turn: " + _game.getPlayerInTurn());
        Color checkedPlayer = _game.isCheck();
        if (checkedPlayer != Color.NONE) {
            Color checkMatedPlayer;
            if ((checkMatedPlayer = _game.isPlayerInCheckMate()) != Color.NONE) {
                lblGameStatus.setText(checkMatedPlayer.name() + " is Check Mated!");
            } else {
                lblGameStatus.setText(checkedPlayer + " is Checked");
            }
        } else if (_game.isRemis()) {
            lblGameStatus.setText("Remis!");
        } else {
            lblGameStatus.setText("");
        }
        _game.getWinner();
    }
}
