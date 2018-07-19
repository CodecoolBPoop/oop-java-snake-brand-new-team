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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
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

    public Game(int num) {
        Globals.snakeNumbers = num;
        if (num == 2){
            createSnakes();
        } else {
            createFirstSnake();
        }
        spawnEnemies();

    }

    private void createSnakes(){
        createFirstSnake();
        createSecondSnake();
    }

    public void createFirstSnake(){
        snakeHead1 = new SnakeHead(this, 500, 500);
        healthBar1 = new HealthBar(this, 30, 30);
        snakeHead1.setBar(healthBar1);
        healthBar1.setLife(snakeHead1.getHealth(), "Green player: ");
        scoreBar1 = new ScoreBar(this, 230, 30);
        snakeHead1.setScore(scoreBar1);
        scoreBar1.setScore(snakeHead1.getScore());
        Globals.firstSnakeDead = false;
    }

    public void createSecondSnake(){
        snakeHead2 = new SnakeHead2(this, 700, 500);
        healthBar2 = new HealthBar(this, 500, 30);
        snakeHead2.setBar(healthBar2);
        healthBar2.setLife(snakeHead2.getHealth(), "Red player: ");
        scoreBar2 = new ScoreBar(this, 700, 30);
        snakeHead2.setScore(scoreBar2);
        scoreBar2.setScore(snakeHead2.getScore());
        Globals.secondSnakeDead = false;
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
        Globals.score1 = 0;
        Globals.score2 = 0;
        this.getChildren().clear();

        if (Globals.snakeNumbers == 2){
            createSnakes();
        } else if (Globals.snakeNumbers == 1){
            createFirstSnake();
        }

        Globals.leftKeyDown  = false;
        Globals.rightKeyDown  = false;

        start();
        new HealthPowerup(Game.this);
        new SimplePowerup(Game.this);
        new PowerUpSpeed(Game.this);
        new SimpleEnemy(Game.this);
    }

    public static void gameOver() {

        if (Globals.snakeNumbers == 2 && Globals.secondSnakeDead && Globals.firstSnakeDead) {
            initiateGameOver();
        } else if (Globals.snakeNumbers == 1){
            initiateGameOver();
        }
    }

    public static void initiateGameOver(){
        Globals.gameLoop.stop();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        int score1 = Globals.score1;
        int score2 = Globals.score2;
        alert.setHeaderText("Player 1's score: " + score1 + "\n" + "Player 2's score: " + score2);
        String s = "Press R to Restart";
        alert.setContentText(s);
        alert.show();
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}

