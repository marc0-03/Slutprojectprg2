import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        boolean run = true;
        String ip = "localhost";
        String name = JOptionPane.showInputDialog(null,"Name?","Connect to..",JOptionPane.QUESTION_MESSAGE);
        int port = Integer.parseInt(JOptionPane.showInputDialog(null,"Select a Port\nFor example 4823","Choose port",JOptionPane.QUESTION_MESSAGE));

        Socket socket = null;

        try {
            socket = new Socket(ip,port);
        } catch (IOException e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        try {
            Scanner tgb = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), id);
            Thread listener = new Thread(in);
            listener.start();

            while (run) {
                String msg;
                msg = tgb.nextLine();

                out.println(name + ": " + msg);

            }

            out.close();
            socket.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }
}
