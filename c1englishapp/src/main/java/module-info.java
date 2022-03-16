module com.dht.c1englishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.dht.c1englishapp to javafx.fxml;
    exports com.dht.c1englishapp;
    exports com.dht.pojo;
}
