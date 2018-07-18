package com.codecool.snake;

import com.codecool.snake.entities.enemies.FollowingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthBar;
import com.codecool.snake.entities.powerups.HealthPowerup;
import com.codecool.snake.entities.powerups.PowerUpSpeed;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.Random;

public class Game extends Pane {

    public static Timeline randomSecondSpawn;
    SnakeHead snakeHead;
    HealthBar healthBar;

    public Game() {
        snakeHead= new SnakeHead(this, 500, 500);
        healthBar = new HealthBar(this);
        snakeHead.setBar(healthBar);
        healthBar.setLife(snakeHead.getHealth());
        spawnEnemies();

    }

    public void spawnEnemies() {

        new FollowingEnemy(Game.this);
        new HealthPowerup(Game.this);
        new SimplePowerup(Game.this);
        new PowerUpSpeed(Game.this);
        new SimpleEnemy(Game.this);
        randomSpawn("health", 17, 20);
        randomSpawn("simple", 3, 6);
        randomSpawn("speed", 12, 18);
        randomSpawn("simpleEnemy", 1, 5);
    }

    public void randomSpawn(String toSpawn, int timeFrom, int timeTo){
        Random rand = new Random();
        int randomNumber = rand.nextInt(timeTo) + timeFrom;
        switch (toSpawn) {
            case "health":
                randomSecondSpawn = new Timeline(new KeyFrame(Duration.seconds(randomNumber), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new HealthPowerup(Game.this);
                    }
                }));
                break;
            case "simple":
                randomSecondSpawn = new Timeline(new KeyFrame(Duration.seconds(randomNumber), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new SimplePowerup(Game.this);
                    }
                }));
                break;
            case "speed":
                randomSecondSpawn = new Timeline(new KeyFrame(Duration.seconds(randomNumber), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new PowerUpSpeed(Game.this);
                    }
                }));
                break;
            case "simpleEnemy":
                randomSecondSpawn = new Timeline(new KeyFrame(Duration.seconds(randomNumber), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new SimpleEnemy(Game.this);
                    }
                }));
                break;
        }
        randomSecondSpawn.setCycleCount(Timeline.INDEFINITE);
        randomSecondSpawn.play();
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
        Globals.score = 0;
        randomSecondSpawn.stop();
        this.getChildren().clear();
        start();
        Globals.leftKeyDown  = false;
        Globals.rightKeyDown  = false;
        new SnakeHead(this, 500, 500);
        spawnEnemies();
    }

    public static void gameOver() {
        randomSecondSpawn.stop();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        int score = Globals.score;
        alert.setHeaderText("Your score is: " + score);
        String s ="Press R to Restart";
        alert.setContentText(s);
        alert.show();

    }
}

