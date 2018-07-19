package com.codecool.snake;

import com.codecool.snake.entities.enemies.CircleEnemy;
import com.codecool.snake.entities.enemies.FollowingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.*;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeHead2;
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
    SnakeHead snakeHead1;
    HealthBar healthBar1;
    ScoreBar scoreBar1;

    SnakeHead2 snakeHead2;
    HealthBar healthBar2;
    ScoreBar scoreBar2;

    public Game() {
        snakeHead1 = new SnakeHead(this, 500, 500);
        healthBar1 = new HealthBar(this, 30, 30);
        snakeHead1.setBar(healthBar1);
        healthBar1.setLife(snakeHead1.getHealth());
        scoreBar1 = new ScoreBar(this, 100, 30);
        snakeHead1.setScore(scoreBar1);
        scoreBar1.setScore(snakeHead1.getScore());

        snakeHead2 = new SnakeHead2(this, 700, 500);
        healthBar2 = new HealthBar(this, 200, 30);
        snakeHead2.setBar(healthBar2);
        healthBar2.setLife(snakeHead2.getHealth());
        scoreBar2 = new ScoreBar(this, 300, 30);
        snakeHead2.setScore(scoreBar2);
        scoreBar2.setScore(snakeHead2.getScore());
        spawnEnemies();

    }

    public void spawnEnemies() {

        new FollowingEnemy(Game.this);
        new HealthPowerup(Game.this);
        new SimplePowerup(Game.this);
        new PowerUpSpeed(Game.this);
        new SimpleEnemy(Game.this);
        new CircleEnemy(Game.this);
        randomSpawn("health", 17, 20);
        randomSpawn("simple", 3, 6);
        randomSpawn("speed", 12, 18);
        randomSpawn("simpleEnemy", 1, 5);
        randomSpawn("followingEnemy", 5, 20);
        randomSpawn("circleEnemy", 5, 10);
    }

    public void randomSpawn(String toSpawn, int timeFrom, int timeTo) {
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
            case "followingEnemy":
                randomSecondSpawn = new Timeline(new KeyFrame(Duration.seconds(randomNumber), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new FollowingEnemy(Game.this);
                    }
                }));
                break;
            case "circleEnemy":
                randomSecondSpawn = new Timeline(new KeyFrame(Duration.seconds(randomNumber), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        new CircleEnemy(Game.this);
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
                case A: Globals.aKeyDown = true; break;
                case D: Globals.dKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case A: Globals.aKeyDown = false; break;
                case D: Globals.dKeyDown = false; break;
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
        this.getChildren().clear();
        Globals.leftKeyDown  = false;
        Globals.rightKeyDown  = false;
        SnakeHead snakeHead = new SnakeHead(this, 500, 500);
        snakeHead.setHealth(100);
        snakeHead.setScore(0);
        HealthBar healthBar = new HealthBar(this, 30, 30);
        snakeHead.setBar(healthBar);
        healthBar.setLife(snakeHead.getHealth());
        scoreBar1 = new ScoreBar(this, 300, 30);
        snakeHead.setScore(scoreBar1);
        scoreBar1.setScore(snakeHead.getScore());
        Globals.endGame = false;
        start();
        new HealthPowerup(Game.this);
        new SimplePowerup(Game.this);
        new PowerUpSpeed(Game.this);
        new SimpleEnemy(Game.this);
    }

    public static void gameOver() {
        Globals.endGame = true;
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

