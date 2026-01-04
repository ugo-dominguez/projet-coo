import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<NPC> enemies;
    private List<Item> items;
    private int roomNumber;

    public Room(int roomNumber, ThemeFactory factory) {
        this.roomNumber = roomNumber;
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        generateContent(factory);
    }

    private void generateContent(ThemeFactory factory) {
        // 1 à 4 enemies
        int enemyCount = Dice.randomInt(1, 3);
        for (int i = 0; i < enemyCount; i++) {
            enemies.add(factory.createRandomEnemy());
        }

        // 0 à 2 items normaux
        int itemCount = Dice.randomInt(0, 3);
        for (int i = 0; i < itemCount; i++) {
            items.add(factory.createRandomItem());
        }

        // 20% de chance d'avoir un objet légendaire
        if (Dice.randomInt(1, 100) <= 20) {
            items.add(factory.createLegendaryItem());
        }
    }

    public List<NPC> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}