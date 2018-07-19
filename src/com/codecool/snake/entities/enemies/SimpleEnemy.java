package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeHead2;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private double direction;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.arrowHead);
        pane.getChildren().add(this);
        int speed = 4;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        if (getX() <= SnakeHead.firstSnakePositionX & getX()-500 >= SnakeHead.firstSnakePositionX + 1000) {
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        }
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        if (getY() <= SnakeHead.firstSnakePositionY & getY()-500 >= SnakeHead.firstSnakePositionY + 1000) {
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        }

        this.direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        //this.direction += 5.00;
        //heading = Utils.directionToVector(this.direction, 5);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public void apply(SnakeHead2 snakeHead) {
        snakeHead.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
