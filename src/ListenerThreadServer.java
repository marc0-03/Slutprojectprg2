import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ListenerThreadServer implements Runnable{
    private BufferedReader in;
    private int id;
    private boolean firstmessage=true;
    private model model;


    public ListenerThreadServer(BufferedReader in, model model) {
        this.in = in;
        this.model = model;
    }

    @Override
    public void run() {
        String msg = null;
        int id;
        while (true) {
                try {
                    msg = in.readLine();
                    id = Integer.parseInt(msg);

                    if (firstmessage){
                        firstmessage=false;
                        model.CreateBird(id);
                        //create bird
                    }
                    model.JumpBird(id);
                    //jump bird with id = msg

                    //When you get the command to jump take the ID and jump that bird.

                    /*
                    for (int x = 0; x < out.size(); x++) {
                        out.get(x).println(msg);
                    }
                    System.out.println(msg);
                     */
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
}