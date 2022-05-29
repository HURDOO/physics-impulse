package kr.kro.hurdoo.physics_impulse;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class MainScene implements Initializable {

    @FXML TextField pitcher_velocity,ball_weight,hitter_power,hitter_time;
    @FXML ImageView ball;
    @FXML Button play,stop;
    @FXML TextField pitcher_ball_physics,hitter_ball_impulse;
    @FXML AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Platform.runLater(() -> {

            TextField[] changeAbles = {pitcher_velocity,ball_weight,hitter_power,hitter_time};
            for(TextField changeable : changeAbles) {
                changeable.textProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        if (newValue.equals("")) changeable.setText("0");
                        else changeable.setText(Double.toString(Double.parseDouble(newValue)));
                    } catch (Exception ex) {
                        changeable.setText(oldValue);
                    }
                });
            }

            play.setOnAction(event -> {
                startAnimation();
            });
            stop.setOnAction(event -> {
                playAnimation(0,0,0,0, 1, null);
                pitcher_ball_physics.setText("");
                hitter_ball_impulse.setText("");
            });
        });
    }

    public void startAnimation() {
        double pitcher_ball_physics_value = getValue(ball_weight)/1000*getValue(pitcher_velocity)*1000/3600;
        pitcher_ball_physics.setText(Double.toString(pitcher_ball_physics_value));
        hitter_ball_impulse.setText("");

        double pitcher_ball_duration = 150/getValue(pitcher_velocity)*1000;
        playAnimation(0,571,0,0, pitcher_ball_duration, event2 -> {

            double hitter_ball_impulse_value = getValue(hitter_power) * getValue(hitter_time);
            hitter_ball_impulse.setText(Double.toString(hitter_ball_impulse_value));

            double hitter_ball_velocity_change = getValue(hitter_power) * getValue(hitter_time) / getValue(ball_weight) * 1000;
            double hitter_ball_duration = 150/(getValue(pitcher_velocity)-hitter_ball_velocity_change)*1000;
            if(hitter_ball_duration<0) hitter_ball_duration *= -1;

            playAnimation(571,0,0,-350, hitter_ball_duration, null);
        });
    }

    public void playAnimation(int fromX, int toX, int fromY, int toY, double ms, EventHandler<ActionEvent> onFinished) {
        TranslateTransition transition = new TranslateTransition();
        transition.setFromX(fromX);
        transition.setToX(toX);
        transition.setFromY(fromY);
        transition.setToY(toY);
        transition.setDuration(Duration.millis(ms));
        transition.setNode(ball);
        transition.play();
        if(onFinished != null) transition.setOnFinished(onFinished);
    }

    public double getValue(TextField changeable) {
        return Double.parseDouble(changeable.getText());
    }
}
