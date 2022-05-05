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
    private String id;

    public ListenerThread(BufferedReader in, String id) {
        this.in = in;
        this.id = id;
    }

    @Override
    public void run() {
        String msg = null;
        while (true) {
            try {
                msg = in.readLine();
            } catch (IOException e) {
                //e.printStackTrace();
            }
            if (msg.contains(": [")) {
                String checkid = msg.substring(msg.indexOf("[")+1);
                checkid = checkid.substring(0, checkid.indexOf("]"));
                if (checkid.equals(id)) {
                    System.out.println("Private message from " + msg.substring(0,msg.indexOf(": ")+2) + msg.substring(msg.indexOf("]")+1));
                }
            } else {
                System.out.println(msg);
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
}