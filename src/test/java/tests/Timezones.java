package tests;

public class Timezones {
    public enum UsTimezones {
        EASTERN("Eastern"),
        CENTRAL("Central"),
        MOUNTAIN("Mountain"),
        PACIFIC("Pacific");

        private final String displayName;

        UsTimezones(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

    }
}
