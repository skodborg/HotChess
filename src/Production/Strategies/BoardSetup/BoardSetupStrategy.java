package Production.Strategies.BoardSetup;

import Production.Utility.BoardPosition;
import Production.Piece;

import java.util.Map;

public interface BoardSetupStrategy {

    void setupPieces(Map<BoardPosition, Piece> pieceMap);
}
