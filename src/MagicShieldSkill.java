
public class MagicShieldSkill extends PassiveSkill {
    private boolean active = true;

    @Override
    public String getName() {
        return "Magic Shield";
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == EventType.START_COMBAT || event.getType() == EventType.END_COMBAT) {
            active = true;
        } else if (event.getType() == EventType.TAKE_DAMAGE) {
            if (active) {
                event.getData().put("damage", 0);
                active = false;
                System.out.println("Le Bouclier Magique de " + event.getActor().getName() + " bloque les dégâts !");
            }
        }
    }
}
