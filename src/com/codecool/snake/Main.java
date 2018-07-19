package com.codecool.snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button twoPlayerButton = new Button();
        twoPlayerButton.setText("Player VS Player");
        twoPlayerButton.setLayoutX(30);
        twoPlayerButton.setLayoutY(0);


        twoPlayerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Label secondLabel = new Label();

                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(secondLabel);

                Game game = new Game(2);
                Scene secondScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("The Game");
                newWindow.setScene(secondScene);

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(primaryStage);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX());
                newWindow.setY(primaryStage.getY());
                newWindow.show();
                game.start();
            }
        });

        Button onePlayerButton = new Button();
        onePlayerButton.setText("Single Player");


        onePlayerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Label secondLabel = new Label();

                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(secondLabel);

                Game game = new Game(1);
                Scene secondScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("The Game");
                newWindow.setScene(secondScene);

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(primaryStage);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX());
                newWindow.setY(primaryStage.getY());
                newWindow.show();
                game.start();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(twoPlayerButton);

        Scene scene = new Scene(root, 450, 250);

        primaryStage.setTitle("Welcome New Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*Game game = new Game();
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();*/
    }

}
