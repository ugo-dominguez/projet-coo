public class HorrorFantasticThemeFactory extends ThemeFactory {

    @Override
    public NPC createRandomEnemy() {
        int roll = Dice.randomInt(1, 100);
        NPC npc;

        if (roll <= 30) {
            npc = new NPC("Fantôme", 80, 10, 8, 15, 5, "Coup de peur");
            npc.attackStrategy = new MagicalAttack();
        } else if (roll <= 55) {
            npc = new NPC("Gremlins", 50, 5, 15, 8, 5, "Grignotage");
            npc.attackStrategy = new PhysicalAttack();
        } else if (roll <= 75) {
            npc = new NPC("Clown tueur", 40, 8, 10, 5, 20, "Carte coupante");
            npc.attackStrategy = new RangedAttack();
        } else if (roll <= 90) {
            npc = new NPC("Loup-garou", 25, 10, 15, 5, 1, "Morsure");
            npc.attackStrategy = new PhysicalAttack();
        } else {
            npc = new NPC("Lucastein", 30, 15, 12, 8, 4, "Queue de fer");
            npc.attackStrategy = new CriticalAttack();
        }

        return npc;
    }

    @Override
    public Item createRandomItem() {
        int roll = Dice.randomInt(1, 6);
        switch (roll) {
            case 1:
                return new Consumable("Potion de soin", 50, 0.5, new HealthEffect(30, 0));
            case 2:
                return new Consumable("Potion de force", 100, 0.5, new StatEffect("Force", 5, 3));
            case 3:
                return new Consumable("Potion de résistance", 100, 0.5, new StatEffect("Constitution", 10, 2));
            case 4:
                return new Consumable("Ail anti-monstres", 5, 0.1, new HealthEffect(10, 0));
            case 5:
                return new Consumable("Eau bénite", 10, 0.2, new HealthEffect(20, 0));
            case 6:
                return new Consumable("Croix de dieu", 15, 0.3, new HealthEffect(40, 0));
            default:
                return null;
        }
    }

    @Override
    public NPC createBoss() {
        NPC boss = new NPC("Dracula", 250, 20, 10, 15, 10, "Morsure");
        boss.attackStrategy = new PhysicalAttack();
        return boss;
    }

    @Override
    public LegendaryItem createLegendaryItem() {
        return new LegendaryItem(
                "Talisman Maudit",
                "Chasseur de Vampires",
                new PhysicalAttack(),
                "Ce talisman ancien pulse d'une énergie sombre et te lie aux forces occultes.\nTu deviens un Chasseur de Vampires en plus de ta classe actuelle.");
    }
}
