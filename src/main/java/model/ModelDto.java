package model;

public class ModelDto extends AbstractDtoObject {
    private CategoryDto category;

    public ModelDto() {}

    public ModelDto(CategoryDto category, String name, int id) {
        super(id, name);
        this.category = category;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
