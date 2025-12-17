
public abstract class Character {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int force;
    protected int dexterity;
    protected int constitution;
    protected int intelligence;
    protected AttackStrategy attackStrategy;

    public abstract void attack(Character target);

}
