package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Category;
import model.Line;
import model.Model;

public class MainScreenController {

    @FXML
    private ComboBox<Line> lineSelector;

    @FXML
    private TreeView modelSelector;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane titledPaneLines;

    @FXML
    private TitledPane titledPaneModels;

    @FXML
    void initialize() {

        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);

        lineSelector.setItems(FXCollections.observableArrayList(Line.values()));
        lineSelector.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView()));

    }

    void openTreeView() {
        titledPaneModels.setDisable(false);
        titledPaneModels.setExpanded(true);

        fillTreeView();
    }

    void fillTreeView() {
        TreeItem root = new TreeItem();

        Line lineSelected = lineSelector.getValue();
        for (Category category: Category.values()) {
            if(category.getLine().equals(lineSelected)) {
                TreeItem item = new TreeItem<>(category);
                root.getChildren().add(item);
                for (Model model : Model.values()) {
                    if(model.getCategory().getLine().equals(lineSelected)
                    && model.getCategory().equals(category)) {
                        TreeItem modelo = new TreeItem(model);
                        item.getChildren().add(modelo);
                    }
                }
            }
        }

        root.setValue(lineSelected);
        root.setExpanded(true);
        modelSelector.setRoot(root);

    }

}
