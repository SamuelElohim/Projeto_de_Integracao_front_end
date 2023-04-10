package controller;

import javafx.scene.control.*;
import model.CategoryDto;
import model.LineDto;
import model.ModelDto;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.testfx.framework.junit.ApplicationTest;
import service.JsonDtoMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static service.JsonDtoMapper.getDatabaseList;

public class MainScreenControllerTest extends ApplicationTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    MainScreenController mc;
    protected static List<TreeItem<LineDto>> expectedCategoryTreeItems = new ArrayList<>();
    protected static List<TreeItem<LineDto>> actualCategoryTreeItems = new ArrayList<>();

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
        mc.lineSelector.setValue(new LineDto(1, "Ares"));
        doNothing().when(mc).fillTreeItem(any());

        ArgumentCaptor<TreeItem> captor = ArgumentCaptor.forClass(TreeItem.class);

        mc.fillTreeView();
        verify(mc).fillTreeItem(captor.capture());

        assertTrue(captor.getValue().isExpanded());
    }

    @Test
    public void fillTreeViewTest02() {
        mc.lineSelector.setValue(new LineDto(1, "Ares"));
        doNothing().when(mc).fillTreeItem(any());

        ArgumentCaptor<TreeItem> captor = ArgumentCaptor.forClass(TreeItem.class);

        mc.fillTreeView();

        verify(mc).fillTreeItem(captor.capture());
        assertEquals(mc.modelSelector.getRoot(), captor.getValue());
    }

    @Test
    public void fillTreeItemTest01() {
        try(MockedStatic<JsonDtoMapper> mockConnection = mockStatic(JsonDtoMapper.class)) {

            LineDto cronos = new LineDto(2, "Cronos");

            CategoryDto cronosOld = new CategoryDto(1, "Cronos Old", cronos);
            CategoryDto cronosL = new CategoryDto(1, "Cronos L", cronos);

            ModelDto cronos6001A = new ModelDto(1, "Cronos 6001-A", cronosOld);
            ModelDto cronos6003 = new ModelDto(2, "Cronos 6003", cronosOld);
            ModelDto cronos6021 = new ModelDto(4, "Cronos 6021", cronosL);
            ModelDto cronos7023L = new ModelDto(5, "Cronos 7023L", cronosL);

            List<CategoryDto> categoryDtosList = new ArrayList<>();
            categoryDtosList.add(cronosOld);
            categoryDtosList.add(cronosL);

            List<ModelDto> cronosOldDtoModelsList = new ArrayList<>();
            cronosOldDtoModelsList.add(cronos6001A);
            cronosOldDtoModelsList.add(cronos6003);

            List<ModelDto> cronosLDtoModelsList = new ArrayList<>();
            cronosLDtoModelsList.add(cronos6021);
            cronosLDtoModelsList.add(cronos7023L);

            mockConnection.when(() -> getDatabaseList(CategoryDto[].class, "categorias", "Cronos"))
                    .thenReturn(categoryDtosList);
            mockConnection.when(() -> getDatabaseList(ModelDto[].class, "modelos", "Cronos Old"))
                    .thenReturn(cronosOldDtoModelsList);
            mockConnection.when(() -> getDatabaseList(ModelDto[].class, "modelos", "Cronos L"))
                    .thenReturn(cronosLDtoModelsList);

            TreeItem<LineDto> root = new TreeItem<>(cronos);
            mc.fillTreeItem(root);


            TreeItem cronosOldTreeItem = new TreeItem("Cronos Old");
            TreeItem cronosLTreeItem = new TreeItem("Cronos L");
            cronosOldDtoModelsList.forEach(e -> cronosOldTreeItem.getChildren().add(new TreeItem<>(e)));
            cronosLDtoModelsList.forEach(e -> cronosLTreeItem.getChildren().add(new TreeItem<>(e)));

            expectedCategoryTreeItems = new ArrayList<>();
            expectedCategoryTreeItems.add(cronosOldTreeItem);
            expectedCategoryTreeItems.add(cronosLTreeItem);

            actualCategoryTreeItems = new ArrayList<>(root.getChildren());

            assertEquals(expectedCategoryTreeItems.toString(), actualCategoryTreeItems.toString());

        }
    }

    @Test
    public void fillTreeItemTest02() {
        StringBuilder expectedModelTreeItems = new StringBuilder();
        StringBuilder actualModelTreeItems = new StringBuilder();

        expectedCategoryTreeItems.forEach(categoryTreeItem -> {
            expectedModelTreeItems.append(categoryTreeItem.toString());
            categoryTreeItem.getChildren()
                    .forEach(modelTreeItem -> expectedModelTreeItems.append(modelTreeItem.toString()));
        });

        actualCategoryTreeItems.forEach(categoryTreeItem -> {
            actualModelTreeItems.append(categoryTreeItem.toString());
            categoryTreeItem.getChildren()
                    .forEach(modelTreeItem -> actualModelTreeItems.append(modelTreeItem.toString()));
        });

        assertEquals(expectedModelTreeItems.toString(), actualModelTreeItems.toString());
    }


}