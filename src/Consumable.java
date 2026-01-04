public class Consumable extends Item {
    private Effect effect;

    public Consumable(String name, int value, double weight, Effect effect) {
        super(name, value, weight);
        this.effect = effect;
    }

    public void use(Player player) {
        if (effect != null) {
            player.addObserver(effect);
        }
    }

    public Effect getEffect() {
        return effect;
    }
}
