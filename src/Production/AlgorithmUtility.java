package Production;

import java.util.*;

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

    public static boolean isWhiteCheckMated(Game game, Map<BoardPosition, Piece> pieceMap) {

        /*
        -------------------------
        STEP 0: Initialize data structures for use throughout
        -------------------------
         */

        BoardPosition whiteKingPos = null;
        Piece whiteKingPiece = null;

        // find the positions of the white king
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            BoardPosition currPos = entry.getKey();
            Piece currPiece = entry.getValue();
            if (currPiece.getType().equals(GameConstants.KING)
                    && currPiece.getColor() == Color.WHITE) {
                whiteKingPos = currPos;
                whiteKingPiece = currPiece;
            }
        }

        Iterator<BoardPosition> it;

        Map<Piece, List<BoardPosition>> blackPieceMappings = new HashMap<Piece, List<BoardPosition>>();
        Map<Piece, List<BoardPosition>> whitePieceMappings = new HashMap<Piece, List<BoardPosition>>();

        // fill the above sets
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPos = entry.getKey();
            boolean currPieceIsWhite = currPiece.getColor().equals(Color.WHITE);
            ArrayList<BoardPosition> tempPosList = new ArrayList<BoardPosition>();
            it = currPiece.possibleMovingPositions(currPos, game, pieceMap);

            while (it.hasNext()) {
                tempPosList.add(it.next());
            }

            (currPieceIsWhite ? whitePieceMappings : blackPieceMappings).put(currPiece, tempPosList);
        }

        /*
        -------------------------
        STEP 1: Check if King can flee
        -------------------------
         */

        // remove white king temporarily to check that it is not blocking its own fleeing positions
        pieceMap.remove(whiteKingPos);

        Set<BoardPosition> black_CoveredPositions_noWhiteKing = new TreeSet<BoardPosition>();
        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPos = entry.getKey();
            if (currPiece.getColor().equals(Color.BLACK)) {
                it = currPiece.possibleMovingPositions(currPos, game, pieceMap);
                while (it.hasNext()) {
                    black_CoveredPositions_noWhiteKing.add(it.next());
                }
            }
        }
        // restore white king
        pieceMap.put(whiteKingPos, whiteKingPiece);

        // find the white king moves not covered by black, if any
        TreeSet<BoardPosition> temp_whiteKing_possibleMoves =
                new TreeSet<BoardPosition>(whitePieceMappings.get(whiteKingPiece));
        temp_whiteKing_possibleMoves.removeAll(black_CoveredPositions_noWhiteKing);

        // if white king has possible moves, return false with no further analysis
        if (!temp_whiteKing_possibleMoves.isEmpty()) { return false; }

        /*
        -------------------------
        STEP 2: Check if attacking the threatening piece(s) helps
        -------------------------
         */

        // check which pieces threatens the white king

        Set<Piece> black_threateningPieces = new HashSet<Piece>();
        Set<BoardPosition> black_threateningPiecePositions = new HashSet<BoardPosition>();

        for (Map.Entry<Piece, List<BoardPosition>> entry : blackPieceMappings.entrySet()) {
            Piece currPiece = entry.getKey();
            List<BoardPosition> currPiecePossibleMoves = entry.getValue();
            if (currPiecePossibleMoves.contains(whiteKingPos)) {
                black_threateningPieces.add(currPiece);
                black_threateningPiecePositions.add(game.getPositionOfPiece(currPiece));
            }
        }

        // check that white is still in check if the threatening black piece is attacked;
        for (Map.Entry<Piece, List<BoardPosition>> entry : whitePieceMappings.entrySet()) {
            Piece currWhitePiece = entry.getKey();
            List<BoardPosition> currWhitePiecePossMoves = entry.getValue();

            // Check if the current white piece can attack a black threatening piece
            ArrayList<BoardPosition> temp_blkThreatPos = new ArrayList<BoardPosition>(black_threateningPiecePositions);
            temp_blkThreatPos.retainAll(currWhitePiecePossMoves);
            if (!temp_blkThreatPos.isEmpty()) {

                // check if such an attack lets white avoid the black Check
                BoardPosition currWhitePiecePos = game.getPositionOfPiece(currWhitePiece);

                for (BoardPosition blackBP : temp_blkThreatPos) {
                    // simulate move and check if still in check; if not, return false as game move is valid
                    Map<BoardPosition, Piece> tempPieceMap = new HashMap<BoardPosition, Piece>(pieceMap);
                    tempPieceMap.put(blackBP, currWhitePiece);
                    tempPieceMap.remove(currWhitePiecePos);
                    if (!AlgorithmUtility.isCheck(game, tempPieceMap)) {
                        // white can avoid the check with an attack
                        return false;
                    }
                }
            }
        }

        /*
        -------------------------
        STEP 3: Check if interleaving an attacking piece helps
        -------------------------
         */

        // find all the possible moves of the threatening black pieces to try and intervene here
        Set<BoardPosition> black_PossMovesToCheck = new TreeSet<BoardPosition>();
        for (Piece blkThreateningPiece : black_threateningPieces) {
            black_PossMovesToCheck.addAll(blackPieceMappings.get(blkThreateningPiece));
        }

        for (Map.Entry<Piece, List<BoardPosition>> entry : whitePieceMappings.entrySet()) {
            Piece currPiece = entry.getKey();
            List<BoardPosition> currPiecePossMoves = entry.getValue();
            BoardPosition currPiecePos = game.getPositionOfPiece(currPiece);

            if (currPiece == whiteKingPiece) continue; // already determined that king cannot move

            // check which possible moves the current piece has in common with the black threatening piece(s),
            // and check if white is still in check when the current piece moves to each one of them
            Set<BoardPosition> tmp_currWhiteRelevantMoves = new HashSet<BoardPosition>(currPiecePossMoves);
            tmp_currWhiteRelevantMoves.retainAll(black_PossMovesToCheck);

            for (BoardPosition possMove : tmp_currWhiteRelevantMoves) {
                // create a temporary new instance of the game in which game possible move has happened
                // and check if white is still in check; return false if not, as mate can then be avoided
                Map<BoardPosition, Piece> tmp_pieceMap = new HashMap<BoardPosition, Piece>(pieceMap);
                tmp_pieceMap.put(possMove, currPiece);
                tmp_pieceMap.remove(currPiecePos);
                if (!AlgorithmUtility.isCheck(game, tmp_pieceMap)) {
                    // a white piece can successfully intervene the black attack and avoid check mate
                    return false;
                }
            }
        }

        // if this statement is reached, white is mated
        return true;

    }

}
