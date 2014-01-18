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

    public PieceImpl(PieceFactory pieceFactory) {
        _pieceMoveRuleStrategy = pieceFactory.getMoveRuleStrategy();
        _pieceType = pieceFactory.getPieceType();
        _pieceColor = pieceFactory.getPieceColor();
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
    public String toString() {
        return _pieceColor + " " + _pieceType;
    }

    @Override
    public Iterator<BoardPosition> possibleMovingPositions(BoardPosition from) {
        return _pieceMoveRuleStrategy.iterator(from);
    }
}
