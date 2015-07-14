package Production;

import java.util.*;

public class AlgorithmUtility {

    public static boolean isPlayerChecked(Game game,
                                          Map<BoardPosition, Piece> pieceMap,
                                          Color playerToCheckIfChecked) {

        Color friendlyColor = playerToCheckIfChecked;
        Color enemyColor = (friendlyColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
        BoardPosition friendlyKingPos = null;

        // find the positions of the friendly king
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            if (entry.getValue().getType().equals(GameConstants.KING)
                    && entry.getValue().getColor() == friendlyColor) {
                friendlyKingPos = entry.getKey();
            }
        }

        // for each enemy piece, save their possible moving positions
        TreeSet<BoardPosition> possibleEnemyMovingPositions = new TreeSet<BoardPosition>();
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            if (entry.getValue().getColor() == enemyColor) {
                Piece curr = entry.getValue();
                BoardPosition currPos = entry.getKey();
                Iterator<BoardPosition> currentIt = curr.possibleMovingPositions(currPos, game, pieceMap);
                while (currentIt.hasNext()) {
                    BoardPosition next = currentIt.next();
                    if (next != null) { // next might be null because of BoardPosition.north()
                        possibleEnemyMovingPositions.add(next);
                    }
                }
            }
        }

        // returns whether the position of the friendly king is covered by the set of possible enemy moving targets
        return possibleEnemyMovingPositions.contains(friendlyKingPos);
    }

    public static Color isPlayerCheckMated(Game game,
                                             Map<BoardPosition, Piece> pieceMap,
                                             Color playerToCheckIfMated) {

        /*
        -------------------------
        STEP 0: Initialize data structures for use throughout
        -------------------------
         */

        Color friendlyColor = playerToCheckIfMated;
        Color enemyColor = (friendlyColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

        BoardPosition friendlyKingPos = null;
        Piece friendlyKingPiece = null;

        // find the positions of the friendly king
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            BoardPosition currPos = entry.getKey();
            Piece currPiece = entry.getValue();
            if (currPiece.getType().equals(GameConstants.KING)
                    && currPiece.getColor() == friendlyColor) {
                friendlyKingPos = currPos;
                friendlyKingPiece = currPiece;
            }
        }

        Iterator<BoardPosition> it;

        Map<Piece, List<BoardPosition>> enemyPieceMappings = new HashMap<Piece, List<BoardPosition>>();
        Map<Piece, List<BoardPosition>> friendlyPieceMappings = new HashMap<Piece, List<BoardPosition>>();

        // fill the above sets
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPos = entry.getKey();
            boolean currPieceIsFriendly = currPiece.getColor().equals(friendlyColor);
            ArrayList<BoardPosition> tempPosList = new ArrayList<BoardPosition>();
            it = currPiece.possibleMovingPositions(currPos, game, pieceMap);

            while (it.hasNext()) {
                tempPosList.add(it.next());
            }

            (currPieceIsFriendly ? friendlyPieceMappings : enemyPieceMappings).put(currPiece, tempPosList);
        }

        /*
        -------------------------
        STEP 1: Check if King can flee
        -------------------------
         */

        // remove friendly king temporarily to check that it is not blocking its own fleeing positions
        pieceMap.remove(friendlyKingPos);

        Set<BoardPosition> enemy_CoveredPositions_noFriendlyKing = new TreeSet<BoardPosition>();
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPos = entry.getKey();
            if (currPiece.getColor().equals(enemyColor)) {
                it = currPiece.possibleMovingPositions(currPos, game, pieceMap);
                while (it.hasNext()) {
                    enemy_CoveredPositions_noFriendlyKing.add(it.next());
                }
            }
        }
        // restore friendly king
        pieceMap.put(friendlyKingPos, friendlyKingPiece);

        // find the friendly king moves not covered by enemy, if any
        TreeSet<BoardPosition> temp_friendlyKing_possibleMoves =
                new TreeSet<BoardPosition>(friendlyPieceMappings.get(friendlyKingPiece));
        temp_friendlyKing_possibleMoves.removeAll(enemy_CoveredPositions_noFriendlyKing);

        // if friendly king has possible moves, return Color.NONE with no further analysis
        if (!temp_friendlyKing_possibleMoves.isEmpty()) { return Color.NONE; }

        /*
        -------------------------
        STEP 2: Check if attacking the threatening piece(s) helps
        -------------------------
         */

        // check which pieces threatens the friendly king

        Set<Piece> enemy_threateningPieces = new HashSet<Piece>();
        Set<BoardPosition> enemy_threateningPiecePositions = new HashSet<BoardPosition>();

        for (Map.Entry<Piece, List<BoardPosition>> entry : enemyPieceMappings.entrySet()) {
            Piece currPiece = entry.getKey();
            List<BoardPosition> currPiecePossibleMoves = entry.getValue();
            if (currPiecePossibleMoves.contains(friendlyKingPos)) {
                enemy_threateningPieces.add(currPiece);
                enemy_threateningPiecePositions.add(game.getPositionOfPiece(currPiece));
            }
        }

        // check that friendly player is still in check if the threatening enemy piece is attacked;
        for (Map.Entry<Piece, List<BoardPosition>> entry : friendlyPieceMappings.entrySet()) {
            Piece currFriendlyPiece = entry.getKey();
            List<BoardPosition> currFriendlyPiecePossMoves = entry.getValue();

            // Check if the current friendly piece can attack a enemy threatening piece
            ArrayList<BoardPosition> temp_enemyThreatPos = new ArrayList<BoardPosition>(enemy_threateningPiecePositions);
            temp_enemyThreatPos.retainAll(currFriendlyPiecePossMoves);
            if (!temp_enemyThreatPos.isEmpty()) {

                // check if such an attack lets friendly player avoid the enemy Check
                BoardPosition currFriendlyPiecePos = game.getPositionOfPiece(currFriendlyPiece);

                for (BoardPosition enemyBP : temp_enemyThreatPos) {
                    // simulate move and check if still in check; if not, return false as game move is valid
                    Map<BoardPosition, Piece> tempPieceMap = new HashMap<BoardPosition, Piece>(pieceMap);
                    tempPieceMap.put(enemyBP, currFriendlyPiece);
                    tempPieceMap.remove(currFriendlyPiecePos);
                    if (!AlgorithmUtility.isPlayerChecked(game, tempPieceMap, friendlyColor)) {
                        // friendly player can avoid the check with an attack
                        return Color.NONE;
                    }
                }
            }
        }

        /*
        -------------------------
        STEP 3: Check if interleaving an attacking piece helps
        -------------------------
         */

        // find all the possible moves of the threatening enemy pieces to try and intervene here
        Set<BoardPosition> enemy_PossMovesToCheck = new TreeSet<BoardPosition>();
        for (Piece enemy_ThreateningPiece : enemy_threateningPieces) {
            enemy_PossMovesToCheck.addAll(enemyPieceMappings.get(enemy_ThreateningPiece));
        }

        for (Map.Entry<Piece, List<BoardPosition>> entry : friendlyPieceMappings.entrySet()) {
            Piece currPiece = entry.getKey();
            List<BoardPosition> currPiecePossMoves = entry.getValue();
            BoardPosition currPiecePos = game.getPositionOfPiece(currPiece);

            if (currPiece == friendlyKingPiece) continue; // already determined that king cannot move

            // check which possible moves the current piece has in common with the enemy threatening piece(s),
            // and check if friendly player is still in Check when the current piece moves to each one of them
            Set<BoardPosition> tmp_currFriendlyRelevantMoves = new HashSet<BoardPosition>(currPiecePossMoves);
            tmp_currFriendlyRelevantMoves.retainAll(enemy_PossMovesToCheck);

            for (BoardPosition possMove : tmp_currFriendlyRelevantMoves) {
                // create a temporary new instance of the game in which game possible move has happened
                // and check if friendly player is still in Check; return false if not, as mate can then be avoided
                Map<BoardPosition, Piece> tmp_pieceMap = new HashMap<BoardPosition, Piece>(pieceMap);
                tmp_pieceMap.put(possMove, currPiece);
                tmp_pieceMap.remove(currPiecePos);
                if (!AlgorithmUtility.isPlayerChecked(game, tmp_pieceMap, friendlyColor)) {
                    // a friendly piece can successfully intervene the enemy attack and avoid check mate
                    return Color.NONE;
                }
            }
        }

        // if this statement is reached, friendly player is mated
        return friendlyColor;

    }

}
