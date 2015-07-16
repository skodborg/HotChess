package Production.Factories;

import Production.Strategies.MoveRules.PieceMoveRuleStrategy;
import Production.Strategies.MoveRules.WhitePawnMoveRuleStrategy;
import Production.Utility.Color;
import Production.Utility.GameConstants;

public class WhitePawnPieceFactory implements PieceFactory {
    @Override
    public PieceMoveRuleStrategy getMoveRuleStrategy() {
        return new WhitePawnMoveRuleStrategy();
    }

    @Override
    public String getPieceType() {
        return GameConstants.PAWN;
    }

    @Override
    public Color getPieceColor() {
        return Color.WHITE;
    }
}
