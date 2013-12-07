package HotChess;

/**
 * Created by marc on 07/12/13.
 */
public class PieceImpl implements Piece {
    private final String _pieceType;
    private final Color _pieceColor;

    public PieceImpl(String pieceType, Color pieceColor) {
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
}
