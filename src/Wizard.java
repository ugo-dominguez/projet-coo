
public class Wizard extends Player {
    public Wizard(String name) {
        this.name = name;
        this.maxHealth = 80;
        this.health = 80;
        this.force = 4;
        this.dexterity = 8;
        this.constitution = 6;
        this.intelligence = 25;
        this.attackStrategy = new MagicalAttack();
    }
}
