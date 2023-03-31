package service;

import com.google.gson.Gson;
import model.AbstractDtoObject;

import java.util.Arrays;
import java.util.List;

import static service.HttpRestClient.getDatabaseJSON;

public class JsonDtoMapper {

    public static <T extends AbstractDtoObject> List<T> getDatabaseList(Class<T[]> dtoClass, String endURL) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(getDatabaseJSON(endURL), dtoClass));
    }

    public static <T extends AbstractDtoObject> List<T> getDatabaseList(Class<T[]> dtoClass, String endURL, String filter) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(getDatabaseJSON(endURL, filter), dtoClass));
    }

}
