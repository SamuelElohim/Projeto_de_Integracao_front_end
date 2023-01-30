package model;

public enum Category {

    CRONOS_OLD(Line.CRONOS, "Cronos Old"),
    CRONOS_L(  Line.CRONOS, "Cronos L"),
    CRONOS_NG( Line.CRONOS, "Cronos NG"),
    ARES_TB(   Line.ARES,   "Ares TB"),
    ARES_THS(  Line.ARES,   "Ares THS");

    private final Line line;
    private final String name;
    Category(Line line, String name){
        this.line = line;
        this.name = name;
    }
    public Line getLine() {
        return line;
    }

    @Override
    public String toString() {
        return name;
    }
}
