package model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static service.HttpRestClient.getDatabaseJSON;

public class LineDto implements DtoObjectInterface {

    private int id;
    private String name;
    public LineDto() {}

    public LineDto(String name, int id) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
