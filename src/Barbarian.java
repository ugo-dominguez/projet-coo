
public class Barbarian extends Player {
    public Barbarian(String name) {
        this.name = name;
        this.maxHealth = 120;
        this.health = 120;
        this.force = 20;
        this.dexterity = 5;
        this.constitution = 12;
        this.intelligence = 2;
        this.attackStrategy = new PhysicalAttack();
    }
}