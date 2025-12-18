
public class CriticalAttack implements AttackStrategy {
    @Override
    public int calculateDamage(Character attacker, Character defender) {
        // Attaque critique avec chance de dégâts doublés
        int baseDamage = attacker.getDexterity() + Dice.roll(8);
        // 30% de chance de critique
        if (Dice.randomInt(1, 100) <= 30) {
            System.out.println("COUP CRITIQUE !");
            return baseDamage * 2;
        }
        return baseDamage;
    }
}
