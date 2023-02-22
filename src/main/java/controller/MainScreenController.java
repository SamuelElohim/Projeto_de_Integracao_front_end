package controller;

import java.util.List;
import hibernate.util.HibernateSession;
import javafx.scene.control.*;
import model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import org.hibernate.Session;

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

    public Session session = HibernateSession.getSessionInstance();

    @FXML
    void initialize() {

        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);

        List<LineEntity> lineList = getDatabaseList(LineEntity.class);

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

        List<CategoryEntity> categoryList = getFilteredDatabaseList(
                CategoryEntity.class, "line_id", lineSelected);

        categoryList.forEach(categoryListItem -> {
            TreeItem categoryItem = new TreeItem<>(categoryListItem);
            root.getChildren().add(categoryItem);

            List<ModelEntity> modelList= getFilteredDatabaseList(
                    ModelEntity.class, "category_id", categoryListItem);

            modelList.forEach(model -> {
                TreeItem modelItem = new TreeItem(model);
                categoryItem.getChildren().add(modelItem);
            });
        });

        root.setValue(lineSelected);
        root.setExpanded(true);
        modelSelector.setRoot(root);

    }

    <T extends EntityInterface> List<T> getDatabaseList(Class<T> entityClass) {
        List<T> entityList = session.createQuery(
                        String.format("FROM %s", entityClass.getSimpleName()))
                .list();

        return entityList;
    }

    <T extends EntityInterface> List<T> getFilteredDatabaseList(Class<T> entityClass, String columnName, Object filterObject) {
        List<T> entityList = session.createQuery(
                        String.format("FROM %s WHERE %s = '%s'",
                                entityClass.getSimpleName(),
                                columnName,
                                filterObject.toString()))
                .list();

        return entityList;
    }

}
