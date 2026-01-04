public class LegendaryItem extends Item {
    private String grantedClassName;
    private AttackStrategy grantedAttackStrategy;
    private String flavorText;

    public LegendaryItem(String name, String grantedClassName, AttackStrategy grantedAttackStrategy, String flavorText) {
        super(name, 500, 2.0);
        this.grantedClassName = grantedClassName;
        this.grantedAttackStrategy = grantedAttackStrategy;
        this.flavorText = flavorText;
    }

    public String getGrantedClassName() {
        return grantedClassName;
    }

    public AttackStrategy getGrantedAttackStrategy() {
        return grantedAttackStrategy;
    }

    public String getFlavorText() {
        return flavorText;
    }
}