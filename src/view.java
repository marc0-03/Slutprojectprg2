import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class view extends Canvas {
    private BufferStrategy bs;
    private int HEIGHT;
    private int WIDTH;

    public view() {
        this.WIDTH = 500;
        this.HEIGHT = 900;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void draw() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(58, 116, 177));
        g.fillRect(0,0, 800, 950);
        g.setColor(new Color(86, 147, 210));
        g.fillRect(0,500, 800, 450);
        g.setColor(new Color(117, 169, 229));
        g.fillRect(0,650, 800, 300);
        g.setColor(new Color(173, 213, 255));
        g.fillRect(0,750, 800, 200);


        g.setColor(new Color(201, 173, 71));
        g.fillRect(0,870,800,100);
        g.setColor(new Color(46, 139, 23));
        g.fillRect(0,850,800,20);
        g.setColor(new Color(53, 170, 24));
        g.fillRect(0,855,800,10);

        g.dispose();
        bs.show();
    }

    public int getHeight() {
        return HEIGHT;
    }
    public int getWidth() {
        return WIDTH;
    }

    public void drawBirds(ArrayList<Bird> bids) {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(58, 116, 177));
        g.fillRect(0,0, 800, 950);
        g.setColor(new Color(86, 147, 210));
        g.fillRect(0,500, 800, 450);
        g.setColor(new Color(117, 169, 229));
        g.fillRect(0,650, 800, 300);
        g.setColor(new Color(173, 213, 255));
        g.fillRect(0,750, 800, 200);


        g.setColor(new Color(201, 173, 71));
        g.fillRect(0,870,800,100);
        g.setColor(new Color(46, 139, 23));
        g.fillRect(0,850,800,20);
        g.setColor(new Color(53, 170, 24));
        g.fillRect(0,855,800,10);

        for (Bird bird: bids){
            g.setColor(bird.getColor());
            g.fillRect(100, (int) bird.getY(), 60, 45);
        }

        g.dispose();
        bs.show();
    }

    public void drawPipes(ArrayList<Obstacle> pies) {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        for (Obstacle pipe: pies){
                g.setColor(new Color(20, 198, 42));
                g.fillRect((int) pipe.getX(), (int) pipe.getY()-30,120,30);
                g.fillRect( (int) pipe.getX()+10, 0,100, (int) pipe.getY()-30);

                g.fillRect((int) pipe.getX(),(int) pipe.getY()+120,120,30);
                g.fillRect( (int) pipe.getX()+10, (int) pipe.getY()+150,100, (int) (700-pipe.getY()));


                g.setColor(new Color(2,240,2));
                g.fillRect((int) pipe.getX()+4,(int) pipe.getY()-26,112,22);
                g.fillRect( (int) pipe.getX()+14, 4,92, (int) pipe.getY()-38);

                g.fillRect((int) pipe.getX()+4,(int) pipe.getY()+124,112,22);
                g.fillRect( (int) pipe.getX()+14, (int) pipe.getY()+154,92, (int) (700-pipe.getY()-8));
        }

        g.dispose();
        bs.show();
    }
}
