package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url=Thread.currentThread().getContextClassLoader().getResource("sample.fxml");
            loader.setLocation(url);
            Parent root = new AnchorPane();
            loader.setRoot(root);
            root = loader.load();
            primaryStage.setTitle("");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
