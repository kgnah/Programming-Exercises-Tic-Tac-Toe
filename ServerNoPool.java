import java.io.*;
import java.net.*;

public class ServerNoPool {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                Thread gameThread = new Thread(new GameHandler(socket));
                gameThread.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
