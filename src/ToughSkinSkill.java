
public class ToughSkinSkill extends PassiveSkill {
    private static final double REDUCTION_PERCENT = 0.10;

    @Override
    public String getName() {
        return "Tough Skin";
    }

    @Override
    public boolean isAllowed(Player player) {
        return player instanceof Barbarian || player instanceof Archer;
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == EventType.TAKE_DAMAGE) {
            int currentDamage = (int) event.getData().get("damage");
            int reduction = (int) (currentDamage * REDUCTION_PERCENT);
            if (reduction > 0) {
                event.getData().put("damage", currentDamage - reduction);
                System.out.println(event.getActor().getName() + " subit moins de dégâts grâce à Peau Dure !");
            }
        }
    }
}
