public class SkillScroll extends Item {
    private PassiveSkill skill;

    public SkillScroll(String name, int value, double weight, PassiveSkill skill) {
        super(name, value, weight);
        this.skill = skill;
    }

    public SkillScroll(String name, int value, double weight, PassiveSkill skill, int duration) {
        this(name, value, weight, skill);
        this.skill.setDuration(duration);
    }

    public void use(Player player) {
        if (skill.isAllowed(player)) {
            PassiveSkill existingSkill = player.getSkill(skill.getClass());

            if (existingSkill != null) {
                if (skill.getDuration() == -1 || (existingSkill.getDuration() != -1 && skill.getDuration() > existingSkill.getDuration())) {
                    player.removeObserver(existingSkill);
                    player.addObserver(skill);
                    System.out.println(ConsoleColors.ANSI_GREEN + "Votre compétence " + skill.getName()
                            + " a été améliorée/rafraîchie !" + ConsoleColors.ANSI_RESET);
                } else {
                    System.out.println(ConsoleColors.ANSI_YELLOW + "Vous possédez déjà une version égale ou supérieure de "
                            + skill.getName() + ". Le parchemin est détruit." + ConsoleColors.ANSI_RESET);
                }
            } else {
                player.addObserver(skill);
                System.out.println(player.getName() + " apprend la compétence " + skill.getName() + " !");
            }
        } else {
            System.out.println(ConsoleColors.ANSI_RED + "Vous ne pouvez pas utiliser " + this.name
                    + " avec votre classe, celui-ci est détruit." + ConsoleColors.ANSI_RESET);
        }
    }

    public PassiveSkill getSkill() {
        return skill;
    }
}
