package Production;

import Production.GUI.ViewUtility;
import Production.Strategies.BoardSetup.FullBoardSetupStrategy;
import Production.Utility.BoardPosition;
import Production.Utility.Color;

import java.util.Map;

public class AiSimulationEnv {

    public static final int NO_OF_SIMULATIONS = 1000000;

    private Game _game;
    private CleverAiBot _ai1;
    private CleverAiBot _ai2;

    private int _cntWhiteWins;
    private int _cntBlackWins;
    private int _cntRemis;
    private int _cntGamesPlayed;

    private double pctWhiteWins;
    private double pctBlackWins;
    private double pctRemis;

    public AiSimulationEnv() {
        _game = new GameImpl(new FullBoardSetupStrategy());
        _ai1 = new CleverAiBot(Color.WHITE, false);
        _ai2 = new CleverAiBot(Color.BLACK, true);
        _cntWhiteWins = 0;
        _cntBlackWins = 0;
        _cntRemis = 0;
        _cntGamesPlayed = 0;

        start();
    }

    private void start() {
        for (int i = 0; i < NO_OF_SIMULATIONS; i++) {
            simulateGame();
            evaluateResult();
            if (i % 100 == 0) {
                printStatus();
            }
        }
    }

    private void printStatus() {
        pctWhiteWins = round((double) _cntWhiteWins / _cntGamesPlayed * 100, 2);
        pctBlackWins = round((double) _cntBlackWins / _cntGamesPlayed * 100, 2);
        pctRemis = round((double) _cntRemis / _cntGamesPlayed * 100, 2);

        System.out.println("W: " + pctWhiteWins + "   B: " + pctBlackWins+ "   R: " + pctRemis + "   games: " + _cntGamesPlayed);

    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private void evaluateResult() {
        Color winner = _game.getWinner();
        if (winner.equals(Color.WHITE)) {
            _cntWhiteWins++;
        }
        else if (winner.equals(Color.BLACK)) {
            _cntBlackWins++;
        }
        else {
            _cntRemis++;
        }
    }

    private void simulateGame() {
        try {
            // initialize new game
            _game = new GameImpl(new FullBoardSetupStrategy());

            while (_game.getWinner().equals(Color.NONE) && !_game.isRemis()) {
                if (_game.getPlayerInTurn().equals(Color.WHITE)) {
                    _ai1.act(_game);
                } else {
                    _ai2.act(_game);
                }
            }
            _cntGamesPlayed++;
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    private void printBoardState(Map<BoardPosition, Piece> argPieceMap) {
        ViewUtility vu = new ViewUtility();
        char[][] bs = vu.describeBoardState(argPieceMap);
        for (int i = 0; i < bs.length; i++) {
            for (int j = 0; j < bs[0].length; j++) {
                System.out.print(bs[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new AiSimulationEnv();
    }
}
