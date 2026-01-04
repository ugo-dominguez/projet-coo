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
    public void onEvent(GameEvent event) {
    }

    @Override
    public void onAdd(Character character) {
        applyStats(character, 1);
    }

    @Override
    public void onRemove(Character character) {
        applyStats(character, -1);
    }

    private void applyStats(Character character, int multiplier) {
        System.out.println("\n" + ConsoleColors.ANSI_GREEN + character.getName() + "gagne " + amount * multiplier
                + " de " + statName + " !" + ConsoleColors.ANSI_RESET);

        switch (statName.toLowerCase()) {
            case "dexterity":
                character.increaseDexterity(amount * multiplier);
                break;
            case "force":
                character.increaseForce(amount * multiplier);
                break;
            case "constitution":
                character.increaseConstitution(amount * multiplier);
                break;
            case "intelligence":
                character.increaseIntelligence(amount * multiplier);
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
