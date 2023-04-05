package controller;

import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;
import model.LineDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.MockedStatic;
import org.testfx.framework.junit.ApplicationTest;
import service.HttpRestClient;
import service.JsonDtoMapper;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MainScreenControllerTest extends ApplicationTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    MainScreenController mc;

    @Before
    public void setUp() {
        mc = spy(MainScreenController.class);
        mc.lineSelector = new ComboBox();
        mc.modelSelector = new TreeView();
        mc.titledPaneLines = new TitledPane();
        mc.titledPaneModels = new TitledPane();
        mc.accordion = new Accordion();
    }

    @After
    public void finished() {
        mc = null;
    }

    @Test
    public void initializeTest01() {
        doNothing().when(mc).fillLineSelectorComboBox();
        mc.accordion.setExpandedPane(null);

        mc.initialize(null, null);

        assertEquals("Check if initialize sets TitledPaneLines to expanded",
                mc.titledPaneLines,
                mc.accordion.getExpandedPane());
    }

    @Test
    public void initializeTest02() {
        doNothing().when(mc).fillLineSelectorComboBox();
        mc.titledPaneModels.setDisable(false);

        mc.initialize(null, null);

        assertTrue(mc.titledPaneModels.isDisabled());
    }

    @Test
    public void fillLineSelectorComboBoxTest01() {

        try(MockedStatic<JsonDtoMapper> mockConnection = mockStatic(JsonDtoMapper.class)) {

            LineDto[] dtos = new LineDto[]{new LineDto(1, "Ares"), new LineDto(2, "Cronos")};
            List<LineDto> dtoslist = Arrays.asList(dtos);

            mockConnection.when(() -> JsonDtoMapper.getDatabaseList(LineDto[].class, "linhas")).thenReturn(dtoslist);
            mc.lineSelector.getItems().clear();
            mc.fillLineSelectorComboBox();
            assertFalse("Check comboBox is empty when request LineDto list", mc.lineSelector.getItems().isEmpty());
        }
    }

    @Test
    public void fillLineSelectorComboBoxTest02() {

        try(MockedStatic<JsonDtoMapper> mockConnection = mockStatic(JsonDtoMapper.class)) {

            LineDto[] dtos = new LineDto[]{new LineDto(1, "Ares"), new LineDto(2, "Cronos")};
            List<LineDto> expected = Arrays.asList(dtos);

            mockConnection.when(() -> JsonDtoMapper.getDatabaseList(LineDto[].class, "linhas")).thenReturn(expected);
            mc.lineSelector.getItems().clear();
            mc.fillLineSelectorComboBox();
            List<LineDto> actual = mc.lineSelector.getItems();
            assertArrayEquals(expected.toArray(), actual.toArray());

        }
    }

    @Test
    public void openTreeViewTest01() {
        doNothing().when(mc).fillTreeView();

        mc.titledPaneModels.setDisable(true);
        mc.openTreeView();
        assertFalse(mc.titledPaneModels.isDisabled());

    }

    @Test
    public void openTreeViewTest02() {
        doNothing().when(mc).fillTreeView();

        mc.titledPaneModels.setExpanded(false);
        mc.openTreeView();
        assertTrue(mc.titledPaneModels.isExpanded());

    }

    @Test
    public void fillTreeViewTest01() {

    }




}