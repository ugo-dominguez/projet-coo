import java.util.List;
import java.util.Map;

public abstract class Player extends Character {
    protected List<Item> inventory;
    protected Map<Effect, Integer> effects;

    public void attack(Character target) {
        if (attackStrategy != null) {
            int damage = attackStrategy.calculateDamage(this, target);
            target.takeDamage(damage);
            System.out.println(this.name + " attacks " + target.name + " for " + damage + " damage.");
        } else {
            System.out.println(this.name + " has no attack strategy!");
        }
    }

    private Map<EquipmentSlot, Equipment> equippedItems = new java.util.HashMap<>();

    {
        inventory = new java.util.ArrayList<>();
        effects = new java.util.HashMap<>();
    }

    public void equipItem(Equipment item) {
        Equipment current = equippedItems.get(item.getSlot());
        if (current != null) {
            unequipItem(current);
        }

        equippedItems.put(item.getSlot(), item);
        applyEquipmentStats(item, true);
        System.out.println("Equipped " + item.getName());
    }

    public void unequipItem(Equipment item) {
        if (equippedItems.get(item.getSlot()) == item) {
            equippedItems.remove(item.getSlot());
            applyEquipmentStats(item, false);
            System.out.println("Unequipped " + item.getName());
        }
    }

    private void applyEquipmentStats(Equipment item, boolean equip) {
        int multiplier = equip ? 1 : -1;
        this.increaseForce(item.getForceBonus() * multiplier);
        this.increaseDexterity(item.getDexterityBonus() * multiplier);
        this.increaseConstitution(item.getConstitutionBonus() * multiplier);
        this.increaseIntelligence(item.getIntelligenceBonus() * multiplier);
    }

    public void consumeItem(Consumable item) {
        item.use(this);
        inventory.remove(item);
        System.out.println("Consumed " + item.getName());
    }

    public void addEffect(Effect effect) {
        effects.put(effect, effect.getDuration());
        effect.apply(this);
    }

    public void startTurn() {
        java.util.Iterator<Map.Entry<Effect, Integer>> it = effects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Effect, Integer> entry = it.next();
            Effect effect = entry.getKey();

            if (effect instanceof HealthEffect) {
                effect.apply(this);
            }

            effect.decreaseDuration();
            entry.setValue(effect.getDuration());

            if (effect.getDuration() <= 0) {
                it.remove();
            }
        }
    }
}
