package HotChess.variants;

import HotChess.Game;
import HotChess.MovingStrategy;
import HotChess.Position;

/**
 * Created by marc on 07/12/13.
 */
public class NoAttackingMovingStrategy implements MovingStrategy {
    @Override
    public boolean isMoveValid(Game game, Position from, Position to) {
        return isPlayerMovingOwnPiece(game, from)
                && isTargetFieldFree(game, to);
    }

    private boolean isTargetFieldFree(Game game, Position to) {
        return game.getPieceAtPosition(to) == null;
    }

    private boolean isPlayerMovingOwnPiece(Game game, Position from) {
        return game.getPlayerInTurn() == game.getPieceAtPosition(from).getColor();
    }
}
