package com.codecool.snake.entities.powerups;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ScoreBar {
    Label scoreLabel;


    public ScoreBar(Pane pane, int x, int y) {
        scoreLabel = new Label();
        scoreLabel.setLayoutX(x);
        scoreLabel.setLayoutY(y);
        pane.getChildren().add(scoreLabel);

    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScore(int score) {
        this.scoreLabel.setText(score + " score");
        this.scoreLabel.setTextFill(Color.WHITE);
        this.scoreLabel.setStyle("-fx-font-weight: bold");
    }
}
