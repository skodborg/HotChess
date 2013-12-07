package HotChess;

/**
 * Created by marc on 07/12/13.
 */
public interface ChessGameFactory {
    MovingStrategy createMovingStrategy();

    BoardSetupStrategy createBoardSetupStrategy();
}
