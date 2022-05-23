import java.io.*;
import java.util.ArrayList;

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
    private ObjectInputStream oin;
    private int i=1;

    public ListenerThread(InputStream in, int id, view view) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(in));
        oin = new ObjectInputStream(in);
        this.id = id;
        this.view = view;
    }

    @Override
    public void run() {
        ArrayList<Bird> birds;
        ArrayList<Obstacle> pipes;
        ArrayList<Object> objects=null;
        while (true) {
            try {
                objects = (ArrayList<Object>) oin.readObject();

                /*
                if (i==1){
                    birds = (ArrayList<Bird>) oin.readObject();
                    System.out.println("BIRDS:"+birds);
                    i=0;
                    view.drawBirds(birds);
                } else {
                    pipes = (ArrayList<Obstacle>) oin.readObject();
                    System.out.println("PIPES:"+pipes);
                    i=1;
                    view.drawPipes(pipes);
                }

                 */

                //Here users will be sent locations of birds and pipes to display?
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("OBJECTS: "+objects);

            //turn msg into cordinates
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