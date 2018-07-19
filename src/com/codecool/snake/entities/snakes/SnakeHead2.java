package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.HealthBar;
import com.codecool.snake.entities.powerups.ScoreBar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class SnakeHead2 extends GameEntity implements Animatable {

    private static float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    public static double secondSnakePositionX;
    public static double secondSnakePositionY;

    private static List<GameEntity> tailList = new ArrayList<>();

    public void setHealth(int health) {
        this.health = health;
    }
    public void setScore(int score) {
        Globals.score2 = score;
    }


    private int health;

    private HealthBar bar;
    private ScoreBar score;

    public SnakeHead2(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead2);
        pane.getChildren().add(this);

        addPart(4);
    }
    public void addHealth(){
        if (health<100) {
            changeHealth(10);
        }
    }

    public static void setSpeed() {
        speed = 4;
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(5000),
                ae ->speed=2));
        timeline.play();



    }

    public int getHealth() {
        return health;
    }
    public int getScore() {
        return Globals.score2;
    }



    public void step() {
        double dir = getRotate();
        if (Globals.aKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.dKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        secondSnakePositionX = getX();
        setY(getY() + heading.getY());
        secondSnakePositionY = getY();

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            destroy();
            for (GameEntity tails : tailList){
                tails.destroy();
            }
            Globals.secondSnakeDead = true;
            Game.gameOver();

        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tailList.add(newPart);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        this.bar.setLife(health, "Red player: ");
    }

    public void changeScore(int diff) {
        Globals.score2 += diff;
        this.score.setScore(Globals.score2);
    }

    public void setBar(HealthBar healthBar) {
        this.bar = healthBar;
    }

    public void setScore(ScoreBar scoreBar) {
        this.score = scoreBar;
    }


}
