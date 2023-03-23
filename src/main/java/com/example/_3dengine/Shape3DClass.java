package com.example._3dengine;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;

import java.util.Objects;

public class Shape3DClass extends PhongMaterial {

    PhongMaterial material;
    Box box;
    Sphere sphere;

    public Shape3DClass() {
        box = prepereBox();
        sphere = prepereSphere();

    }

    public Box getBox() {
        return box;
    }

    public Sphere getSphere() {
        return sphere;
    }

    private Material changeMaterial() {
        material = new PhongMaterial();
        try {
            material.setDiffuseMap(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/diffuseMap.jpg"))));
            material.setBumpMap(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/normalMap.jpg"))));
            material.setSpecularMap(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/specularMap.jpg"))));
            material.setSelfIlluminationMap(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/illumination.png"))));
        } catch (Exception e) {
            System.out.println("Error while loading textures: " + e.toString());
        } finally {
            if (material.getDiffuseMap() == null) {
                material.setDiffuseColor(Color.RED);
            }
            if (material.getBumpMap() == null) {
                material.setSpecularColor(Color.BLUE);
            }
            if (material.getSpecularMap() == null) {
                material.setSpecularColor(Color.GREEN);
            }
        }
        return material;
    }

    private Box prepereBox() {
        Box box = new Box(200, 200, 200);
        box.setMaterial(changeMaterial());
        return box;
    }

    private Sphere prepereSphere() {
        Sphere sphere = new Sphere(100);
        sphere.setMaterial(changeMaterial());
        return sphere;
    }

    // create method for changing material





}
