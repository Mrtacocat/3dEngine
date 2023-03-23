package com.example._3dengine;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class GUI extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws Exception {

        Shape3DClass box = new Shape3DClass();
        Shape3DClass sphere = new Shape3DClass();

        Group group = new Group();
        //group.getChildren().add(box.getBox());
        group.getChildren().add(sphere.getSphere());

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT);

        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        Rotate worldRotX = new Rotate(0, Rotate.X_AXIS);
        Rotate worldRotY = new Rotate(0, Rotate.Y_AXIS);

        Translate worldTransX = new Translate();

        group.getTransforms().add(worldRotY);
        group.getTransforms().add(worldRotX);


        // Positions the box in the middle of the scene
        group.translateXProperty().set(WIDTH / 2d);
        group.translateYProperty().set(HEIGHT / 2d);
        group.translateZProperty().set(-800);

        MoveObject.initMouseControl(group, scene);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case S -> group.setTranslateZ(group.getTranslateZ() + 20);
                case W -> group.setTranslateZ(group.getTranslateZ() - 20);
                case A -> group.setTranslateX(group.getTranslateX() + 20);
                case D -> group.setTranslateX(group.getTranslateX() - 20);
                case SHIFT -> group.setTranslateY(group.getTranslateY() + 20);
                case CONTROL -> group.setTranslateY(group.getTranslateY() - 20);
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