package com.dht.c1englishapp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    public void checkHandler(ActionEvent event) {
        int a = 20, b = 20;
        if (a > 0) {
            int c = a + b;
        }
    }
} 
