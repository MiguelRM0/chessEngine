/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/6/25
 *Time: 10:27 PM
 *
 *Project: chessEngine
 *Package: com.marm.gui.Practice
 *Class: StackPaneClickExample
 *Description:
 * **************************************** */
package com.marm.gui.Practice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackPaneClickExample extends Application {
    public void start(Stage primaryStage) {
        // Create a StackPane
        StackPane stackPane = new StackPane();
        Text text = new Text("Click me!");
        stackPane.getChildren().add(text);

        // Add a click event to the StackPane
        stackPane.setOnMouseClicked(event -> {
            System.out.println("StackPane clicked!");
            text.setText("You clicked the StackPane!");
        });

        // Create a Scene
        Scene scene = new Scene(stackPane, 300, 200);

        // Set up the Stage
        primaryStage.setTitle("StackPane Click Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}