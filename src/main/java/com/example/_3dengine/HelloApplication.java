package com.example._3dengine;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape3D;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws Exception {

        Box box = new Box(100, 100, 100);

        Group group = new Group();
        group.getChildren().add(box);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        box.translateXProperty().set(WIDTH / 2d);
        box.translateYProperty().set(HEIGHT / 2d);
        box.translateZProperty().set(-1200);

        Transform transform = new Rotate(65, new Point3D(1, 0, 0));
        box.getTransforms().add(transform);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    box.translateZProperty().set(box.getTranslateZ() + 100);
                    break;
                case S:
                    box.translateZProperty().set(box.getTranslateZ() - 100);
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