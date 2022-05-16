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
    private boolean running;
    private Socket socket;
    private Thread thread;
    private view view;

    public Client(){
        view = new view();
        JFrame frame = new JFrame("My Birds");
        frame.setSize(1200,800);
        frame.add(view);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addKeyListener(new KL());
        frame.setVisible(true);


        boolean run = true;
        String ip = "localhost";
        String name = JOptionPane.showInputDialog(null,"Name?","Connect to..",JOptionPane.QUESTION_MESSAGE);
        int port = Integer.parseInt(JOptionPane.showInputDialog(null,"Select a Port\nFor example 4823","Choose port",JOptionPane.QUESTION_MESSAGE));
        id = (int) (Math.random()*100);
        socket = null;


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

            ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), id, view);
            Thread listener = new Thread(in);
            listener.start();

        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.start();
    }
    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {

        }
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
        stop();
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
