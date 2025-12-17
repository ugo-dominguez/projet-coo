public abstract class Item {
    protected String name;
    protected int value;
    protected double weight;

    public Item(String name, int value, double weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }
}
