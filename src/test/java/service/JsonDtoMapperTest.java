package service;

import model.LineDto;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

 public class JsonDtoMapperTest {

    @Test
    public void testGetDatabaseList() {

        try(MockedStatic<HttpRestClient> mockConnection = mockStatic(HttpRestClient.class)) {

            String endURL = "linhas";
            String json = "[{\"id\":1,\"name\":\"Ares\"},{\"id\":2,\"name\":\"Cronos\"}]";


            mockConnection.when(() -> HttpRestClient.getDatabaseJSON(endURL)).thenReturn(json);

            LineDto[] dtos = new LineDto[]{new LineDto(1, "Ares"), new LineDto(2, "Cronos")};
            List<LineDto> expected = Arrays.asList(dtos);

            List<LineDto> actual = JsonDtoMapper.getDatabaseList(LineDto[].class, endURL);

            assertTrue(expected.size() == actual.size());

        }
    }




}