
public interface Effect {
    void apply(Player player);
    int getDuration();
    void decreaseDuration();
}
