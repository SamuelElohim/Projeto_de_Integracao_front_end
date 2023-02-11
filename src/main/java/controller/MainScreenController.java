package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainScreenController {

    @FXML
    private ComboBox<LineEntity> lineSelector;

    @FXML
    private TreeView modelSelector;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane titledPaneLines;

    @FXML
    private TitledPane titledPaneModels;

    public Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    void initialize() {

        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);


        List<LineEntity> lineList = session.createQuery("FROM LineEntity ORDER BY name ASC").list();
        lineSelector.setItems(FXCollections.observableArrayList(lineList));
        lineSelector.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView()));

    }

    void openTreeView() {
        titledPaneModels.setDisable(false);
        titledPaneModels.setExpanded(true);

        fillTreeView();
    }

    void fillTreeView() {
        TreeItem root = new TreeItem();
        List<TreeItem> categoryItems = new ArrayList<>();

        String lineSelected = lineSelector.getValue().toString();

        List<CategoryEntity> categoryList= session.
                createQuery("FROM CategoryEntity WHERE line_id = :lineSelected")
                .setParameter("lineSelected", lineSelected)
                .list();
        categoryList.forEach(category -> {
            TreeItem categoryItem = new TreeItem<>(category);
            root.getChildren().add(categoryItem);

            List<ModelEntity> modelList= session.
                    createQuery("FROM ModelEntity WHERE category_id = :category")
                    .setParameter("category", category)
                    .list();

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
