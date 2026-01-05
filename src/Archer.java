
public class Archer extends Player {
    public Archer(String name) {
        this.name = name;
        this.maxHealth = 90;
        this.health = 90;
        this.force = 10;
        this.dexterity = 20;
        this.constitution = 8;
        this.intelligence = 5;
        this.attackStrategy = new RangedAttack();
        this.initializeBaseStrategy();
    }
}
