import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private List<Room> rooms;
    private int roomCount = 10;
    private NPC boss;

    public Dungeon(ThemeFactory factory) {
        this.rooms = new ArrayList<>();
        generateDungeon(factory);
    }

    private void generateDungeon(ThemeFactory factory) {
        for (int i = 1; i <= roomCount; i++) {
            rooms.add(new Room(i, factory));
        }
        this.boss = factory.createBoss();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public NPC getBoss() {
        return boss;
    }
}
