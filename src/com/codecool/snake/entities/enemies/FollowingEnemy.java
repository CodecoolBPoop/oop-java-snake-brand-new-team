package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeHead2;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class FollowingEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private int speed;

    public FollowingEnemy(Pane pane) {

        super(pane);
        setImage(Globals.followingEnemy);
        pane.getChildren().add(this);
        Random rand = new Random();
        setX(rand.nextDouble() * Globals.WINDOW_WIDTH);
        if (getX() <= SnakeHead.firstSnakePositionX & getX()-500 >= SnakeHead.firstSnakePositionX + 1000) {
            setX(rand.nextDouble() * Globals.WINDOW_WIDTH);
        }
        setY(rand.nextDouble() * Globals.WINDOW_HEIGHT);
        if (getY() <= SnakeHead.firstSnakePositionY & getY()-500 >= SnakeHead.firstSnakePositionY + 1000) {
            setY(rand.nextDouble() * Globals.WINDOW_HEIGHT);
        }
        this.speed = 1;
        double snakeX = SnakeHead.firstSnakePositionX;
        double snakeY = SnakeHead.firstSnakePositionY;
        heading = Utils.getDirectionVectorToFollowSnake(snakeX, snakeY, getX(), getY(), speed);

    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        double snakeX = SnakeHead.firstSnakePositionX;
        double snakeY = SnakeHead.firstSnakePositionY;
        heading = Utils.getDirectionVectorToFollowSnake(snakeX, snakeY, getX(), getY(), speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }


    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(-damage);
        destroy();
    }

    @Override
    public void apply(SnakeHead2 snakeHead) {
        snakeHead.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Damage 10";
    }
}
