public class FuturisticThemeFactory extends ThemeFactory {

    @Override
    public NPC createRandomEnemy() {
        int roll = Dice.randomInt(1, 100);
        NPC npc;

        if (roll <= 40) {
            npc = new NPC("Drone de Combat", 60, 5, 15, 5, 20, "Tir de missile");
            npc.attackStrategy = new RangedAttack();
        } else if (roll <= 70) {
            npc = new NPC("Soldat Cybernétique", 100, 12, 10, 10, 5, "Tir Plasma");
            npc.attackStrategy = new RangedAttack();
        } else {
            npc = new NPC("Alien Predateur", 80, 18, 15, 8, 2, "Griffes Acérées");
            npc.attackStrategy = new PhysicalAttack();
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
                return new Consumable("Barre Énergétique", 15, 0.2, new HealthEffect(20, 0));
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
        boss.attackStrategy = new MagicalAttack();
        return boss;
    }

    @Override
    public LegendaryItem createLegendaryItem() {
        return new LegendaryItem(
            "Implant Militaire",
            "Soldat d'Élite",
            new CriticalAttack(),
            "Cet implant réécrit tes réflexes et ton instinct de combat.\nTu acquiers les capacités d'un Soldat d'Élite en plus de ta classe actuelle."
        );
    }
}