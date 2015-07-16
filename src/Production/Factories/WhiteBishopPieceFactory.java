package Production.Factories;

import Production.Strategies.MoveRules.BishopNoPassMoveRuleStrategy;
import Production.Strategies.MoveRules.PieceMoveRuleStrategy;
import Production.Utility.Color;
import Production.Utility.GameConstants;

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
