package Production.Factories;

import Production.Strategies.MoveRules.KnightMoveRuleStrategy;
import Production.Strategies.MoveRules.PieceMoveRuleStrategy;
import Production.Utility.Color;
import Production.Utility.GameConstants;

public class BlackKnightPieceFactory implements PieceFactory {
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
        return Color.BLACK;
    }
}
