
public class WarCrySkill implements PassiveSkill {
    private static final double STR_PERCENT = 0.03;
    private static final double CON_PERCENT = 0.10;

    private boolean active = false;
    private int strBuff = 0;
    private int conBuff = 0;

    @Override
    public String getName() {
        return "War Cry";
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == EventType.START_COMBAT) {
            if (!active) {
                Character actor = event.getActor();
                strBuff = (int) Math.max(1, actor.getForce() * STR_PERCENT);
                conBuff = (int) Math.max(1, actor.getConstitution() * CON_PERCENT);

                actor.increaseForce(strBuff);
                actor.increaseConstitution(conBuff);
                active = true;
                System.out.println(
                        actor.getName() + " utilise Cri de Guerre ! Force et Constitution augmentées pour ce combat.");
            }
        } else if (event.getType() == EventType.END_COMBAT) {
            if (active) {
                Character actor = event.getActor();
                actor.decreaseForce(strBuff);
                actor.decreaseConstitution(conBuff);
                active = false;
                strBuff = 0;
                conBuff = 0;
            }
        }
    }
}
