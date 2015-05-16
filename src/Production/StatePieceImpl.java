package Production;

public class StatePieceImpl extends PieceImpl {

    private boolean _hasMoved;

    private String _pieceType;
    private Color _pieceColor;
    private PieceMoveRuleStrategy _pieceMoveRuleStrategy;
    private PieceFactory _pieceFactory;

    public StatePieceImpl(String pieceType, Color pieceColor, PieceMoveRuleStrategy pieceMoveRuleStrategy) {
        super(pieceType, pieceColor, pieceMoveRuleStrategy);

        _pieceType = pieceType;
        _pieceColor = pieceColor;
        _pieceMoveRuleStrategy = pieceMoveRuleStrategy;
        _pieceFactory = null;

        _hasMoved = false;
    }

    public StatePieceImpl(PieceFactory pieceFactory) {
        super(pieceFactory);

        _pieceType = null;
        _pieceColor = null;
        _pieceMoveRuleStrategy = null;
        _pieceFactory = pieceFactory;

        _hasMoved = false;
    }

    public void setHasMoved(boolean hasMovedValue) {
        _hasMoved = hasMovedValue;
    }

    public boolean hasMoved() {
        return _hasMoved;
    }

    public Object clone() {
        try {
            super.clone();
            if (_pieceFactory == null) {
                return new StatePieceImpl(_pieceType, _pieceColor, _pieceMoveRuleStrategy);
            } else {
                return new StatePieceImpl(_pieceFactory);
            }
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}