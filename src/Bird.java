import java.awt.*;

public class Bird {
    private double Y,SpeedY;
    private Rectangle hitBox;
    private Color color;
    private int id, score;
    private boolean alive;

    public Bird(int id){
        this.id = id;
        alive=false;
        score=0;

        color = new Color((int) (Math.random()*255),(int) (Math.random()*255),(int) (Math.random()*255));


    }

    private void Jump(){
        SpeedY=10;
    }

    private void Move(){
        Y+=SpeedY;
    }
}
