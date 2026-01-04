import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DisplayManager {
    public void displayRoomHeader(int roomNumber) {
        System.out.println(ConsoleColors.ANSI_YELLOW + "\n=== Salle " + roomNumber + " ===" + ConsoleColors.ANSI_RESET);
    }

    public void displayEnemies(List<NPC> enemies) {
        if (enemies.isEmpty()) {
            return;
        }

        Map<String, Integer> counts = new HashMap<>();
        for (NPC e : enemies) {
            counts.put(e.getName(), counts.getOrDefault(e.getName(), 0) + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int totalTypes = counts.size();
        
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            
            if (index > 0) {
                sb.append(index == totalTypes - 1 ? " et " : ", ");
            } if (count > 1) {
                sb.append(count).append(" ").append(name).append("s");
            } else {
                sb.append("un ").append(name);
            }
            
            index++;
        }
        
        sb.append(enemies.size() > 1 ? " apparaissent !" : " apparaît !");
        System.out.println(sb.toString());
    }

    public void displayItems(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("Aucun objet visible.");
            return;
        }
        
        Map<String, Integer> counts = new HashMap<>();
        for (Item item : items) {
            counts.put(item.getName(), counts.getOrDefault(item.getName(), 0) + 1);
        }
        
        StringBuilder sb = new StringBuilder("Vous trouvez : ");
        int index = 0;
        int totalTypes = counts.size();
        
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            
            if (index > 0) {
                sb.append(index == totalTypes - 1 ? " et " : ", ");
            } if (count > 1) {
                sb.append(count).append(" ").append(name).append("s");
            } else {
                sb.append("un ").append(name);
            }
            
            index++;
        }
        
        sb.append(".");
        System.out.println(sb.toString());
    }

    public void displayRoomMap(List<NPC> enemies, List<Item> items) {
        System.out.println("");
        System.out.print(ConsoleColors.ANSI_GREEN + "@ " + ConsoleColors.ANSI_RESET);
        
        for (NPC enemy : enemies) {
            System.out.print(ConsoleColors.ANSI_RED + enemy.getName().charAt(0) + " " + ConsoleColors.ANSI_RESET);
        }
        
        for (Item _ : items) {
            System.out.print(ConsoleColors.ANSI_YELLOW + "?" + " " + ConsoleColors.ANSI_RESET);
        }
        System.out.println("\n");
    }

    public void displayPlayerStats(Player player) {
        System.out.println(ConsoleColors.ANSI_GREEN + "Statistiques de " + player.getName() + ConsoleColors.ANSI_RESET);
        System.out.println("PV: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Force: " + player.getForce());
        System.out.println("Dextérité: " + player.getDexterity());
        System.out.println("Constitution: " + player.getConstitution());
        System.out.println("Intelligence: " + player.getIntelligence());
    }

    public void displayInventory(Player player) {
        if (player.inventory.isEmpty()) {
            System.out.println("Inventaire vide.");
            return;
        }
        
        System.out.println(ConsoleColors.ANSI_GREEN + "Inventaire :" + ConsoleColors.ANSI_RESET);
        for (int i = 0; i < player.inventory.size(); i++) {
            System.out.println((i + 1) + ". " + player.inventory.get(i).getName());
        }
        System.out.println((player.inventory.size() + 1) + ". Retour");
    }

    public void displayMainMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Entrer dans le donjon");
        System.out.println("2. Afficher mes statistiques");
        System.out.println("3. Ouvrir l'inventaire");
        System.out.println("4. Quitter");
    }

    public void displayBossStatus(NPC boss, Player player) {
        System.out.println("\n" + ConsoleColors.ANSI_RED + "Boss: " + boss.getName() + " (PV: " + boss.getHealth() + ")" + ConsoleColors.ANSI_RESET);
    }

    public void displayCombatMenu(boolean isBoss) {
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Attaquer");
        System.out.println("2. Afficher mes statistiques");
        System.out.println("3. Ouvrir l'inventaire");
        if (!isBoss) {
            System.out.println("4. Ramasser un objet");
        }
    }

    public void displayVictory() {
        System.out.println("\n" + ConsoleColors.ANSI_GREEN + "VICTOIRE ! Vous avez terminé le donjon." + ConsoleColors.ANSI_RESET);
    }

    public void displayGameOver() {
        System.out.println(ConsoleColors.ANSI_RED + "GAME OVER" + ConsoleColors.ANSI_RESET);
    }

    public void displayBossAnnouncement(String bossName) {
        System.out.println("\n" + ConsoleColors.ANSI_RED_BACKGROUND + "VOUS ARRIVEZ AU BOSS FINAL !" + ConsoleColors.ANSI_RESET);
        System.out.println("Un " + bossName + " rugit !");
    }
}