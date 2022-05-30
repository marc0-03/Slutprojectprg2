import java.awt.*;
import java.io.Serializable;

public class Obstacle implements Serializable {
    public double X,Y, Speed;
    private Rectangle hitBox;

    public Obstacle(double x, double y, double speed) {
        X=x;
        Y=y;
        Speed=speed;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public void move(){
        X-=Speed;
    }

}