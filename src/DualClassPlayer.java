public class DualClassPlayer extends Player {
    private Player basePlayer;
    private String secondClassName;
    private AttackStrategy secondClassAttackStrategy;
    private boolean usingSecondClass;

    public DualClassPlayer(Player basePlayer, String secondClassName, AttackStrategy secondClassAttackStrategy) {
        this.basePlayer = basePlayer;
        this.secondClassName = secondClassName;
        this.secondClassAttackStrategy = secondClassAttackStrategy;
        this.usingSecondClass = false;
        this.name = basePlayer.name;
        this.maxHealth = basePlayer.maxHealth;
        this.health = basePlayer.health;
        this.force = basePlayer.force;
        this.dexterity = basePlayer.dexterity;
        this.constitution = basePlayer.constitution;
        this.intelligence = basePlayer.intelligence;
        this.attackStrategy = basePlayer.attackStrategy;
        this.inventory = basePlayer.inventory;
        this.effects = basePlayer.effects;
    }

    public void switchToSecondClass() {
        this.attackStrategy = secondClassAttackStrategy;
        this.usingSecondClass = true;
    }

    public void switchToFirstClass() {
        this.attackStrategy = basePlayer.attackStrategy;
        this.usingSecondClass = false;
    }

    public String getCurrentClassName() {
        return usingSecondClass ? secondClassName : getBaseClassName();
    }

    public String getBaseClassName() {
        return basePlayer.getClass().getSimpleName();
    }

    public String getSecondClassName() {
        return secondClassName;
    }

    @Override
    public void attack(Character target) {
        if (attackStrategy != null) {
            int damage = attackStrategy.calculateDamage(this, target);
            String attackType = usingSecondClass ? secondClassName : getBaseClassName();
            System.out.println(this.name + " utilise l'attaque de " + attackType + " !");
            target.takeDamage(damage);
        } else {
            System.out.println(this.name + " n'a pas de stratégie d'attaque !");
        }
    }
}