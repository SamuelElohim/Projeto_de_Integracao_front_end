package model;

public enum Line {
    CRONOS("Cronos"),
    ARES(  "Ares");

    private final String name;
    Line(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
