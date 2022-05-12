import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private static PrintWriter out;
    private static int id;

    public Client(){

    }

    public static void main(String[] args) {
        boolean run = true;
        String ip = "localhost";
        String name = JOptionPane.showInputDialog(null,"Name?","Connect to..",JOptionPane.QUESTION_MESSAGE);
        int port = Integer.parseInt(JOptionPane.showInputDialog(null,"Select a Port\nFor example 4823","Choose port",JOptionPane.QUESTION_MESSAGE));
        id = (int) Math.random()*100;
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
            out = new PrintWriter(socket.getOutputStream(),true);

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

    @Override
    public void run() {

    }

    private class KL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == ' ' || e.getKeyChar() == ' ') {
                out.println(id);
            }
            if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
            }
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }

    }
}
