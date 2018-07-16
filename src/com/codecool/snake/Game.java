package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Game extends Pane {

    Timeline fiveSecondsWonder;

    public Game() {
        new SnakeHead(this, 500, 500);
        spawnEnemies();

    }

    public void spawnEnemies() {

        new SimplePowerup(Game.this);
        new SimpleEnemy(Game.this);

        fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new SimplePowerup(Game.this);
                new SimpleEnemy(Game.this);
                }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();


    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
            if (event.getCode() == KeyCode.R) {
                restartGame();
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void restartGame() {
        Globals.gameLoop.stop();
        Globals.gameObjects.clear();
        Globals.oldGameObjects.clear();
        Globals.newGameObjects.clear();
        fiveSecondsWonder.stop();
        this.getChildren().clear();
        start();
        new SnakeHead(this, 500, 500);
        spawnEnemies();

    }
}

