import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ListenerThread implements Runnable{
    private ObjectInputStream ois;
    private BufferedReader in;
    private int id;
    private view view;

    public ListenerThread(BufferedReader in, int id, view view) {
        this.in = in;
        this.id = id;
        this.view = view;
    }

    @Override
    public void run() {
        Object msg = null;
        while (true) {
            try {
                msg = ois.readObject();
                //Here users will be sent locations of birds and pipes to display?
            } catch (IOException | ClassNotFoundException e) {
                //e.printStackTrace();
            }

            //turn msg into cordinates
            view.draw();



            System.out.println(msg);
        }
    }

    public void stop()  {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}