package model;

public class CategoryDto extends AbstractDtoObject {
    private LineDto line;

    public CategoryDto() {}
    public CategoryDto(LineDto line, String name, int id){
        super(name, id);
        this.line = line;
    }

    public LineDto getLine() {
        return line;
    }

    public void setLine(LineDto line) {
        this.line = line;
    }
}
