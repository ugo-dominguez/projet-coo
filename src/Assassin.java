
public class Assassin extends Player {
    public Assassin(String name) {
        this.name = name;
        this.maxHealth = 85;
        this.health = 85;
        this.force = 12;
        this.dexterity = 18;
        this.constitution = 6;
        this.intelligence = 8;
        this.attackStrategy = new CriticalAttack();
    }
}
