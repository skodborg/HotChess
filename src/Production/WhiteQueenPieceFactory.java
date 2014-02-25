package Production;

public class WhiteQueenPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new QueenNoPassMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.QUEEN;
    }

    @Override
    public Color getPieceColor() {
        return Color.WHITE;
    }
}
