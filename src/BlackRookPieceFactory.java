public class BlackRookPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new RookNoPassMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.ROOK;
    }

    @Override
    public Color getPieceColor() {
        return Color.BLACK;
    }
}
