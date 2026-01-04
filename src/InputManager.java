import java.util.Scanner;

public class InputManager {
    private Scanner scanner;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getInput(int min, int max) {
        while (true) {
            System.out.print("> ");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
            } else {
                scanner.next();
            }
            System.out.println("Choix invalide.");
        }
    }

    public String getString() {
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }
}