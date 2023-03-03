package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ModelEntity extends AbstractEntity {
    private String name;

    private CategoryEntity category;

    public ModelEntity() {}

    public ModelEntity(CategoryEntity category, String name) {
        this.category = category;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public static String getEndUrl() {return "modelos";}

    public static List<ModelEntity> getDatabaseList() {
        Gson gson = new Gson();
        return gson.fromJson(getDatabaseJSON(getEndUrl()), new TypeToken<List<ModelEntity>>(){}.getType());
    }

    public static List<ModelEntity> getDatabaseList(String filter) {
        Gson gson = new Gson();
        return gson.fromJson(getDatabaseJSON(getEndUrl(), filter), new TypeToken<List<ModelEntity>>(){}.getType());
    }

    @Override
    public String toString() {
        return name;
    }
}
