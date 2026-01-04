
public class SkillScrollTest {

    // Mock classes for testing
    static class TestSkill extends PassiveSkill {
        @Override
        public String getName() {
            return "TestSkill";
        }

        @Override
        public void onEvent(GameEvent event) {
        }
    }

    static class TestPlayer extends Player {
        public TestPlayer(String name) {
            this.name = name;
            this.maxHealth = 100;
            this.health = 100;
            this.force = 10;
            this.dexterity = 10;
            this.constitution = 10;
            this.intelligence = 10;
        }
    }

    public static void main(String[] args) {
        testPermanentSkill();
        testTemporarySkill();
    }

    public static void testPermanentSkill() {
        System.out.println("Testing Permanent Skill...");
        TestPlayer player = new TestPlayer("Hero");
        TestSkill skill = new TestSkill();
        SkillScroll scroll = new SkillScroll("Scroll of Testing", 10, 1.0, skill);

        scroll.use(player);

        if (skill.getDuration() == -1) {
            System.out.println("SUCCESS: Skill has indefinite duration.");
        } else {
            System.out.println("FAILURE: Skill should have duration -1, got " + skill.getDuration());
        }
    }

    public static void testTemporarySkill() {
        System.out.println("Testing Temporary Skill...");
        TestPlayer player = new TestPlayer("Hero");
        TestSkill skill = new TestSkill();
        SkillScroll scroll = new SkillScroll("Scroll of Fading", 10, 1.0, skill, 3);

        scroll.use(player);

        if (skill.getDuration() == 3) {
            System.out.println("SUCCESS: Skill initialized with duration 3.");
        } else {
            System.out.println("FAILURE: Skill should have duration 3, got " + skill.getDuration());
        }

        // Simulate turns
        player.startTurn(); // Duration -> 2
        if (skill.getDuration() == 2)
            System.out.println("Turn 1 OK");
        else
            System.out.println("Turn 1 FAIL: " + skill.getDuration());

        player.startTurn(); // Duration -> 1
        if (skill.getDuration() == 1)
            System.out.println("Turn 2 OK");
        else
            System.out.println("Turn 2 FAIL: " + skill.getDuration());

        player.startTurn(); // Duration -> 0, should expire
        if (skill.getDuration() == 0)
            System.out.println("Turn 3 OK (Expired)");
        else
            System.out.println("Turn 3 FAIL: " + skill.getDuration());
    }
}
