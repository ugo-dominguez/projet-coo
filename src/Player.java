import java.util.List;
import java.util.Map;

public abstract class Player extends Character {
    protected List<Item> inventory;
    protected Map<Effect, Integer> effects;

    public void attack(Character target) {
        if (attackStrategy != null) {
            int damage = attackStrategy.calculateDamage(this, target);

            // Emit DEAL_DAMAGE
            java.util.Map<String, Object> data = new java.util.HashMap<>();
            data.put("damage", damage);
            data.put("target", target);
            this.dispatchEvent(new GameEvent(EventType.DEAL_DAMAGE, this, data));

            target.takeDamage(damage, this);
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
        System.out.println("Equipé " + item.getName());

        // Emit CHANGE_WEAPON
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("item", item);
        this.dispatchEvent(new GameEvent(EventType.CHANGE_WEAPON, this, data));
    }

    public void unequipItem(Equipment item) {
        if (equippedItems.get(item.getSlot()) == item) {
            equippedItems.remove(item.getSlot());
            applyEquipmentStats(item, false);
            System.out.println("Déséquipé " + item.getName());
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
    public void takeDamage(int amount, Character attacker) {
        int reduction = (this.dexterity + this.constitution) / 4;
        int damageTaken = Math.max(1, amount - reduction);

        // Emit TAKE_DAMAGE and allow modification
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("damage", damageTaken);
        data.put("attacker", attacker);
        this.dispatchEvent(new GameEvent(EventType.TAKE_DAMAGE, this, data));

        damageTaken = (int) data.get("damage");

        this.health -= damageTaken;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println(this.name + " encaisse " + reduction + " dégats, mais en subit " + damageTaken);
    }

    public void consumeItem(Consumable item) {
        item.use(this);
        inventory.remove(item);
        System.out.println("");
        System.out.println(item.getName() + " consommé !");

        // Emit USE_ITEM
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("item", item);
        this.dispatchEvent(new GameEvent(EventType.USE_ITEM, this, data));
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

        // Emit START_TURN
        this.dispatchEvent(new GameEvent(EventType.START_TURN, this, new java.util.HashMap<>()));
    }
}
