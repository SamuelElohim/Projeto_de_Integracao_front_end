package model;

public enum Model {

    CRONOS_6001_A( Category.CRONOS_OLD, "Cronos 6001-A"),
    CRONOS_6003(   Category.CRONOS_OLD, "Cronos 6003"),
    CRONOS_7023(   Category.CRONOS_OLD, "Cronos 7023"),
    CRONOS_6021L(  Category.CRONOS_L,   "Cronos 6021L"),
    CRONOS_7023L(  Category.CRONOS_L,   "Cronos 7023L"),
    CRONOS_6001_NG(Category.CRONOS_NG,  "Cronos 6001-NG"),
    CRONOS_6003_NG(Category.CRONOS_NG,  "Cronos 6003-NG"),
    CRONOS_6021_NG(Category.CRONOS_NG,  "Cronos 6021-NG"),
    CRONOS_6031_NG(Category.CRONOS_NG,  "Cronos 6031-NG"),
    CRONOS_7021_NG(Category.CRONOS_NG,  "Cronos 7021-NG"),
    CRONOS_7023_NG(Category.CRONOS_NG,  "Cronos 7023-NG"),
    ARES_7021(     Category.ARES_TB,    "Ares 7021"),
    ARES_7031(     Category.ARES_TB,    "Ares 7031"),
    ARES_7023(     Category.ARES_TB,    "Ares 7023"),
    ARES_8023_15(  Category.ARES_THS,   "Ares 8023 15"),
    ARES_8023_200( Category.ARES_THS,   "Ares 8023 200"),
    ARES_8023_2_5( Category.ARES_THS,   "Ares 8023 2,5");

    private final Category category;
    private final String name;
    Model(Category category, String name) {
        this.category = category;
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name;
    }
}
