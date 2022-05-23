import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class serverSocketer implements Runnable{
    ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
    private ArrayList<ListenerThreadServer> list = new ArrayList<ListenerThreadServer>();
    private ArrayList<Thread> Threads = new ArrayList<Thread>();
    private model model;
    private  ArrayList<ObjectOutputStream> oouts = new ArrayList<ObjectOutputStream>();

    ServerSocket serverSocket;

    public serverSocketer(ServerSocket serverSocket, model model){
        this.serverSocket = serverSocket;
        this.model = model;
    }

    @Override
    public void run() {
        Socket socket = null;
        PrintWriter temp1 = null;
        BufferedReader temp2 = null;
        ObjectOutputStream temp3 = null;
        while (true) {
            try {
                socket = serverSocket.accept();
                temp1 = new PrintWriter(socket.getOutputStream(), true);
                temp2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                temp3 = new ObjectOutputStream(socket.getOutputStream());
                list.add(new ListenerThreadServer(temp2, model));
                outs.add(temp1);
                oouts.add(temp3);
                Threads.add(new Thread(list.get(list.size()-1)));
                Threads.get(Threads.size()-1).start();
                System.out.println("New user connected");
            } catch (IOException e) {

            }
        }
    }
    public void Send(model s){
        for (PrintWriter out: outs){
         out.println(s);
        }
    }
    public void send(ArrayList<Bird> Birds, ArrayList<Obstacle> Pipes) throws IOException {
        for (ObjectOutputStream oout: oouts){
            System.out.println("BIRDS SENDING: "+Birds);
            oout.writeObject(Birds);

            System.out.println("PIPES SENDING: "+Pipes);
            oout.writeObject(Pipes);
        }
    }
}
