import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ListenerThreadServer implements Runnable{
    private ArrayList<PrintWriter> out = new ArrayList<PrintWriter>();
    private BufferedReader in;


    public ListenerThreadServer(BufferedReader in, ArrayList<PrintWriter> out) {
        this.in = in;
        for (PrintWriter p : out) {
            this.out.add(p);
        }
    }
    public ListenerThreadServer() {

    }

    @Override
    public void run() {
        String msg = null;
        while (true) {
                try {
                    msg = in.readLine();
                    for (int x = 0; x < out.size(); x++) {
                        out.get(x).println(msg);
                    }
                    System.out.println(msg);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
    }

    public void stop()  {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(PrintWriter out) {
        this.out.add(out);
    }
}