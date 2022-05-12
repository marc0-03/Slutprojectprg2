import java.awt.*;

public class Bird {
    private double Y,SpeedY;
    private Color color;
    private int id, score;
    private boolean alive;

    public Bird(int id){
        this.id = id;
        alive=false;
        score=0;
        Y=400;

        color = new Color((int) (Math.random()*255),(int) (Math.random()*255),(int) (Math.random()*255));

    }

    public double getY(){
        return Y;
    }

    public void Jump(){
            SpeedY = 10;
    }

    public void Move(){
            Y += SpeedY;
            SpeedY = -0.5;
    }

    public void DIE(){
        //SEND HIGSCORE TO DATABASE
        alive=false;
    }

    public boolean alive() {
        return alive;
    }

    public void score() {
        score++;
    }
    public void Start(){
        score=0;
        Y=400;
        alive=true;
        SpeedY=5;
    }
}
