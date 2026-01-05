import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private ThemeFactory themeFactory;
    private Dungeon dungeon;

    // Managers
    private InputManager inputManager;
    private DisplayManager displayManager;
    private FightManager fightManager;
    private InventoryManager inventoryManager;

    public Game() {
        Scanner scanner = new Scanner(System.in);
        this.inputManager = new InputManager(scanner);
        this.displayManager = new DisplayManager();
        this.fightManager = new FightManager(inputManager);
        this.inventoryManager = new InventoryManager(inputManager, displayManager);
    }

    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }

    public void start() {
        System.out.println(ConsoleColors.ANSI_CYAN + "Bienvenue dans le RPG !" + ConsoleColors.ANSI_RESET);

        setupTheme();
        setupPlayer();
        setupDungeon();

        System.out.println();
        System.out.println(ConsoleColors.ANSI_PURPLE + "Le jeu commence !" + ConsoleColors.ANSI_RESET);
        gameLoop();
    }

    private void setupTheme() {
        System.out.println();
        System.out.println("Choisissez un thème :");
        System.out.println("1. Médiéval");
        System.out.println("2. Futuriste");
        System.out.println("3. Horreur fantastique");

        int themeChoice = inputManager.getInput(1, 3);

        switch (themeChoice) {
            case 1:
                themeFactory = new MedievalThemeFactory();
                break;
            case 2:
                themeFactory = new FuturisticThemeFactory();
                break;
            case 3:
                themeFactory = new HorrorFantasticThemeFactory();
                break;
        }
    }

    private void setupPlayer() {
        System.out.println();
        System.out.println("Choisissez une classe :");
        System.out.println("1. Barbare (Force élevée)");
        System.out.println("2. Archer (Dextérité élevée)");
        System.out.println("3. Assassin (Dextérité/Critique)");
        System.out.println("4. Sorcier (Intelligence élevée)");

        int classChoice = inputManager.getInput(1, 4);

        System.out.println();
        System.out.println("Entrez le nom de votre héros :");
        String name = inputManager.getString();

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
    }

    private void setupDungeon() {
        player.inventory.add(themeFactory.createRandomItem());
        player.inventory.add(themeFactory.createRandomItem());
        dungeon = new Dungeon(themeFactory);
    }

    private void gameLoop() {
        boolean inGame = true;
        while (inGame) {
            displayManager.displayMainMenu();
            int choice = inputManager.getInput(1, 4);
            System.out.println("");

            switch (choice) {
                case 1: // Entrer dans le donjon
                    enterDungeon();
                    break;
                case 2: // Statistiques
                    displayManager.displayPlayerStats(player);
                    break;
                case 3: // Inventaire
                    inventoryManager.manageInventory(player, this);
                    break;
                case 4: // Quitter
                    inGame = false;
                    break;
            }
        }
    }

    private void enterDungeon() {
        System.out.println("Vous entrez dans le donjon...");

        for (Room room : dungeon.getRooms()) {
            if (!processRoom(room)) {
                displayManager.displayGameOver();
                System.exit(0);
            }
        }

        processFinalBoss();
    }

    private void processFinalBoss() {
        NPC boss = dungeon.getBoss();
        displayManager.displayBossAnnouncement(boss.getName());

        List<NPC> bossList = new ArrayList<>();
        bossList.add(boss);

        if (processCombat(bossList, new ArrayList<>(), true)) {
            displayManager.displayVictory();
        } else {
            displayManager.displayGameOver();
        }
        System.exit(0);
    }

    private boolean processRoom(Room room) {
        displayManager.displayRoomHeader(room.getRoomNumber());

        List<NPC> enemies = room.getEnemies();
        List<Item> items = room.getItems();

        displayManager.displayEnemies(enemies);
        displayManager.displayItems(items);

        if (!processCombat(enemies, items, false)) {
            return false;
        }

        System.out.println("Salle nettoyée !");
        return true;
    }

    private boolean processCombat(List<NPC> enemies, List<Item> items, boolean isBoss) {
        player.dispatchEvent(new GameEvent(EventType.START_COMBAT, player, new java.util.HashMap<>()));

        while (!enemies.isEmpty() && player.getHealth() > 0) {
            if (isBoss) {
                displayManager.displayBossStatus(enemies.get(0), player);
            } else {
                displayManager.displayRoomMap(enemies, items);
            }

            displayManager.displayCombatMenu(isBoss);

            int maxOption = isBoss ? 3 : 4;
            int action = inputManager.getInput(1, maxOption);
            System.out.println("");

            if (!handleCombatAction(action, enemies, items, isBoss)) {
                return false; // Le joueur est mort
            }

            player.startTurn();
        }

        player.dispatchEvent(new GameEvent(EventType.END_COMBAT, player, new java.util.HashMap<>()));

        return true;
    }

    private boolean handleCombatAction(int action, List<NPC> enemies, List<Item> items, boolean isBoss) {
        switch (action) {
            case 1: // Attaquer
                fightManager.playerAttackEnemy(player, enemies);
                if (!enemies.isEmpty()) {
                    fightManager.enemiesAttack(player, enemies);
                }
                break;
            case 2: // Statistiques
                displayManager.displayPlayerStats(player);
                break;
            case 3: // Inventaire
                inventoryManager.manageInventory(player, this);
                if (!enemies.isEmpty()) {
                    fightManager.enemiesAttack(player, enemies);
                }
                break;
            case 4: // Ramasser (seulement hors boss)
                if (!isBoss) {
                    inventoryManager.pickupItem(player, items, this);
                }
                break;
        }

        return player.getHealth() > 0;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}