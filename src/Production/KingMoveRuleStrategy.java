package Production;

import GUI.Board;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class KingMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from, Game game, Map<BoardPosition, Piece> pieceMap) {
        Set<BoardPosition> validPositions = new TreeSet<BoardPosition>();
        Piece movingPiece = pieceMap.get(from);
        BoardPosition currentPos;
        if ((currentPos = BoardPosition.north(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northEast(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.east(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southEast(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.south(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.southWest(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.west(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.northWest(from)) != null
                && onlyValidAttacks(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }

        if ((currentPos = BoardPosition.east(BoardPosition.east(from))) != null
                && isCastlingShortValid(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.west(BoardPosition.west(from))) != null
                && isCastlingLongValid(pieceMap, movingPiece, currentPos)) {
            validPositions.add(currentPos);
        }

        return validPositions.iterator();
    }

    // returns true if the target position is not occupied, or contains a piece of the opposing players color
    private boolean onlyValidAttacks(Map<BoardPosition, Piece> pieceMap,
                                     Piece movingPiece,
                                     BoardPosition targetPosition) {
        Piece targetPiece = pieceMap.get(targetPosition);
        return targetPiece == null || targetPiece.getColor() != movingPiece.getColor();
    }

    private boolean isCastlingShortValid(Map<BoardPosition, Piece> pieceMap,
                                         Piece kingPiece,
                                         BoardPosition kingPosition) {
        // TODO: implement
        if (!kingPiece.getType().equals(GameConstants.KING)) {
            // castling piece is not a king; bail out
            return false;
        }
        if (kingPiece instanceof StatePieceImpl) {
            StatePieceImpl stateKingPiece = (StatePieceImpl) kingPiece;
            if (stateKingPiece.hasMoved()) {
                return false;
            }
            Piece rookShort = (kingPiece.getColor() == Color.WHITE) ? pieceMap.get(BoardPosition.H1) : pieceMap.get(BoardPosition.H8);
            if (rookShort != null) {

                // bail out if the castling rook is not actually a rook type piece
                if (!rookShort.getType().equals(GameConstants.ROOK)) { return false; }

                if (rookShort instanceof StatePieceImpl) {
                    StatePieceImpl stateRook = (StatePieceImpl) rookShort;
                    if (stateRook.hasMoved()) {
                        return false;
                    }
                }
            }


            else {
                return false;
            }
        }
        return true;
    }

    private boolean isCastlingLongValid(Map<BoardPosition, Piece> pieceMap,
                                         Piece kingPiece,
                                         BoardPosition kingPosition) {
        // TODO: implement
        if (kingPiece instanceof StatePieceImpl) {
            StatePieceImpl stateKingPiece = (StatePieceImpl) kingPiece;
            return !stateKingPiece.hasMoved();
        }
        return true;
    }
}
