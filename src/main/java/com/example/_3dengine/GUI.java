package com.example._3dengine;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class GUI extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage stage) throws Exception {

        Shape3DClass box = new Shape3DClass();

        Group group = new Group();
        group.getChildren().add(box.getBox());

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

        initMouseControl(group, scene);

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

    private void initMouseControl(Group group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
    }


    public static void main(String[] args) {
        launch();
    }

}