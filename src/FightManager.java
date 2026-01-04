import java.util.List;
import java.util.Scanner;

public class FightManager {
    private Scanner scanner;

    public FightManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void selectAttackClass(Player player) {
        if (player instanceof DualClassPlayer) {
            DualClassPlayer dualPlayer = (DualClassPlayer) player;
            System.out.println("Choisissez votre type d'attaque :");
            System.out.println("1. " + dualPlayer.getBaseClassName());
            System.out.println("2. " + dualPlayer.getSecondClassName());
            
            int attackChoice = getUserInput(1, 2);
            System.out.println("");
            
            if (attackChoice == 1) {
                dualPlayer.switchToFirstClass();
            } else {
                dualPlayer.switchToSecondClass();
            }
        }
    }

    public boolean playerAttackEnemy(Player player, List<NPC> enemies) {
        selectAttackClass(player);
        
        System.out.println("Attaquer qui ?");
        for (int j = 0; j < enemies.size(); j++) {
            System.out.println((j + 1) + ". " + enemies.get(j).getName() + " (PV: " + enemies.get(j).getHealth() + ")");
        }
        
        int enemyIdx = getUserInput(1, enemies.size()) - 1;
        NPC target = enemies.get(enemyIdx);

        GameAction attackCommand = new AttackCommand(player, target);
        attackCommand.execute();

        if (target.getHealth() <= 0) {
            System.out.println(target.getName() + " est vaincu !");
            enemies.remove(enemyIdx);
            return true;
        }
        return false;
    }

    public void enemiesAttack(Player player, List<NPC> enemies) {
        System.out.println("");
        System.out.println(ConsoleColors.ANSI_RED + "Les ennemis ripostent !" + ConsoleColors.ANSI_RESET);
        
        for (NPC enemy : enemies) {
            System.out.println("");
            enemy.attack(player);
            if (player.getHealth() <= 0) {
                break;
            }
        }
    }

    private int getUserInput(int min, int max) {
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
}