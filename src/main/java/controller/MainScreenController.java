package controller;

import java.util.List;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import model.*;

import static service.JsonDtoMapper.getDatabaseList;

public class MainScreenController {

    @FXML
    private ComboBox<LineDto> lineSelector;

    @FXML
    private TreeView modelSelector;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane titledPaneLines;

    @FXML
    private TitledPane titledPaneModels;

    @FXML
    void initialize(){

        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);

        List<LineDto> lineList = getDatabaseList(LineDto[].class, "linhas");

        lineSelector.setItems(FXCollections.observableArrayList(lineList));
        lineSelector.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView()));

    }

    void openTreeView() {
        titledPaneModels.setDisable(false);
        titledPaneModels.setExpanded(true);

        fillTreeView();
    }

    void fillTreeView() {
        String lineSelected = lineSelector.getValue().toString();
        TreeItem root = new TreeItem(lineSelected);

        List<CategoryDto> categoryList = getDatabaseList(CategoryDto[].class, "categorias", lineSelected);

        categoryList.forEach(categoryListItem -> {
            TreeItem categoryItem = new TreeItem<>(categoryListItem);
            root.getChildren().add(categoryItem);

            List<ModelDto> modelList = getDatabaseList(ModelDto[].class, "modelos", categoryListItem.toString());

            modelList.forEach(model -> {
                TreeItem modelItem = new TreeItem(model);
                categoryItem.getChildren().add(modelItem);
            });
        });

        root.setValue(lineSelected);
        root.setExpanded(true);
        modelSelector.setRoot(root);

    }

}
