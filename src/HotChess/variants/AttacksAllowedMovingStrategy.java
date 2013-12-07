package HotChess.variants;

import HotChess.*;

/**
 * Created by marc on 07/12/13.
 */
public class AttacksAllowedMovingStrategy implements MovingStrategy {
    @Override
    public boolean isMoveValid(Game game, Position from, Position to) {

        Color playerInTurn = game.getPlayerInTurn();
        Piece movingPiece = game.getPieceAtPosition(from);
        Piece targetPositionPiece = game.getPieceAtPosition(to);

        if (targetPositionPiece != null) {
            if (targetPositionPiece.getColor() == playerInTurn) {
                return false;
            }
        }

        return playerInTurn == movingPiece.getColor();
    }
}