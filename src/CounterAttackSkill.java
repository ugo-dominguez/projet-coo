
public class CounterAttackSkill implements PassiveSkill {
    private static final double RETURN_PERCENT = 0.20;

    @Override
    public String getName() {
        return "Counter-Attack";
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == EventType.TAKE_DAMAGE) {
            Character attacker = (Character) event.getData().get("attacker");
            if (attacker != null && attacker != event.getActor()) {
                int receivedDamage = (int) event.getData().get("damage");
                if (receivedDamage > 0) {
                    int returnDamage = (int) Math.max(1, receivedDamage * RETURN_PERCENT);
                    System.out.println(event.getActor().getName() + " riposte avec Contre-Attaque et inflige "
                            + returnDamage + " dégâts !");
                    attacker.takeDamage(returnDamage);
                }
            }
        }
    }
}
