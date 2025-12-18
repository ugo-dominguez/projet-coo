public class FuturisticThemeFactory extends ThemeFactory {

    @Override
    public NPC createRandomEnemy() {
        int roll = Dice.randomInt(1, 100);
        NPC npc;

        // Futuristic Enemies
        if (roll <= 40) {
            npc = new NPC("Drone de Combat", 60, 5, 15, 5, 20, "Tir de missile");
            npc.attackStrategy = (attacker, defender) -> attacker.getDexterity() + 2;
        } else if (roll <= 70) {
            npc = new NPC("Soldat Cybernétique", 100, 12, 10, 10, 5, "Tir Plasma");
            npc.attackStrategy = (attacker, defender) -> attacker.getDexterity() + 4;
        } else {
            npc = new NPC("Alien Predateur", 80, 18, 15, 8, 2, "Griffes Acérées");
            npc.attackStrategy = (attacker, defender) -> attacker.getForce() + 5;
        }

        return npc;
    }

    @Override
    public Item createRandomItem() {
        int roll = Dice.randomInt(1, 4);
        switch (roll) {
            case 1:
                return new Consumable("Nano-Kit de soin", 40, 0.5, new HealthEffect(40, 0));
            case 2:
                return new Consumable("Barre Énergétique", 15, 0.2, new HealthEffect(15, 0));
            case 3:
                return new Consumable("Stimulant Force", 50, 0.1, new StatEffect("Force", 5, 0));
            case 4:
                return new Consumable("Bouclier Portatif", 100, 2.0, new StatEffect("Constitution", 5, 3));
            default:
                return null;
        }
    }

    @Override
    public NPC createBoss() {
        NPC boss = new NPC("Cyber-Overlord", 250, 30, 20, 30, 30, "Annihilation Laser");
        boss.attackStrategy = (attacker, defender) -> attacker.getIntelligence() + Dice.roll(10) + 10;
        return boss;
    }
}
