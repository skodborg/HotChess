package HotChess.variants.factories;

import HotChess.BoardSetupStrategy;
import HotChess.ChessGameFactory;
import HotChess.MovingStrategy;
import HotChess.variants.NoAttackingMovingStrategy;
import HotChess.variants.SimpleBoardSetupStrategy;

public class AlphaChessGameFactory implements ChessGameFactory {
    @Override
    public MovingStrategy createMovingStrategy() {
        return new NoAttackingMovingStrategy();
    }

    @Override
    public BoardSetupStrategy createBoardSetupStrategy() {
        return new SimpleBoardSetupStrategy();
    }
}
