module Pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;

    opens Controllers to javafx.fxml;

    exports Controllers;
    exports Models;
    exports Views;
    exports Animation; // Ajoute cette ligne pour exporter le package Animation
}
