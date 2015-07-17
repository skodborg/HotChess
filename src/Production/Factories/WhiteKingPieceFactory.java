package Production.Factories;

import Production.Strategies.MoveRules.KingMoveRuleStrategy;
import Production.Strategies.MoveRules.PieceMoveRuleStrategy;
import Production.Utility.Color;
import Production.Utility.GameConstants;

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
