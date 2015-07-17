package Production.Strategies.MoveRules;

import Production.*;
import Production.Utility.AlgorithmUtility;
import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Utility.GameConstants;

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
                && isCastlingValid(pieceMap, movingPiece, from, game, true)) {
            validPositions.add(currentPos);
        }
        if ((currentPos = BoardPosition.west(BoardPosition.west(from))) != null
                && isCastlingValid(pieceMap, movingPiece, from, game, false)) {
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

    private boolean isCastlingValid(Map<BoardPosition, Piece> pieceMap,
                                    Piece kingPiece,
                                    BoardPosition kingCurrPos,
                                    Game game,
                                    boolean isCastlingShort) {

        // TODO: missing rules
        //   - castling through an attacked field

        // castling piece is not a king; bail out
        if (!kingPiece.getType().equals(GameConstants.KING)) return false;

        // if the king trying to castle is checked, castling is not allowed; bail out
        if (game.isCheck().equals(kingPiece.getColor())) return false;

        if (kingPiece instanceof StatePieceImpl) {
            StatePieceImpl stateKingPiece = (StatePieceImpl) kingPiece;
            if (stateKingPiece.hasMoved()) {
                // king has previously moved; bail out
                return false;
            }

            // initialize correct rook depending on king color and short/long castling
            Piece rook;
            if (isCastlingShort) {
                rook = (kingPiece.getColor() == Color.WHITE) ? pieceMap.get(BoardPosition.H1) : pieceMap.get(BoardPosition.H8);
            } else {
                rook = (kingPiece.getColor() == Color.WHITE) ? pieceMap.get(BoardPosition.A1) : pieceMap.get(BoardPosition.A8);
            }

            if (rook != null) {
                // the castling rook is not actually a rook type piece; bail out
                if (!rook.getType().equals(GameConstants.ROOK)) {
                    return false;
                }

                // the castling rook has previously moved; bail out
                if (rook instanceof StatePieceImpl) {
                    StatePieceImpl stateRook = (StatePieceImpl) rook;
                    if (stateRook.hasMoved()) {
                        return false;
                    }
                }

                // check that the two/three fields between the king and rook are not occupied
                // - trdNeighbour is only relevant when castling long
                BoardPosition fstNeighbourPos =
                        isCastlingShort ? BoardPosition.east(kingCurrPos) :
                                          BoardPosition.west(kingCurrPos);
                Piece fstNeighbour= pieceMap.get(fstNeighbourPos);
                BoardPosition sndNeighbourPos =
                        isCastlingShort ? BoardPosition.east(BoardPosition.east(kingCurrPos)) :
                                          BoardPosition.west(BoardPosition.west(kingCurrPos));
                Piece sndNeighbour = pieceMap.get(sndNeighbourPos);
                // trdNeighbourPos is only relevant when castling long, should be initialized to null if castling short
                BoardPosition trdNeighbourPos =
                        isCastlingShort ? null : BoardPosition.west(BoardPosition.west(BoardPosition.west(kingCurrPos)));
                Piece trdNeighbour = pieceMap.get(trdNeighbourPos);

                if (fstNeighbour != null || sndNeighbour != null || trdNeighbour != null) {
                    // one of the neighbouring fields on the castling side of the king is occupied; bail out
                    return false;
                }
            }

            else {
                // there is no piece on the rooks position; bail out
                return false;
            }

        }
        return true;
    }

}
