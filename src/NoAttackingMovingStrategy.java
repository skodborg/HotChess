/**
 * Created by marc on 07/12/13.
 */
public class NoAttackingMovingStrategy implements MovingStrategy {
    @Override
    public boolean isMoveValid(Game game, BoardPosition from, BoardPosition to) {
        return isPlayerMovingOwnPiece(game, from)
                && isTargetFieldFree(game, to);
    }

    private boolean isTargetFieldFree(Game game, BoardPosition to) {
        return game.getPieceAtPosition(to) == null;
    }

    private boolean isPlayerMovingOwnPiece(Game game, BoardPosition from) {
        return game.getPlayerInTurn() == game.getPieceAtPosition(from).getColor();
    }
}
