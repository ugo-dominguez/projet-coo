import java.util.Map;

public class GameEvent {
    private EventType type;
    private Character actor;
    private Map<String, Object> data;

    public GameEvent(EventType type, Character actor, Map<String, Object> data) {
        this.type = type;
        this.actor = actor;
        this.data = data;
    }

    public EventType getType() {
        return type;
    }

    public Character getActor() {
        return actor;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }
}
