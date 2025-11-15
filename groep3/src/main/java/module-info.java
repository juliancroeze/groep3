module com.groep3 {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.groep3.Controller to javafx.fxml;
    opens com.groep3 to javafx.fxml;
    exports com.groep3;
}
