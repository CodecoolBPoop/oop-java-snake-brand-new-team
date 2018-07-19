package com.codecool.snake.entities.powerups;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import com.codecool.snake.Globals;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class HealthBar  {
    Label lifeLabel;


    public HealthBar(Pane pane, int x, int y) {
        lifeLabel = new Label();
        lifeLabel.setLayoutX(x);
        lifeLabel.setLayoutY(y);
        pane.getChildren().add(lifeLabel);

    }

    public Label getLifeLabel() {
        return lifeLabel;
    }

    public void setLife(int life, String text) {
        this.lifeLabel.setText(text + life + " life");
        this.lifeLabel.setTextFill(Color.WHITE);
        this.lifeLabel.setStyle("-fx-font-weight: bold");

    }
}
