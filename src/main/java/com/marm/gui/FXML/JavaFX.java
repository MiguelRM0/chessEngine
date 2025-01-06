/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/6/25
 *Time: 1:30 AM
 *
 *Project: chessEngine
 *Package: com.marm.gui.FXML
 *Class: JavaFX
 *Description:
 * **************************************** */
package com.marm.gui.FXML;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class JavaFX extends Application {
    Stage window;
    Scene scene;
    Button button;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

      TextField userInput = new TextField();
      userInput.setMaxWidth(200);
      Label firstLabel = new Label("Welcome to the site ");
      Label secondLabel = new Label();


        HBox bottomText = new HBox(firstLabel, secondLabel);
        bottomText.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, userInput,bottomText);
        layout.setAlignment(Pos.CENTER);
        secondLabel.textProperty().bind(userInput.textProperty());
        scene= new Scene(layout,300,300);
        window.setScene(scene);
        window.show();


    }

    public static void main(String[] args) {
        launch(args);
    }







}