import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MainScreen.fxml"));
        stage.getIcons().add(new Image("view/Eletra_Icon.png"));

        Scene s = new Scene(loader.load());
        stage.setScene(s);
        stage.setTitle("Projeto de Integração");
        stage.setMinHeight(380);
        stage.setMinWidth(420);
        stage.show();

    }
}