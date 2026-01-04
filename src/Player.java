import java.util.List;
import java.util.Map;

public abstract class Player extends Character {
    protected List<Item> inventory = new java.util.ArrayList<>();

    private Map<EquipmentSlot, Equipment> equippedItems = new java.util.HashMap<>();

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
        addObserver(effect);
    }

    public void startTurn() {
        // Dispatch START_TURN event
        this.dispatchEvent(new GameEvent(EventType.START_TURN, this, new java.util.HashMap<>()));

        // Handle duration updates and expiration
        // Using a copy to avoid concurrent modification issues during iteration
        for (GameObserver observer : new java.util.ArrayList<>(observers)) {
            if (observer instanceof Timed) {
                Timed timed = (Timed) observer;
                if (timed.getDuration() != -1) {
                    timed.decreaseDuration();

                    if (timed.isExpired()) {
                        removeObserver(observer);
                    }
                }
            }
        }
    }
}
