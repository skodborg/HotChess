/**
 * Created by marc on 07/12/13.
 */
public interface MovingStrategy {
    boolean isMoveValid(Game game, BoardPosition from, BoardPosition to);
}
