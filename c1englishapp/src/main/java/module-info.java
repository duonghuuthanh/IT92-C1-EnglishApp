module com.dht.c1englishapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dht.c1englishapp to javafx.fxml;
    exports com.dht.c1englishapp;
}
