package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class FollowingEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 20;

    public FollowingEnemy(Pane pane) {
        super(pane);
        setImage(Globals.followingEnemy);
        pane.getChildren().add(this);
        int speed = 3;
        double direction = 90;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

    }

    @Override
    public void step() {

    }

    @Override
    public void apply(SnakeHead snakeHead) {

    }

    @Override
    public String getMessage() {
        return null;
    }
}
