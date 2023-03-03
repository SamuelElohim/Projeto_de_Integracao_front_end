package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import model.*;

public class MainScreenController {

    @FXML
    private ComboBox<AbstractEntity> lineSelector;

    @FXML
    private TreeView modelSelector;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane titledPaneLines;

    @FXML
    private TitledPane titledPaneModels;

    @FXML
    void initialize() throws IOException {

        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);


        List<LineEntity> lineList = LineEntity.getDatabaseList();

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

        List<CategoryEntity> categoryList = CategoryEntity.getDatabaseList(lineSelected);

        categoryList.forEach(categoryListItem -> {
            TreeItem categoryItem = new TreeItem<>(categoryListItem);
            root.getChildren().add(categoryItem);

            List<ModelEntity> modelList = ModelEntity.getDatabaseList(categoryListItem.toString());

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
