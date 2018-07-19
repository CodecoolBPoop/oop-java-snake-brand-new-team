package com.codecool.snake.entities.powerups;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import com.codecool.snake.Globals;
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

    public void setLife(int life) {
        this.lifeLabel.setText(life + " life");

    }
}
