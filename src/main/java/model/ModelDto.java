package model;

public class ModelDto extends AbstractDtoObject {
    private CategoryDto category;

    public ModelDto() {}

    public ModelDto(int id, String name, CategoryDto category) {
        super(id, name);
        this.category = category;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ModelDto)) {
            return false;
        }

        ModelDto otherModel = (ModelDto) obj;
        return super.equals(obj) && category.equals(otherModel.category);
    }
}
