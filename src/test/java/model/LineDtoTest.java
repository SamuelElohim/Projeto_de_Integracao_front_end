package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineDtoTest {
    @Test
    public void equalsTest01(){
        LineDto line1 = new LineDto(1, "Ares");
        LineDto line2 = new LineDto(1, "Ares");

        assertEquals(line1, line2);
    }

    @Test
    public void equalsTest02(){
        LineDto line1 = new LineDto(2, "Ares");
        LineDto line2 = new LineDto(1, "Ares");

        assertNotEquals(line1, line2);
    }

}