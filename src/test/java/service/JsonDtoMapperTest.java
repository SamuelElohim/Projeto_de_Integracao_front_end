package service;

import model.CategoryDto;
import model.LineDto;
import model.ModelDto;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

 public class JsonDtoMapperTest {

     @Test
     public void testGetDatabaseList01() {

         try (MockedStatic<HttpRestClient> mockConnection = mockStatic(HttpRestClient.class)) {

             String endURL = "linhas";
             String json = "[{\"id\":1,\"name\":\"Ares\"},{\"id\":2,\"name\":\"Cronos\"}]";


             mockConnection.when(() -> HttpRestClient.getDatabaseJSON(endURL)).thenReturn(json);

             List<LineDto> actual = JsonDtoMapper.getDatabaseList(LineDto[].class, endURL);

             List<LineDto> expected = new ArrayList<>();
             expected.add(new LineDto(1, "Ares"));
             expected.add(new LineDto(2, "Cronos"));

             assertEquals(expected, actual);

         }
     }

     @Test
     public void testGetDatabaseList02() {

         try (MockedStatic<HttpRestClient> mockConnection = mockStatic(HttpRestClient.class)) {

             String endURL = "categorias";
             String json = "[{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}," +
                     "{\"id\":2,\"name\":\"Cronos L\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}]";


             mockConnection.when(() -> HttpRestClient.getDatabaseJSON(endURL)).thenReturn(json);

             List<CategoryDto> actual = JsonDtoMapper.getDatabaseList(CategoryDto[].class, endURL);

             List<CategoryDto> expected = new ArrayList<>();
             expected.add(new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos")));
             expected.add(new CategoryDto(2, "Cronos L", new LineDto(2, "Cronos")));


             assertEquals(actual, expected);

         }
     }

     @Test
     public void testGetDatabaseList03() {

         try (MockedStatic<HttpRestClient> mockConnection = mockStatic(HttpRestClient.class)) {

             String endURL = "modelos";
             String json = "[{\"id\":1,\"name\":\"Cronos 6001-A\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}," +
                     "{\"id\":2,\"name\":\"Cronos 6003\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}]";


             mockConnection.when(() -> HttpRestClient.getDatabaseJSON(endURL)).thenReturn(json);

             List<ModelDto> actual = JsonDtoMapper.getDatabaseList(ModelDto[].class, endURL);

             List<ModelDto> expected = new ArrayList<>();
             expected.add(new ModelDto(1, "Cronos 6001-A", new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos"))));
             expected.add(new ModelDto(2, "Cronos 6003", new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos"))));


             assertEquals(actual, expected);

         }
     }

     @Test
     public void testGetDatabaseList04() {

         try (MockedStatic<HttpRestClient> mockConnection = mockStatic(HttpRestClient.class)) {

             String endURL = "categorias";
             String json = "[{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}," +
                     "{\"id\":2,\"name\":\"Cronos L\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}]";
             String filter = "Cronos";


             mockConnection.when(() -> HttpRestClient.getDatabaseJSON(endURL, filter)).thenReturn(json);

             List<CategoryDto> actual = JsonDtoMapper.getDatabaseList(CategoryDto[].class, endURL, filter);

             List<CategoryDto> expected = new ArrayList<>();
             expected.add(new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos")));
             expected.add(new CategoryDto(2, "Cronos L", new LineDto(2, "Cronos")));


             assertEquals(actual, expected);

         }
     }

     @Test
     public void testGetDatabaseList05() {

         try (MockedStatic<HttpRestClient> mockConnection = mockStatic(HttpRestClient.class)) {

             String endURL = "modelos";
             String json = "[{\"id\":1,\"name\":\"Cronos 6001-A\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}," +
                     "{\"id\":2,\"name\":\"Cronos 6003\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}]";
             String filter = "Cronos Old";


             mockConnection.when(() -> HttpRestClient.getDatabaseJSON(endURL,filter)).thenReturn(json);

             List<ModelDto> actual = JsonDtoMapper.getDatabaseList(ModelDto[].class, endURL, filter);

             List<ModelDto> expected = new ArrayList<>();
             expected.add(new ModelDto(1, "Cronos 6001-A", new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos"))));
             expected.add(new ModelDto(2, "Cronos 6003", new CategoryDto(1, "Cronos Old", new LineDto(2, "Cronos"))));


             assertEquals(actual, expected);

         }
     }





}