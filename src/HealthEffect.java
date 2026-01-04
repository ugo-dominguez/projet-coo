public class HealthEffect implements Effect {
    private int amount;
    private int duration;

    public HealthEffect(int amount, int duration) {
        this.amount = amount;
        this.duration = duration;
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == EventType.START_TURN && event.getActor() instanceof Character) {
            System.out.println("\n" + ConsoleColors.ANSI_GREEN + event.getActor().getName() + " a récupéré " + amount
                    + " points de vie !" + ConsoleColors.ANSI_RESET);
            event.getActor().heal(amount);
        }
    }

    @Override
    public void onAdd(Character owner) {
        if (duration == 0) {
            owner.heal(amount);
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
