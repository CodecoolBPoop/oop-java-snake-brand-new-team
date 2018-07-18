package com.codecool.snake.entities.powerups;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ScoreBar {
    Label scoreLabel;


    public ScoreBar(Pane pane) {
        scoreLabel = new Label();
        scoreLabel.setLayoutX(100);
        scoreLabel.setLayoutY(30);
        pane.getChildren().add(scoreLabel);

    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScore(int score) {
        this.scoreLabel.setText(score + " score");
    }
}
