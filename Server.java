import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket =  new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

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
                    output.println("MOVE:" + move);
                }
            }

            socket.close();
            serverSocket.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // machine logic
    private static int findMove(String board) {
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == '0') {
                return i;
            }
        }
        return -1;
    }
}
