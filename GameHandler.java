import java.io.*;
import java.net.*;

public class GameHandler implements Runnable {

    private Socket socket;

    public GameHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Client connected: " + Thread.currentThread().getName());

            while (true) {

                String message = input.readLine();

                if (message == null) {
                    break;
                }

                if (message.equals("GAME_OVER")) {
                    break;
                }

                if (message.startsWith("BOARD:")) {

                    String board = message.substring(6);

                    int move = findMove(board);

                    System.out.println("Received: " + message);
                    System.out.println("Sending move: " + move);

                    output.println("MOVE:" + move);
                }
            }

            socket.close();

            System.out.println("Client disconnected: " + Thread.currentThread().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // computer move logic
    private int findMove(String board) {

        for (int i = 0; i < board.length(); i++) {

            if (board.charAt(i) == '0') {
                return i;
            }
        }

        return -1;
    }
}