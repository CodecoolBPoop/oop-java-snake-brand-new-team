package com.codecool.snake.entities.powerups;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import com.codecool.snake.Globals;
public class HealthBar  {
    Label lifeLabel;


    public HealthBar(Pane pane) {
        lifeLabel = new Label();
        lifeLabel.setLayoutX(30);
        lifeLabel.setLayoutY(30);
        pane.getChildren().add(lifeLabel);

    }

    public Label getLifeLabel() {
        return lifeLabel;
    }

    public void setLife(int life) {
        this.lifeLabel.setText(life + " life");

    }
}
