import java.util.Iterator;

public class PawnPieceMoveRuleStrategy implements PieceMoveRuleStrategy {
    @Override
    public Iterator<BoardPosition> iterator(final BoardPosition from) {
        return new Iterator<BoardPosition>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (count <= 1) {
                    return true;
                }
                return false;
            }

            @Override
            public BoardPosition next() {
                if (count == 1) {
                    count++;
                    return BoardPosition.south(from);
                }
                count++;
                return BoardPosition.north(from);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
