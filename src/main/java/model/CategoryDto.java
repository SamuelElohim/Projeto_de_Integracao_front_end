package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CategoryDto implements DtoObjectInterface {

    private int id;
    private String name;
    private LineDto line;


    public CategoryDto() {}
    public CategoryDto(LineDto line, String name, int id){
        this.id = id;
        this.line = line;
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

    public LineDto getLine() {
        return line;
    }

    public void setLine(LineDto line) {
        this.line = line;
    }

    public static String getEndUrl() {return "categorias";}

    @Override
    public String toString() {
        return name;
    }
}
