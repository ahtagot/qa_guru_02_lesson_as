package tests;

public enum Timezone {

    EASTERN("Eastern"),
    CENTRAL("Central"),
    MOUNTAIN("Mountain"),
    PACIFIC("Pacific");

    private final String displayName;

    Timezone(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return displayName;
    }

}
