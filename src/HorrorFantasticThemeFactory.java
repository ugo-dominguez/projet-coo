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
        int roll = Dice.randomInt(1, 100);
        
        // 30% armes/armures, 40% consommables, 30% parchemins
        if (roll <= 15) {
            return createRandomWeapon();
        } else if (roll <= 30) {
            return createRandomArmor();
        } else if (roll <= 70) {
            return createRandomConsumable();
        } else {
            return createRandomSkillScroll();
        }
    }

    private Item createRandomWeapon() {
        int roll = Dice.randomInt(1, 4);
        switch (roll) {
            case 1:
                return new Weapon("Pieu en Argent", 70, 1.0, 6, 2, 0, 0, 
                    new PhysicalAttack(), "Physique");
            case 2:
                return new Weapon("Arbalète Sacrée", 85, 3.0, 0, 9, 0, 0, 
                    new RangedAttack(), "Distance");
            case 3:
                return new Weapon("Crucifix Béni", 80, 0.5, 0, 0, 0, 10, 
                    new MagicalAttack(), "Magique");
            case 4:
                return new Weapon("Lame Maudite", 90, 2.0, 4, 6, 0, 0, 
                    new CriticalAttack(), "Critique");
            default:
                return null;
        }
    }

    private Item createRandomArmor() {
        int roll = Dice.randomInt(1, 4);
        switch (roll) {
            case 1:
                return new Equipment("Cape de Vampire", 110, 2.0, 
                    EquipmentSlot.CHESTPLATE, 0, 8, 3, 0);
            case 2:
                return new Equipment("Masque Rituel", 90, 1.0, 
                    EquipmentSlot.HELMET, 0, 0, 2, 7);
            case 3:
                return new Equipment("Bottes Spectrales", 60, 0.5, 
                    EquipmentSlot.BOOTS, 0, 5, 0, 3);
            case 4:
                return new Equipment("Armure Maudite", 120, 18.0, 
                    EquipmentSlot.LEGGINGS, 3, -1, 9, 0);
            default:
                return null;
        }
    }

    private Item createRandomConsumable() {
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

    private Item createRandomSkillScroll() {
        int roll = Dice.randomInt(1, 2);
        switch (roll) {
            case 1:
                return new SkillScroll("Pacte de Sang (Régénération)", 180, 0.2, new RegenerationSkill(), 3);
            case 2:
                return new SkillScroll("Aura Terrifiante (Adrénaline)", 180, 0.0, new AdrenalineSkill(), 3);
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