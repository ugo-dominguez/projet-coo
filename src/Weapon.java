
public class Weapon extends Equipment {
    private AttackStrategy attackStrategy;
    private String attackType;

    public Weapon(String name, int value, double weight, 
                int force, int dexterity, int constitution, int intelligence,
                AttackStrategy attackStrategy, String attackType) {
        super(name, value, weight, EquipmentSlot.WEAPON, force, dexterity, constitution, intelligence);
        this.attackStrategy = attackStrategy;
        this.attackType = attackType;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public String getAttackType() {
        return attackType;
    }
}