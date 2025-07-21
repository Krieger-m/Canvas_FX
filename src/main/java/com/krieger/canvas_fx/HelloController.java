package com.krieger.canvas_fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas canvas;

    @FXML
    private Button start_btn;

    @FXML
    void onStartBtnClick(ActionEvent event) {

    }



    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}
