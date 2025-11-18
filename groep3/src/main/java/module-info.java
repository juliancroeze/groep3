module com.groep3 {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    //    requires com.fasterxml.jackson.annotations;
    requires javafx.controls;
    requires javafx.fxml;

    // export the root package so javafx.graphics can access App
    exports com.groep3;

    // export model if you need it publicly
    exports com.groep3.Model;

    // controllers are instantiated/reflected by FXMLLoader: open to javafx.fxml
    opens com.groep3.Controller to javafx.fxml;

    // open root package for reflection by javafx.graphics / javafx.fxml
    opens com.groep3 to javafx.graphics, javafx.fxml;
}