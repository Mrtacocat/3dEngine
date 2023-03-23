package com.example._3dengine;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import java.util.Objects;

public class HelloApplication extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws Exception {

        Shape3DClass box = new Shape3DClass();

        SmartGroup group = new SmartGroup();
        group.getChildren().add(box.getBox());

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        // Positions the box in the middle of the scene
        group.translateXProperty().set(WIDTH / 2d);
        group.translateYProperty().set(HEIGHT / 2d);
        group.translateZProperty().set(-800);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    group.translateZProperty().set(group.getTranslateZ() + 100);
                    break;
                case S:
                    group.translateZProperty().set(group.getTranslateZ() - 100);
                    break;
                case Q:
                    group.rotateByX(10);
                    break;
                case E:
                    group.rotateByX(-10);
                    break;
                case Z:
                    group.rotateByY(10);
                    break;
                case C:
                    group.rotateByY(-10);
                    break;
            }
        });

        stage.setResizable(false);
        stage.setTitle("3D Engine");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}