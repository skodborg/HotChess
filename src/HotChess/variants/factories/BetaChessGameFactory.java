package HotChess.variants.factories;

import HotChess.BoardSetupStrategy;
import HotChess.ChessGameFactory;
import HotChess.MovingStrategy;
import HotChess.variants.FullBoardSetupStrategy;
import HotChess.variants.NoAttackingMovingStrategy;

public class BetaChessGameFactory implements ChessGameFactory {
    @Override
    public MovingStrategy createMovingStrategy() {
        return new NoAttackingMovingStrategy();
    }

    @Override
    public BoardSetupStrategy createBoardSetupStrategy() {
        return new FullBoardSetupStrategy();
    }
}
