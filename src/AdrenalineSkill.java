
public class AdrenalineSkill extends PassiveSkill {
    private static final double BUFF_PERCENT = 0.20;
    private boolean triggered = false;
    private boolean active = false;
    private int buffAmount = 0;

    @Override
    public String getName() {
        return "Adrenaline";
    }

    @Override
    public boolean isAllowed(Player player) {
        return player instanceof Assassin || player instanceof Archer;
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == EventType.DEAL_DAMAGE) {
            triggered = true;
            System.out.println(
                    event.getActor().getName() + " entre en état d'Adrénaline ! Force +20% pour le prochain tour.");
        } else if (event.getType() == EventType.START_TURN) {
            Character actor = event.getActor();

            // Remove previous buff
            if (active) {
                actor.decreaseForce(buffAmount);
                active = false;
                buffAmount = 0;
            }

            // Apply new buff
            if (triggered) {
                buffAmount = (int) (actor.getForce() * BUFF_PERCENT);
                actor.increaseForce(buffAmount);
                active = true;
                triggered = false;
            }
        } else if (event.getType() == EventType.END_COMBAT) {
            if (active) {
                event.getActor().decreaseForce(buffAmount);
                active = false;
                buffAmount = 0;
            }
            triggered = false;
        }
    }
}
