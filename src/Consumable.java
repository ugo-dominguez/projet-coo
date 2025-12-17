public class Consumable extends Item {
    private Effect effect;

    public Consumable(String name, int value, double weight, Effect effect) {
        super(name, value, weight);
        this.effect = effect;
    }

    public void use(Player player) {
        if (effect != null) {
            if (effect.getDuration() == 0) {
                effect.apply(player);
            } else {
                player.addEffect(effect);
            }
        }
    }

    public Effect getEffect() {
        return effect;
    }
}
