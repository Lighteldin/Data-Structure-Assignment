package me.jehn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Frame extends Application {
    private static Stage stage;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Parent root = FXMLLoader.load(Frame.class.getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        System.setProperty("prism.lcdtext", "false");
    }

    public static void switchScene(String name) throws IOException{
        Parent root = FXMLLoader.load(Frame.class.getResource(name+".fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


}