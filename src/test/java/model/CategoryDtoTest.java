package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryDtoTest {

    @Test
    public void equalsTest01(){
        CategoryDto category1 = new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos"));
        CategoryDto category2 = new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos"));

        assertEquals(category1, category2);
    }

    @Test
    public void equalsTest02(){
        CategoryDto category1 = new CategoryDto(1, "Cronos L", new LineDto(1, "Cronos"));
        CategoryDto category2 = new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos"));

        assertNotEquals(category1, category2);
    }

    @Test
    public void equalsTest03(){
        CategoryDto category1 = new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos"));
        CategoryDto category2 = new CategoryDto(1, "Cronos Old", new LineDto(1, "Cronos"));

        assertNotEquals(category1, category2);
    }

}