package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;


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

    public Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    void initialize() {

        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);


        List<Line> lineList = session.createQuery("FROM Line").list();
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

        String lineSelected = lineSelector.getValue().toString();
        List<Category> categoryList= session.
                createQuery(String.format("FROM Category WHERE line_id = '%s'", lineSelected)).list();

        for (Category category: categoryList) {
            TreeItem item = new TreeItem<>(category);
            root.getChildren().add(item);

            List<Model> modelList= session.
                    createQuery(String.format("FROM Model WHERE category_id = '%s'", category)).list();
            for (Model model : modelList) {
                    TreeItem modelo = new TreeItem(model);
                    item.getChildren().add(modelo);
            }
        }


        root.setValue(lineSelected);
        root.setExpanded(true);
        modelSelector.setRoot(root);

    }

}
