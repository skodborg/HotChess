import java.util.Iterator;

public class NullPieceMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(BoardPosition from) {
        return null;
    }
}
