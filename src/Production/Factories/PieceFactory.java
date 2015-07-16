package Production.Factories;

import Production.Strategies.MoveRules.PieceMoveRuleStrategy;
import Production.Utility.Color;

public interface PieceFactory {

    /*
    returns a PieceMoveRuleStrategy to determine the movement strategy of the piece
     */
    PieceMoveRuleStrategy getMoveRuleStrategy();

    /*
    returns a GameConstant String determining the type of the Piece
     */
    String getPieceType();

    /*
    returns a Color enum determining the Color of the Piece
     */
    Color getPieceColor();
}
