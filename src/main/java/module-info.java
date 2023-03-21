module com.example._3dengine {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example._3dengine to javafx.fxml;
    exports com.example._3dengine;
}