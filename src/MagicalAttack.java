
public class MagicalAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Character attacker, Character defender) {
        // Attaque magique basée sur l'Intelligence
        return attacker.getIntelligence() + Dice.roll(8);
    }
}
