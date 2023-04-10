package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModelDtoTest {

    @Test
    public void equalsTest01(){
        ModelDto model1 = new ModelDto(1, "Cronos 6001-A",
                new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos")));
        ModelDto model2 = new ModelDto(1, "Cronos 6001-A",
                new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos")));

        assertEquals(model1, model2);
    }

    @Test
    public void equalsTest02(){
        ModelDto model1 = new ModelDto(1, "Cronos 6001-B",
                new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos")));
        ModelDto model2 = new ModelDto(1, "Cronos 6001-A",
                new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos")));

        assertNotEquals(model1, model2);
    }

    @Test
    public void equalsTest03(){
        ModelDto model1 = new ModelDto(1, "Cronos 6001-A",
                new CategoryDto(1, "Cronos L", new LineDto(1, "Cronos")));
        ModelDto model2 = new ModelDto(1, "Cronos 6001-A",
                new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos")));

        assertNotEquals(model1, model2);
    }

    @Test
    public void equalsTest04(){
        ModelDto model1 = new ModelDto(1, "Cronos 6001-A",
                new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos")));
        ModelDto model2 = new ModelDto(1, "Cronos 6001-A",
                new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos")));

        assertNotEquals(model1, model2);
    }

}