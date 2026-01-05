public class NPC extends Character {
    private String specialAttackName;

    public NPC(String name, int health, int force, int dexterity, int constitution, int intelligence,
            String specialAttackName) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.force = force;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.specialAttackName = specialAttackName;
    }

    public void attack(Character target) {
        if (attackStrategy != null) {
            int rawDamage = attackStrategy.calculateDamage(this, target);
            System.out.println(this.name + " utilise " + specialAttackName + " sur " + target.getName() + " !");

            java.util.Map<String, Object> data = new java.util.HashMap<>();
            data.put("damage", rawDamage);
            data.put("target", target);
            this.dispatchEvent(new GameEvent(EventType.DEAL_DAMAGE, this, data));

            target.takeDamage(rawDamage, this);
        }
    }

    public String getSpecialAttackName() {
        return specialAttackName;
    }

    @Override
    public void takeDamage(int amount, Character attacker) {
        int reduction = this.constitution / 2;
        int damageTaken = Math.max(1, amount - reduction);

        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("damage", damageTaken);
        data.put("attacker", attacker);
        this.dispatchEvent(new GameEvent(EventType.TAKE_DAMAGE, this, data));

        damageTaken = (int) data.get("damage");

        this.health -= damageTaken;
        if (this.health < 0) {
            this.health = 0;
        }
    }
}
