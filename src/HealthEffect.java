public class HealthEffect implements Effect {
    private int amount;
    private int duration;

    public HealthEffect(int amount, int duration) {
        this.amount = amount;
        this.duration = duration;
    }

    @Override
    public void apply(Player player) {
        player.heal(amount);
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void decreaseDuration() {
        if (duration > 0) {
            duration--;
        }
    }
}
