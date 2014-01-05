import java.util.Map;

/**
 * Created by marc on 07/12/13.
 */
public interface BoardSetupStrategy {

    void setupPieces(Map<Position, Piece> pieceMap);
}
