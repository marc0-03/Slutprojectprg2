import sun.plugin2.message.Pipe;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class model implements Serializable{
    private ArrayList<Bird> Birds = new ArrayList<Bird>();
    private ArrayList<Obstacle> Pipes = new ArrayList<Obstacle>();
    private int Pipecount;
    private int NewGameCounter=100;
    private int deadcount=0;

    public model(){
    }

    public void update() {
        for (int i=0; i<Pipes.size(); i++) {
            Pipes.get(i).X -= 2;
            if (Pipes.get(i).X<=-120){
                Pipes.remove(i);
                for (Bird bird : Birds) {
                    if (bird.alive()){
                        bird.score();
                    }
                }
            }
        }

        if (Pipecount==200) {
            SpawnPipe();
            Pipecount=0;
        } else {
            Pipecount++;
        }

        for (Bird bird : Birds) {
            if (bird.alive()) {
                bird.Move();
                Rectangle Bird = new Rectangle(100, (int) bird.getY(), 60, 45);
                for (Obstacle p : Pipes) {
                    if (p.getX() >= 0 || p.getX() <= 300) {
                        Rectangle PipeTop = new Rectangle((int) p.getX(), 0, 120, (int) p.getY());
                        Rectangle PipeBot = new Rectangle((int) p.getX(), (int) p.getY() + 120, 120, (int) (700 - p.getY()));

                        if (Bird.intersects(PipeTop) || Bird.intersects(PipeBot)) {
                            bird.DIE();
                        }
                    }
                }
            } else {
                deadcount++;
            }
        }

        if (deadcount==Birds.size()){
            NewGameCounter--;
            if (NewGameCounter==0) {
                startNew();
            }
        }
        deadcount=0;
    }

    private void startNew() {
        NewGameCounter=100;
        Pipes.clear();
        Pipecount=200;
        for (Bird bird : Birds) {
            bird.Start();
        }
    }

    private void SpawnPipe() {
        Pipes.add(new Obstacle(900, (Math.random()*500)+100, 2));
    }

    public ArrayList<Bird> getBirds(){
     return Birds;
    }
    public ArrayList<Obstacle> getPipes(){
        return Pipes;
    }

    public void JumpBird(int id){
        for (Bird bird : Birds) {
            if (bird.getId()==id){
                bird.RISE();
                bird.Jump();
            }
        }

        System.out.print("BIRD ID = " + id + " | JUMPED");
    }
    public void CreateBird(int id){
        Birds.add(new Bird(id));
        System.out.println("BIRD CREATED ID = " + id);
    }
}
