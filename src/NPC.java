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
            target.takeDamage(rawDamage);
        }
    }

    public String getSpecialAttackName() {
        return specialAttackName;
    }

    @Override
    public void takeDamage(int amount) {
        int reduction = this.constitution / 2;
        int damageTaken = Math.max(1, amount - reduction);
        this.health -= damageTaken;
        if (this.health < 0) {
            this.health = 0;
        }
    }
}
