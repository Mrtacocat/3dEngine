package com.example._3dengine;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape3D;

import java.io.IOException;

public class HelloApplication extends Application {

    public Parent createContent() throws Exception {

        // Light
        PointLight light = new PointLight();
        light.setColor(Color.RED);
        light.setRotate(45);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);
        Group lightGroup = new Group();
        lightGroup.setTranslateZ(-75);

        lightGroup.getChildren().add(light);





        // Box
        Box testBox = new Box(5, 5, 5);
        testBox.setMaterial(new PhongMaterial(Color.RED));
        testBox.setDrawMode(DrawMode.FILL);

        // Create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(-20, Rotate.Y_AXIS),
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -15));

        // Create Material
        PhongMaterial material = new PhongMaterial();
        Image diffuseMap = new Image("img/diffuseMap.jpg");
        Image normalMap = new Image("img/Wood_027_normal.jpg");

        // Set Material properties
        material.setDiffuseMap(diffuseMap);
        material.setBumpMap(normalMap);
        material.setSpecularColor(Color.WHITE);

        testBox.setMaterial(material);

        // Build the Scene Graph
        Group root = new Group();
        root.getChildren().add(lightGroup);

        root.getChildren().add(camera);
        root.getChildren().add(testBox);

        SubScene subScene = new SubScene(root, 300, 300);
        subScene.setFill(Color.BLACK);
        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);
        return group;
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();

}




    public static void main(String[] args) {
        launch();
    }
}