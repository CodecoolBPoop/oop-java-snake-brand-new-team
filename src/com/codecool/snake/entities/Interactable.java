package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeHead2;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(SnakeHead snakeHead);

    void apply(SnakeHead2 snakeHead);

    String getMessage();

}
