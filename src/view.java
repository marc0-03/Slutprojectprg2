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

        g.setColor(Color.black);
        g.fillRect((int) (Math.random()*1000),(int) (Math.random()*1000),40,40);

        g.dispose();
        bs.show();
    }

    public int getHeight() {
        return HEIGHT;
    }
    public int getWidth() {
        return WIDTH;
    }
}
