import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;


/**
 *
 * @author magnus
 */
public class Server {
    private int port;

    public static void main(String[] args){
        int port = 5884;
        boolean run = true;
        ServerSocket serverSocket;
        System.out.println("Server started.");

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for connections!");
                serverSocketer serv = new serverSocketer(serverSocket);

                Thread listener = new Thread(serv);
                listener.start();

                System.out.println("Client connected!");
                //Protocol
                while (run) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server fail");
        }
    }

    public Server() {
        port = Integer.parseInt(JOptionPane.showInputDialog(null,"Select a Port\nFor example 4823","Choose port",JOptionPane.QUESTION_MESSAGE));
        boolean run = true;
        ServerSocket serverSocket;
        System.out.println("Server started.");

        try {
            serverSocket = new ServerSocket(port);
                System.out.println("Waiting for connections!");
                serverSocketer serv = new serverSocketer(serverSocket);
                Thread listener = new Thread(serv);
                listener.start();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server fail");
        }

    }

    public int getPort(){
        return port;
    }
}