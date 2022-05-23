import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.ServerSocket;


public class Controller implements Runnable{
    private Thread thread;
    private boolean running = false;
    private int fps = 60;
    private String title = "";
    private double deltaT;

    //private Render view;
    private model model;
    private int port;
    private serverSocketer serv;

    public Controller() {
        model = new model();

        port = Integer.parseInt(JOptionPane.showInputDialog(null,"Select a Port\nFor example 4823","Choose port",JOptionPane.QUESTION_MESSAGE));
        ServerSocket serverSocket;
        System.out.println("Server started.");

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for connections!");
            serv = new serverSocketer(serverSocket, model);
            Thread listener = new Thread(serv);
            listener.start();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server fail");
        }
    }


    @Override
    public void run() {
        deltaT = 1000.0 / fps;
        long lastTime = System.currentTimeMillis();

        while (running) {
            long now = System.currentTimeMillis();
            if (now - lastTime > deltaT) {
                model.update();
                try {
                    serv.send(model.getBirds(), model.getPipes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                lastTime = now;
            }
        }
        stop();
    }

    public static void main(String[] args) {
        Controller c = new Controller();
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
}
