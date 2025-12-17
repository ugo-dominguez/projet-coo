import java.util.List;
import java.util.Map;

public abstract class Player extends Character {
    protected List<Item> inventory;
    protected Map<Effect, Integer> effects;

    public void attack(Character target) {
    }

    private Map<EquipmentSlot, Equipment> equippedItems;

    public void equipItem(Equipment item) {
    }

    public void consumeItem(Consumable item) {
    }

}
