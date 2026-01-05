import java.util.List;

public abstract class Character {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int force;
    protected int dexterity;
    protected int constitution;
    protected int intelligence;
    protected AttackStrategy attackStrategy;
    protected java.util.List<GameObserver> observers = new java.util.ArrayList<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
        observer.onAdd(this);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
        observer.onRemove(this);
    }

    public void dispatchEvent(GameEvent event) {
        for (GameObserver observer : new java.util.ArrayList<>(observers)) {
            observer.onEvent(event);
        }
    }

    public <T extends GameObserver> T getSkill(Class<T> skillClass) {
        for (GameObserver observer : observers) {
            if (skillClass.isInstance(observer)) {
                return skillClass.cast(observer);
            }
        }
        return null;
    }

    public abstract void attack(Character target);

    public void heal(int amount) {
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    public void takeDamage(int amount) {
        takeDamage(amount, null);
    }

    public void takeDamage(int amount, Character attacker) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void increaseForce(int amount) {
        this.force += amount;
    }

    public void increaseDexterity(int amount) {
        this.dexterity += amount;
    }

    public void increaseConstitution(int amount) {
        this.constitution += amount;
    }

    public void increaseIntelligence(int amount) {
        this.intelligence += amount;
    }

    public void decreaseForce(int amount) {
        this.force -= amount;
    }

    public void decreaseDexterity(int amount) {
        this.dexterity -= amount;
    }

    public void decreaseConstitution(int amount) {
        this.constitution -= amount;
    }

    public void decreaseIntelligence(int amount) {
        this.intelligence -= amount;
    }

    // Getters
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getForce() {
        return force;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getName() {
        return name;
    }

    public List<GameObserver> getObservers() {
        return observers;
    }
}
