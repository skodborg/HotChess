package Production.Factories;

import Production.Strategies.MoveRules.BlackPawnMoveRuleStrategy;
import Production.Strategies.MoveRules.PieceMoveRuleStrategy;
import Production.Utility.Color;
import Production.Utility.GameConstants;

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
