package Production;

import Production.Utility.BoardPosition;
import Production.Utility.Color;

import java.util.*;

public class AiBot {

    private Color _botColor;
    private int _randomMoveAttemptCount;
    private int _moveAttemptLimit;

    public AiBot(Color playerColor) {
        _botColor = playerColor;
        _randomMoveAttemptCount = 0;
        _moveAttemptLimit = 200;
    }

    public void act(Game argGame) throws Exception {
        // called whenever the bot is to make a move in game
        randomMove(argGame, 0);

    }

    private void randomMove(Game argGame, int cntAttempt) throws Exception {

        if (cntAttempt > _moveAttemptLimit) {
            throw new Exception("Attempted to move randomly "+ _randomMoveAttemptCount +" times without success; terminating");
        }

        Map<BoardPosition, List<BoardPosition>> possibleMoves = new HashMap<BoardPosition, List<BoardPosition>>();
        Map<BoardPosition, Piece> pieceMap = argGame.getPieceMap();

        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPiecePos = entry.getKey();
            if (currPiece.getColor().equals(_botColor)) {
                Iterator<BoardPosition> currPiecePossibleMoves = currPiece.possibleMovingPositions(currPiecePos, argGame, pieceMap);
                if (currPiecePossibleMoves.hasNext()) {
                    List<BoardPosition> tmpList = new ArrayList<BoardPosition>();
                    while (currPiecePossibleMoves.hasNext()) {
                        tmpList.add(currPiecePossibleMoves.next());
                    }
                    possibleMoves.put(currPiecePos, tmpList);
                }
            }
        }

        // select random possible move for random piece
        Random rnd = new Random();
        List<BoardPosition> possibleFromPositionsList = new ArrayList<BoardPosition>(possibleMoves.keySet());
        int rnd_idx = rnd.nextInt(possibleFromPositionsList.size());
        BoardPosition randomFromPosition = possibleFromPositionsList.get(rnd_idx);

        List<BoardPosition> possibleToPositionsList = possibleMoves.get(randomFromPosition);
        int rnd_idxx = rnd.nextInt(possibleToPositionsList.size());
        BoardPosition randomToPosition = possibleToPositionsList.get(rnd_idxx);

        if (!argGame.movePiece(randomFromPosition, randomToPosition)) {
            cntAttempt = cntAttempt+1;
            randomMove(argGame, cntAttempt);
        }
    }

}
