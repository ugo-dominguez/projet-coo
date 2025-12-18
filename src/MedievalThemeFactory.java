public class MedievalThemeFactory extends ThemeFactory {

    @Override
    public NPC createRandomEnemy() {
        int roll = Dice.randomInt(1, 100);
        NPC npc;
        
        if (roll <= 30) {
            npc = new NPC("Chevalier Errant", 120, 15, 8, 12, 5, "Coup d'épée");
            npc.attackStrategy = new PhysicalAttack();
        } else if (roll <= 55) {
            npc = new NPC("Sorcière", 70, 5, 10, 8, 20, "Sort de feu");
            npc.attackStrategy = new MagicalAttack();
        } else if (roll <= 75) {
            npc = new NPC("Vautour", 50, 8, 20, 5, 2, "Attaque plongeante");
            npc.attackStrategy = new RangedAttack();
        } else if (roll <= 90) {
            npc = new NPC("Rat Enragé", 30, 4, 15, 4, 1, "Morsure infectieuse");
            npc.attackStrategy = new PhysicalAttack();
        } else {
            npc = new NPC("Gobelin", 60, 8, 12, 8, 4, "Coup de dague");
            npc.attackStrategy = new CriticalAttack();
        }
        
        return npc;
    }

    @Override
    public Item createRandomItem() {
        // Items: Potions (Health, Force, Resistance), Food (Apple, Bread, Meat)
        int roll = Dice.randomInt(1, 6);
        switch (roll) {
            case 1:
                return new Consumable("Potion de soin", 50, 0.5, new HealthEffect(30, 0));
            case 2:
                return new Consumable("Potion de force", 100, 0.5, new StatEffect("Force", 5, 3));
            case 3:
                return new Consumable("Potion de résistance", 100, 0.5, new StatEffect("Constitution", 10, 2));
            case 4:
                return new Consumable("Pomme", 5, 0.1, new HealthEffect(10, 0));
            case 5:
                return new Consumable("Pain", 10, 0.2, new HealthEffect(15, 0));
            case 6:
                return new Consumable("Viande séchée", 15, 0.3, new HealthEffect(20, 0));
            default:
                return null;
        }
    }

    @Override
    public NPC createBoss() {
        NPC boss = new NPC("Dragon", 300, 25, 10, 20, 15, "Souffle de feu");
        boss.attackStrategy = new MagicalAttack();
        return boss;
    }
}
