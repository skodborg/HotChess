package Production;

public class WhiteKnightPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new KnightMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.KNIGHT;
    }

    @Override
    public Color getPieceColor() {
        return Color.WHITE;
    }
}
