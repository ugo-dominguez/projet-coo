
public class PhysicalAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Character attacker, Character defender) {
        // Attaque physique basée sur la Force
        return attacker.getForce() + Dice.roll(6);
    }
}