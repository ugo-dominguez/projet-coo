
public interface PassiveSkill extends GameObserver, Timed {
    String getName();

    @Override
    default int getDuration() {
        return -1; // Infinite by default
    }
}
