
public class UseItemCommand implements GameAction {
    private Player player;
    private Consumable item;

    public UseItemCommand(Player player, Consumable item) {
        this.player = player;
        this.item = item;
    }

    @Override
    public void execute() {
        player.consumeItem(item);
    }
}
