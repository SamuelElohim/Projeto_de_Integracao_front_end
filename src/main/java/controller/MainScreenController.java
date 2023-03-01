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
    private ComboBox<LineEntity> lineSelector;

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


        List<LineEntity> lineList = Arrays.asList(new Gson().fromJson(getDatabaseJSONString("linhas"), LineEntity[].class));

        System.out.println(getDatabaseJSONString("linhas"));
        System.out.println(getDatabaseJSONString("modelos", "cronos ng"));

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

        List<CategoryEntity> categoryList =
                Arrays.asList(new Gson().fromJson(getDatabaseJSONString("categorias",lineSelected), CategoryEntity[].class));

        categoryList.forEach(categoryListItem -> {
            TreeItem categoryItem = new TreeItem<>(categoryListItem);
            root.getChildren().add(categoryItem);

            List<ModelEntity> modelList=
                    Arrays.asList(new Gson().fromJson(getDatabaseJSONString("modelos",categoryListItem.toString()), ModelEntity[].class));

            modelList.forEach(model -> {
                TreeItem modelItem = new TreeItem(model);
                categoryItem.getChildren().add(modelItem);
            });
        });

        root.setValue(lineSelected);
        root.setExpanded(true);
        modelSelector.setRoot(root);

    }

    String getDatabaseJSONString(String endUrl) {
        String json = "";
        String baseURL = "http://localhost:8080/api/";
        String fullURL = baseURL.concat(endUrl);
        try {
            URL url = new URL(fullURL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() != 200){
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String output;

            while ((output = br.readLine()) != null){
                json += output;
            }
            conexao.disconnect();

        } catch (Exception e) {
            System.out.println(e);
        }
        return json;


    }

    String getDatabaseJSONString(String endUrl, String filter) {
        String json = "";
        String baseURL = "http://localhost:8080/api";
        String fullURL = String.format("%s/%s/%s", baseURL, endUrl, filter.replaceAll(" ", "%20"));
        try {
            URL url = new URL(fullURL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() != 200){
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String output;

            while ((output = br.readLine()) != null){
                json += output;
            }
            conexao.disconnect();

        } catch (Exception e) {
            System.out.println(e);
        }
        return json;


    }

}
