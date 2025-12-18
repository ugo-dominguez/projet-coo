
public class AttackCommand implements GameAction {
    private Player player;
    private NPC target;

    public AttackCommand(Player player, NPC target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public void execute() {
        player.attack(target);
    }
}