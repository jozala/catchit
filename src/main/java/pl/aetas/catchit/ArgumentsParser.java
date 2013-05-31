package pl.aetas.catchit;


public class ArgumentsParser {
    private EventType eventType;

    public void parseArgs(String[] args) {
        String eventString = args[1];
        for (EventType event : EventType.values()) {
            if (eventString.equals(event.getName())) {
                eventType = event;
                return;
            }
        }
    }

    public boolean validateArgs(String[] args) {
        if (args.length < 2) {
            return false;
        }
        if (!args[0].equals("-e")) {
            return false;
        }
        return true;
    }

    public EventType getEventType() {
        return eventType;
    }

}
