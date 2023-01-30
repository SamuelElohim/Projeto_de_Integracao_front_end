import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("resources/view/MainScreen.fxml"));
        Scene s = new Scene(loader.load());
        stage.setScene(s);
        stage.show();
        stage.setTitle("Projeto Integração");
    }
}