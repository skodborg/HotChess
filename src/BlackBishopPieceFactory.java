public class BlackBishopPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new BishopMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.BISHOP;
    }

    @Override
    public Color getPieceColor() {
        return Color.BLACK;
    }
}
