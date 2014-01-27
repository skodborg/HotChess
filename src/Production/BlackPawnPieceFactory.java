package Production;

public class BlackPawnPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new BlackPawnMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.PAWN;
    }

    @Override
    public Color getPieceColor() {
        return Color.BLACK;
    }
}
