package Production;

import Production.Utility.BoardPosition;
import Production.Utility.Color;

import java.util.*;

public class AiBot {

    private Game _game;
    private Color _botColor;
    private int randomMoveAttemptCount;

    public AiBot(Game game, Color playerColor) {
        _game = game;
        _botColor = playerColor;
        randomMoveAttemptCount = 0;
    }

    public void act() throws Exception {
        // called whenever the bot is to make a move in game
        randomMove();
    }

    public void randomMove() throws Exception {

        int i = 200;
        if (randomMoveAttemptCount > i) {
            throw new Exception("Attempted to move randomly "+ i +" times without success; terminating");
        }

        Map<BoardPosition, List<BoardPosition>> possibleMoves = new HashMap<BoardPosition, List<BoardPosition>>();
        Map<BoardPosition, Piece> pieceMap = _game.getPieceMap();

        for (Map.Entry<BoardPosition, Piece> entry : pieceMap.entrySet()) {
            Piece currPiece = entry.getValue();
            BoardPosition currPiecePos = entry.getKey();
            if (currPiece.getColor().equals(_botColor)) {
                Iterator<BoardPosition> currPiecePossibleMoves = currPiece.possibleMovingPositions(currPiecePos, _game, pieceMap);
                if (currPiecePossibleMoves.hasNext()) {
                    List<BoardPosition> tmpList = new ArrayList<BoardPosition>();
                    while (currPiecePossibleMoves.hasNext()) {
                        tmpList.add(currPiecePossibleMoves.next());
                    }
                    possibleMoves.put(currPiecePos, tmpList);
                }
            }
        }

        // System.out.println("possible candidates for random moves: " + possibleMoves);

        // select random possible move for random piece
        Random rnd = new Random();
        List<BoardPosition> possibleFromPositionsList = new ArrayList<BoardPosition>(possibleMoves.keySet());
        int rnd_idx = rnd.nextInt(possibleFromPositionsList.size());
        BoardPosition randomFromPosition = possibleFromPositionsList.get(rnd_idx);

        List<BoardPosition> possibleToPositionsList = possibleMoves.get(randomFromPosition);
        int rnd_idxx = rnd.nextInt(possibleToPositionsList.size());
        BoardPosition randomToPosition = possibleToPositionsList.get(rnd_idxx);

        if (_game.movePiece(randomFromPosition, randomToPosition)) {
            randomMoveAttemptCount = 0;
        } else {
            randomMoveAttemptCount++;
            randomMove();
        }
    }
}
