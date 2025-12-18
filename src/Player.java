import java.util.List;
import java.util.Map;

public abstract class Player extends Character {
    protected List<Item> inventory;
    protected Map<Effect, Integer> effects;

    public void attack(Character target) {
        if (attackStrategy != null) {
            int damage = attackStrategy.calculateDamage(this, target);
            target.takeDamage(damage);
        } else {
            System.out.println(this.name + "Pas de strategie d'attaque !");
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

    @Override
    public void takeDamage(int amount) {
        int reduction = (this.dexterity + this.constitution) / 4;
        int damageTaken = Math.max(1, amount - reduction);
        this.health -= damageTaken;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println(this.name + " subit " + damageTaken + " dégâts (Réduction: " + reduction + ")");
    }

    public void consumeItem(Consumable item) {
        item.use(this);
        inventory.remove(item);
        System.out.println("");
        System.out.println(item.getName() + " consommé !");
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
