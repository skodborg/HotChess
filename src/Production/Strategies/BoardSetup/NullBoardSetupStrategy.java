package Production.Strategies.BoardSetup;

import Production.Utility.BoardPosition;
import Production.Piece;

import java.util.Map;

public class NullBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupPieces(Map<BoardPosition, Piece> pieceMap) {
        // do nothing
    }
}
