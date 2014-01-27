public class WhiteBishopPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new BishopNoPassMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.BISHOP;
    }

    @Override
    public Color getPieceColor() {
        return Color.WHITE;
    }
}
