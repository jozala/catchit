package pl.aetas.catchit;

public enum EventType {
    BOOT("boot"), WAKEUP("wakeup");

    private final String name;

    private EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
