package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.DtoObjectInterface;
import model.LineDto;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static service.HttpRestClient.getDatabaseJSON;

public class JsonDtoMapper {

    public static <T extends DtoObjectInterface> List<T> getDatabaseList(Class<T[]> dtoClass, String endURL) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(getDatabaseJSON(endURL), dtoClass));
    }

    public static <T extends DtoObjectInterface> List<T> getDatabaseList(Class<T[]> dtoClass, String endURL, String filter) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(getDatabaseJSON(endURL, filter), dtoClass));
    }

}
