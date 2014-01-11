import java.util.Iterator;

/**
 * Created by marc on 07/12/13.
 */
public class PieceImpl implements Piece {
    private final String _pieceType;
    private final Color _pieceColor;
    private PieceMoveRuleStrategy _pieceMoveRuleStrategy;

    public PieceImpl(String pieceType, Color pieceColor, PieceMoveRuleStrategy pieceMoveRuleStrategy) {
        _pieceMoveRuleStrategy = pieceMoveRuleStrategy;
        _pieceType = pieceType;
        _pieceColor = pieceColor;
    }

    @Override
    public Color getColor() {
        return _pieceColor;
    }

    @Override
    public String getType() {
        return _pieceType;
    }

    @Override
    public Iterator<BoardPosition> possibleMovingPositions(BoardPosition from) {
        return _pieceMoveRuleStrategy.iterator(from);
    }
}
