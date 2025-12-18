import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private ThemeFactory themeFactory;
    private Dungeon dungeon;
    private Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println(ConsoleColors.ANSI_CYAN + "Bienvenue dans le RPG !" + ConsoleColors.ANSI_RESET);

        System.out.println();
        System.out.println("Choisissez un thème :");
        System.out.println("1. Médiéval");
        System.out.println("2. Futuriste");
        int themeChoice = getUserInput(1, 2);

        if (themeChoice == 1) {
            themeFactory = new MedievalThemeFactory();
        } else {
            themeFactory = new FuturisticThemeFactory();
        }

        System.out.println();
        System.out.println("Choisissez une classe :");
        System.out.println("1. Barbare (Force élevée)");
        System.out.println("2. Archer (Dextérité élevée)");
        System.out.println("3. Assassin (Dextérité/Critique)");
        System.out.println("4. Sorcier (Intelligence élevée)");
        int classChoice = getUserInput(1, 4);

        System.out.println();
        System.out.println("Entrez le nom de votre héros :");
        String name = scanner.next();

        switch (classChoice) {
            case 1:
                player = new Barbarian(name);
                break;
            case 2:
                player = new Archer(name);
                break;
            case 3:
                player = new Assassin(name);
                break;
            case 4:
                player = new Wizard(name);
                break;
        }

        player.inventory.add(themeFactory.createRandomItem());
        player.inventory.add(themeFactory.createRandomItem());

        dungeon = new Dungeon(themeFactory);

        System.out.println();
        System.out.println(ConsoleColors.ANSI_PURPLE + "Le jeu commence !" + ConsoleColors.ANSI_RESET);
        gameLoop();
    }

    private void gameLoop() {
        boolean inGame = true;
        while (inGame) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Afficher l'inventaire");
            System.out.println("2. Entrer dans le donjon");
            System.out.println("3. Quitter");

            int choice = getUserInput(1, 3);
            if (choice == 1) {
                manageInventory();
            } else if (choice == 2) {
                enterDungeon();
            } else {
                inGame = false;
            }
        }
    }

    private void manageInventory() {
        if (player.inventory.isEmpty()) {
            System.out.println("Inventaire vide.");
            return;
        }
        System.out.println("Inventaire :");
        for (int i = 0; i < player.inventory.size(); i++) {
            System.out.println((i + 1) + ". " + player.inventory.get(i).getName());
        }
        System.out.println((player.inventory.size() + 1) + ". Retour");

        int choice = getUserInput(1, player.inventory.size() + 1);
        if (choice <= player.inventory.size()) {
            Item item = player.inventory.get(choice - 1);
            if (item instanceof Consumable) {
                GameAction useItemCommand = new UseItemCommand(player, (Consumable) item);
                useItemCommand.execute();
            } else if (item instanceof Equipment) {
                GameAction equipCommand = new EquipItemCommand(player, (Equipment) item);
                equipCommand.execute();
            } else {
                System.out.println(item.getName() + " ne peut pas être utilisé.");
            }
        }
    }

    private void enterDungeon() {
        System.out.println("\nVous entrez dans le donjon...");
        for (Room room : dungeon.getRooms()) {
            if (processRoom(room)) {
            } else {
                System.out.println(ConsoleColors.ANSI_RED + "GAME OVER" + ConsoleColors.ANSI_RESET);
                System.exit(0);
            }
        }

        System.out
                .println(ConsoleColors.ANSI_RED_BACKGROUND + "VOUS ARRIVEZ AU BOSS FINAL !" + ConsoleColors.ANSI_RESET);
        NPC boss = dungeon.getBoss();
        if (processBoss(boss)) {
            System.out.println(
                    ConsoleColors.ANSI_GREEN + "VICTOIRE ! Vous avez terminé le donjon." + ConsoleColors.ANSI_RESET);
            System.exit(0);
        } else {
            System.out.println(ConsoleColors.ANSI_RED + "GAME OVER" + ConsoleColors.ANSI_RESET);
            System.exit(0);
        }
    }

    private boolean processRoom(Room room) {
        System.out.println(ConsoleColors.ANSI_YELLOW + "\n=== Salle " + room.getRoomNumber() + " ===" + ConsoleColors.ANSI_RESET);

        // Format: "Un Chevalier Errant et 2 Rats Enragés apparaissent !"
        List<NPC> enemies = room.getEnemies();
        if (!enemies.isEmpty()) {
            java.util.Map<String, Integer> counts = new java.util.HashMap<>();
            for (NPC e : enemies) {
                counts.put(e.getName(), counts.getOrDefault(e.getName(), 0) + 1);
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (String name : counts.keySet()) {
                int count = counts.get(name);
                if (i > 0)
                    sb.append(i == enemies.size() ? ", " : " et ");
                if (count > 1)
                    sb.append(count).append(" ").append(name).append("s");
                else
                    sb.append("un ").append(name);
                i++;
            }
            sb.append(enemies.size() > 1 ? " apparaissent !" : " apparait !");
            System.out.println(sb.toString());
        }

        List<Item> items = room.getItems();
        if (!items.isEmpty()) {
            StringBuilder sb = new StringBuilder("Vous trouvez : ");
            for (int k = 0; k < items.size(); k++) {
                if (k > 0)
                    sb.append(" et ");
                sb.append("une ").append(items.get(k).getName());
            }
            sb.append(".");
            System.out.println(sb.toString());
        } else {
            System.out.println("Aucun objet visible.");
        }

        while (!enemies.isEmpty()) {
            System.out.println("");
            System.out.print(ConsoleColors.ANSI_GREEN + "@ " + ConsoleColors.ANSI_RESET); // Player
            for (NPC enemy : enemies) {
                String color = ConsoleColors.ANSI_RED;
                System.out.print(color + enemy.getName().charAt(0) + " " + ConsoleColors.ANSI_RESET);
            }
            for (Item _ : items) {
                System.out.print(ConsoleColors.ANSI_YELLOW + "?" + " " + ConsoleColors.ANSI_RESET);
            }
            System.out.println("\n");

            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Attaquer un ennemi");
            System.out.println("2. Ramasser un objet");
            System.out.println("3. Afficher l'inventaire");

            int action = getUserInput(1, 3);
            System.out.println("");
            if (action == 1) {
                System.out.println("Attaquer qui ?");
                for (int j = 0; j < enemies.size(); j++) {
                    System.out.println(
                            (j + 1) + ". " + enemies.get(j).getName() + " (PV: " + enemies.get(j).getHealth() + ")");
                }
                int enemyIdx = getUserInput(1, enemies.size()) - 1;
                NPC target = enemies.get(enemyIdx);

                GameAction attackCommand = new AttackCommand(player, target);
                attackCommand.execute();

                if (target.getHealth() <= 0) {
                    System.out.println(target.getName() + " est vaincu !");
                    enemies.remove(enemyIdx);
                }

                if (!enemies.isEmpty()) {
                    performEnemyAttacks(enemies);
                }

            } else if (action == 2) {
                if (items.isEmpty()) {
                    System.out.println("Rien à ramasser.");
                } else {
                    System.out.println("Ramasser quoi ?");
                    for (int j = 0; j < items.size(); j++) {
                        System.out.println((j + 1) + ". " + items.get(j).getName());
                    }
                    int itemIdx = getUserInput(1, items.size()) - 1;
                    Item item = items.remove(itemIdx);
                    player.inventory.add(item);
                    System.out.println("Vous ramassez : " + item.getName());
                }
            } else if (action == 3) {
                manageInventory();
            }

            if (player.getHealth() <= 0) {
                return false;
            }

            player.startTurn();
        }

        System.out.println("Salle nettoyée !");
        return true;
    }

    private void performEnemyAttacks(List<NPC> enemies) {
        System.out.println("");
        System.out.println(ConsoleColors.ANSI_RED + "Les ennemis ripostent !" + ConsoleColors.ANSI_RESET);
        for (NPC enemy : enemies) {
            System.out.println("");
            enemy.attack(player);
            if (player.getHealth() <= 0)
                break;
        }
    }

    private boolean processBoss(NPC boss) {
        List<NPC> enemies = new ArrayList<>();
        enemies.add(boss);
        System.out.println("Un " + boss.getName() + " rugit !");

        while (boss.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\nBoss PV: " + boss.getHealth() + " | Joueur PV: " + player.getHealth());
            System.out.println("1. Attaquer");
            System.out.println("2. Inventaire");

            int choice = getUserInput(1, 2);
            if (choice == 1) {
                GameAction attackCommand = new AttackCommand(player, boss);
                attackCommand.execute();
                
                if (boss.getHealth() > 0) {
                    boss.attack(player);
                }
            } else {
                manageInventory();
                if (boss.getHealth() > 0) {
                    boss.attack(player);
                }
            }
            player.startTurn();
        }

        return player.getHealth() > 0;
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

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
