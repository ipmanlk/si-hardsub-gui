package xyz.navinda.sihardsub;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Primary.fxml"));
        primaryStage.setTitle("Sinhala Hardsub");
        primaryStage.setScene(new Scene(root, 600, 373));
        primaryStage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
