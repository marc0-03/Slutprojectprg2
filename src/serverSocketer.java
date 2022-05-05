import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class serverSocketer implements Runnable{
    ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
    private ArrayList<ListenerThreadServer> list = new ArrayList<ListenerThreadServer>();
    private ArrayList<Thread> Threads = new ArrayList<Thread>();

    ServerSocket serverSocket;

    public serverSocketer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        Socket socket = null;
        PrintWriter temp1 = null;
        BufferedReader temp2 = null;
        while (true) {
            try {
                socket = serverSocket.accept();
                temp1 = new PrintWriter(socket.getOutputStream(), true);
                temp2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                for (ListenerThreadServer s : list) {
                    s.add(temp1);
                }
                list.add(new ListenerThreadServer(temp2, outs));
                outs.add(temp1);
                Threads.add(new Thread(list.get(list.size()-1)));
                Threads.get(Threads.size()-1).start();
                System.out.println("New user connected");
            } catch (IOException e) {
            }

        }
    }
}
