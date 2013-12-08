package HotChess.variants;

import HotChess.*;

public class PieceSpecificMovingStrategy implements MovingStrategy {
    @Override
    public boolean isMoveValid(Game game, Position from, Position to) {
        Position[] neighbours = Position.getNeighbourPositions(from);
        Piece movingPiece = game.getPieceAtPosition(from);

        // if a unit is already present at the target, return false immediately
        if (game.getPieceAtPosition(to) != null) {
            return false;
        }

        if (movingPiece.getType().equals(GameConstants.PAWN)) {

            // PAWN
            // if the target position is equal to neighbours[0] for white and
            // neighbours[4] for black, corresponding to the field just in
            // front of the piece, then the move is legal
            if (Color.BLACK == movingPiece.getColor()) {
                return neighbours[4] == to;
            } else {
                return neighbours[0] == to;
            }
        } else {

            // ROOK
            // if the target position is contained in the verticalPosition-list or
            // the horizontalPosition-list and no unit is passed while traveling,
            // then the move is legal

            if (Position.getVerticalPositions(from).contains(to)) {

                return true;
            } else if (Position.getHorizontalPositions(from).contains(to)) {
                return true;
            }

            return false;
        }
    }
}
