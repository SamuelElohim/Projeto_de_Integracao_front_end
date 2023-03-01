package model;

public class CategoryEntity implements EntityInterface {

    private String name;
    private LineEntity line;

    public CategoryEntity() {}

    public CategoryEntity(LineEntity line, String name){
        this.line = line;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LineEntity getLine() {
        return line;
    }

    public void setLine(LineEntity line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return name;
    }
}
