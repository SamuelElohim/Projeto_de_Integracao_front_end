package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import model.*;

import static service.JsonDtoMapper.getDatabaseList;

public class MainScreenController implements Initializable {

    @FXML
    protected ComboBox<LineDto> lineSelector;

    @FXML
    protected TreeView modelSelector;

    @FXML
    protected Accordion accordion;

    @FXML
    protected TitledPane titledPaneLines;

    @FXML
    protected TitledPane titledPaneModels;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);

        fillLineSelectorComboBox();
    }

    public void fillLineSelectorComboBox() {
        List<LineDto> lineList = getDatabaseList(LineDto[].class, "linhas");

        lineSelector.setItems(FXCollections.observableArrayList(lineList));
        lineSelector.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView()));
    }

    public void openTreeView() {
        titledPaneModels.setDisable(false);
        titledPaneModels.setExpanded(true);

        fillTreeView();
    }

    public void fillTreeView() {
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
