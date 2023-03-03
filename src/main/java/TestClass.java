import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.AbstractEntity;
import model.CategoryEntity;
import model.LineEntity;

import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        List<CategoryEntity> abc = CategoryEntity.getDatabaseList("ares");
        abc.forEach(System.out::println);
    }
}
