
public interface GameObserver {
    void onEvent(GameEvent event);

    default void onAdd(Character owner) {
    }

    default void onRemove(Character owner) {
    }
}
