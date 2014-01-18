public class WhiteKingPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new KingMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.KING;
    }

    @Override
    public Color getPieceColor() {
        return Color.WHITE;
    }
}
