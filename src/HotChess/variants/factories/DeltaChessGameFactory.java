package HotChess.variants.factories;

import HotChess.BoardSetupStrategy;
import HotChess.ChessGameFactory;
import HotChess.MovingStrategy;
import HotChess.variants.AttacksAllowedMovingStrategy;
import HotChess.variants.SimpleBoardSetupStrategy;

public class DeltaChessGameFactory implements ChessGameFactory {
    @Override
    public MovingStrategy createMovingStrategy() {
        return new AttacksAllowedMovingStrategy();
    }

    @Override
    public BoardSetupStrategy createBoardSetupStrategy() {
        return new SimpleBoardSetupStrategy();
    }
}
