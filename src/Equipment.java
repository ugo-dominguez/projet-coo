public class Equipment extends Item {
    private EquipmentSlot slot;
    private int forceBonus;
    private int dexterityBonus;
    private int constitutionBonus;
    private int intelligenceBonus;

    public Equipment(String name, int value, double weight, EquipmentSlot slot, int force, int dexterity,
            int constitution, int intelligence) {
        super(name, value, weight);
        this.slot = slot;
        this.forceBonus = force;
        this.dexterityBonus = dexterity;
        this.constitutionBonus = constitution;
        this.intelligenceBonus = intelligence;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public int getForceBonus() {
        return forceBonus;
    }

    public int getDexterityBonus() {
        return dexterityBonus;
    }

    public int getConstitutionBonus() {
        return constitutionBonus;
    }

    public int getIntelligenceBonus() {
        return intelligenceBonus;
    }
}
