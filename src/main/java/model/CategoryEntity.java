package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CategoryEntity extends AbstractEntity {

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

    public static String getEndUrl() {return "categorias";}

    public static List<CategoryEntity> getDatabaseList() {
        Gson gson = new Gson();
        return gson.fromJson(getDatabaseJSON(getEndUrl()), new TypeToken<List<CategoryEntity>>(){}.getType());
    }

    public static List<CategoryEntity> getDatabaseList(String filter) {
        Gson gson = new Gson();
        return gson.fromJson(getDatabaseJSON(getEndUrl(), filter), new TypeToken<List<CategoryEntity>>(){}.getType());
    }

    @Override
    public String toString() {
        return name;
    }
}
