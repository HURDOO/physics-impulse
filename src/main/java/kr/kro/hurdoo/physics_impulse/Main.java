package kr.kro.hurdoo.physics_impulse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("/MainScene.fxml")));
        AnchorPane pane = loader.load();
        Font.loadFont(MainScene.class.getResourceAsStream("/NanumBarunGothic.ttf"),24);
        pane.setStyle("-fx-font-family: \"NanumBarunGothic\"");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
