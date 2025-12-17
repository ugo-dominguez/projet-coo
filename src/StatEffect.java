public class StatEffect implements Effect {
    private String statName;
    private int amount;
    private int duration;

    public StatEffect(String statName, int amount, int duration) {
        this.statName = statName;
        this.amount = amount;
        this.duration = duration;
    }

    @Override
    public void apply(Player player) {
        switch (statName.toLowerCase()) {
            case "dexterity":
                player.increaseDexterity(amount);
                break;
            case "force":
                player.increaseForce(amount);
                break;
            case "constitution":
                player.increaseConstitution(amount);
                break;
            case "intelligence":
                player.increaseIntelligence(amount);
                break;
        }
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
