
public class RangedAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Character attacker, Character defender) {
        // Attaque à distance basée sur la Dextérité
        return attacker.getDexterity() + Dice.roll(6);
    }
}