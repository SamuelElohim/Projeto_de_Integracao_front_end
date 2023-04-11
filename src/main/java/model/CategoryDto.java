package model;

public class CategoryDto extends AbstractDtoObject {
    private LineDto line;

    public CategoryDto() {}
    public CategoryDto(int id, String name, LineDto line){
        super(id, name);
        this.line = line;
    }

    public LineDto getLine() {
        return line;
    }

    public void setLine(LineDto line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CategoryDto)) {
            return false;
        }

        CategoryDto otherCategory = (CategoryDto) obj;
        return super.equals(obj) && line.equals(otherCategory.line);
    }
}


