package Production.Factories;

import Production.Strategies.MoveRules.PieceMoveRuleStrategy;
import Production.Strategies.MoveRules.QueenNoPassMoveRuleStrategy;
import Production.Utility.Color;
import Production.Utility.GameConstants;

public class BlackQueenPieceFactory implements PieceFactory {
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
        return Color.BLACK;
    }
}
