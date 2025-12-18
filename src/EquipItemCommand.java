
public class EquipItemCommand implements GameAction {
    private Player player;
    private Equipment item;

    public EquipItemCommand(Player player, Equipment item) {
        this.player = player;
        this.item = item;
    }

    @Override
    public void execute() {
        player.equipItem(item);
    }
}