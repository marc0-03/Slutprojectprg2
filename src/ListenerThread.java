import java.io.BufferedReader;
import java.io.IOException;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ListenerThread implements Runnable{
    private BufferedReader in;
    private int id;

    public ListenerThread(BufferedReader in, int id) {
        this.in = in;
        this.id = id;
    }

    @Override
    public void run() {
        String msg = null;
        while (true) {
            try {
                msg = in.readLine(); //Here users will be sent locations of birds and pipes to display?
            } catch (IOException e) {
                //e.printStackTrace();
            }
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