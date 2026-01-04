import java.util.List;
import java.util.Scanner;

public class InventoryManager {
    private Scanner scanner;
    private DisplayManager displayManager;

    public InventoryManager(Scanner scanner, DisplayManager displayManager) {
        this.scanner = scanner;
        this.displayManager = displayManager;
    }

    public void manageInventory(Player player, Game game) {
        if (player.inventory.isEmpty()) {
            System.out.println("Inventaire vide.");
            return;
        }
        
        displayManager.displayInventory(player);
        
        int choice = getUserInput(1, player.inventory.size() + 1);
        if (choice <= player.inventory.size()) {
            useItem(player, choice - 1, game);
        }
    }

    private void useItem(Player player, int itemIndex, Game game) {
        Item item = player.inventory.get(itemIndex);
        
        if (item instanceof Consumable) {
            GameAction useItemCommand = new UseItemCommand(player, (Consumable) item);
            useItemCommand.execute();
        } else if (item instanceof Equipment) {
            GameAction equipCommand = new EquipItemCommand(player, (Equipment) item);
            equipCommand.execute();
        } else if (item instanceof LegendaryItem) {
            GameAction equipLegendaryCommand = new EquipLegendaryItemCommand(
                player, (LegendaryItem) item, game
            );
            equipLegendaryCommand.execute();
        } else {
            System.out.println(item.getName() + " ne peut pas être utilisé.");
        }
    }

    public void pickupItem(Player player, List<Item> items, Game game) {
        if (items.isEmpty()) {
            System.out.println("Rien à ramasser.");
            return;
        }
        
        System.out.println("Ramasser quoi ?");
        for (int j = 0; j < items.size(); j++) {
            System.out.println((j + 1) + ". " + items.get(j).getName());
        }
        
        int itemIdx = getUserInput(1, items.size()) - 1;
        Item item = items.remove(itemIdx);
        player.inventory.add(item);
        System.out.println("Vous ramassez : " + item.getName());
        
        if (item instanceof LegendaryItem) {
            offerLegendaryEquip(player, (LegendaryItem) item, game);
        }
    }

    private void offerLegendaryEquip(Player player, LegendaryItem item, Game game) {
        System.out.println(ConsoleColors.ANSI_PURPLE + "\nCet objet semble... spécial." + ConsoleColors.ANSI_RESET);
        System.out.println("Voulez-vous l'utiliser maintenant ?");
        System.out.println("1. Oui");
        System.out.println("2. Non, le garder pour plus tard");
        
        int equipChoice = getUserInput(1, 2);
        
        if (equipChoice == 1) {
            GameAction equipLegendaryCommand = new EquipLegendaryItemCommand(
                player, item, game
            );
            equipLegendaryCommand.execute();
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