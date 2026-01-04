
public class RegenerationSkill implements PassiveSkill {
    private static final double REGEN_PERCENT = 0.05;

    @Override
    public String getName() {
        return "Regeneration";
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == EventType.START_TURN) {
            Character character = event.getActor();
            int amount = (int) Math.max(1, character.getMaxHealth() * REGEN_PERCENT);
            character.heal(amount);
            System.out.println(character.getName() + " récupère " + amount + " PV grâce à Régénération.");
        }
    }
}
