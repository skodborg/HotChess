package Production;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class AlgorithmUtility {

    public static boolean isCheck(Game game, Map<BoardPosition, Piece> pieceMap) {

        BoardPosition whiteKingPos = null;

        // find the positions of the white king
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            if (entry.getValue().getType().equals(GameConstants.KING)
                    && entry.getValue().getColor() == Color.WHITE) {
                whiteKingPos = entry.getKey();
            }
        }

        // for each black piece, save their possible moving positions
        TreeSet<BoardPosition> possibleBlackMovingPositions = new TreeSet<BoardPosition>();
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            if (entry.getValue().getColor() == Color.BLACK) {
                Piece curr = entry.getValue();
                BoardPosition currPos = entry.getKey();
                Iterator<BoardPosition> currentIt = curr.possibleMovingPositions(currPos, game, pieceMap);
                while (currentIt.hasNext()) {
                    possibleBlackMovingPositions.add(currentIt.next());
                }
            }
        }

        // returns whether the position of the white king is covered by the set of possible black moving targets
        return possibleBlackMovingPositions.contains(whiteKingPos);
    }

}
