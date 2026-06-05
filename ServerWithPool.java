import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServerWithPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server with thread pool started...");
            System.out.println("Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                pool.execute(new GameHandler(socket));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
