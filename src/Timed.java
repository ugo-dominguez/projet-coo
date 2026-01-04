
public interface Timed {
    int getDuration();

    default void decreaseDuration() {
    }

    default boolean isExpired() {
        return getDuration() == 0;
    }
}
