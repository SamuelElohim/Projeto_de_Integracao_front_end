package model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class LineEntity extends AbstractEntity {

    private String name;
    public LineEntity() {}

    public LineEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEndUrl() {return "linhas";}

    public static List<LineEntity> getDatabaseList() {
        Gson gson = new Gson();
        return gson.fromJson(getDatabaseJSON(getEndUrl()), new TypeToken<List<LineEntity>>(){}.getType());
    }

    public static List<LineEntity> getDatabaseList(String filter) {
        Gson gson = new Gson();
        return gson.fromJson(getDatabaseJSON(getEndUrl(), filter), new TypeToken<List<LineEntity>>(){}.getType());
    }

    @Override
    public String toString() {
        return name;
    }
}
