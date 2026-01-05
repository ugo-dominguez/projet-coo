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
        int roll = Dice.randomInt(1, 100);
        
        // 40% armes/armures, 40% consommables, 20% parchemins
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
                return new Weapon("Fusil Plasma", 95, 4.0, 0, 10, 0, 0, 
                    new RangedAttack(), "Distance");
            case 2:
                return new Weapon("Lame Énergétique", 100, 2.0, 7, 3, 0, 0, 
                    new PhysicalAttack(), "Physique");
            case 3:
                return new Weapon("Pistolet Neural", 90, 1.5, 0, 0, 0, 9, 
                    new MagicalAttack(), "Magique");
            case 4:
                return new Weapon("Fusil de Précision", 85, 3.5, 2, 8, 0, 0, 
                    new CriticalAttack(), "Critique");
            default:
                return null;
        }
    }

    private Item createRandomArmor() {
        int roll = Dice.randomInt(1, 4);
        switch (roll) {
            case 1:
                return new Equipment("Exosquelette", 150, 20.0, 
                    EquipmentSlot.CHESTPLATE, 5, 0, 12, 0);
            case 2:
                return new Equipment("Implant Cérébral", 120, 0.1, 
                    EquipmentSlot.HELMET, 0, 2, 0, 8);
            case 3:
                return new Equipment("Bottes à Propulsion", 80, 2.0, 
                    EquipmentSlot.BOOTS, 0, 6, 1, 0);
            case 4:
                return new Equipment("Bouclier Énergétique Portable", 110, 3.0, 
                    EquipmentSlot.LEGGINGS, 0, 0, 8, 2);
            default:
                return null;
        }
    }

    private Item createRandomConsumable() {
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

    private Item createRandomSkillScroll() {
        int roll = Dice.randomInt(1, 3);
        switch (roll) {
            case 1:
                return new SkillScroll("Puce d'Adrénaline", 200, 0.1, new AdrenalineSkill(), 3);
            case 2:
                return new SkillScroll("Générateur de Bouclier", 250, 2.0, new MagicShieldSkill(), 3);
            case 3:
                return new SkillScroll("Nanobots de Régénération", 200, 0.1, new RegenerationSkill(), 3);
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
                "Cet implant réécrit tes réflexes et ton instinct de combat.\nTu acquiers les capacités d'un Soldat d'Élite en plus de ta classe actuelle.");
    }
}