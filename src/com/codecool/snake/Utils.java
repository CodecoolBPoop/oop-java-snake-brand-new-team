package com.codecool.snake;

import javafx.geometry.Point2D;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static Point2D getDirectionVectorToFollowSnake(double snakeX, double snakeY, double enemyX, double enemyY, double length) {
        double xDiff = (snakeX - enemyX);
        double yDiff = (snakeY - enemyY);
        double hypotenuse = Math.sqrt(Math.pow(yDiff, 2) + Math.pow(xDiff, 2));

        double xVectorLength = length * xDiff / hypotenuse;
        double yVectorLength = length * yDiff / hypotenuse;
        Point2D heading = new Point2D(xVectorLength, yVectorLength);

        return heading;
    }
}
