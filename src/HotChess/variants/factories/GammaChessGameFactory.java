package HotChess.variants.factories;

import HotChess.BoardSetupStrategy;
import HotChess.ChessGameFactory;
import HotChess.MovingStrategy;
import HotChess.variants.FullBoardSetupStrategy;
import HotChess.variants.PieceSpecificMovingStrategy;

public class GammaChessGameFactory implements ChessGameFactory {
    @Override
    public MovingStrategy createMovingStrategy() {
        return new PieceSpecificMovingStrategy();
    }

    @Override
    public BoardSetupStrategy createBoardSetupStrategy() {
        return new FullBoardSetupStrategy();
    }
}
