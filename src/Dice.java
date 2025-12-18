import java.util.Random;

public class Dice {
    private static Random random = new Random();

    public static int roll(int faces) {
        return random.nextInt(faces) + 1;
    }

    public static int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
