import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static Board board = new Board();

    public static void main(String[] args) {
        try {
            Board board = new Board();

            Socket socket = new Socket("localhost", 5000);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Connected to server");

            while (true) {
                board.display();

                System.out.println("Enter move (1-9) or 0 to quit: ");
                int move = scanner.nextInt() - 1;

                //quit
                if (move == -1) {
                    break;
                }

                // invalid move
                if (move < 0 || move > 8 || board.getCells()[move] != 0) {
                    System.out.println("Invalid move!");
                    continue;
                }

                // human move
                board.setCell(move, 1);

                // check human win
                if (board.checkWin(1)) {
                    board.display();
                    System.out.println("Player 1 won!");

                    output.println("GAME_OVER");
                    break;
                }

                // check draw
                if (board.isFull()) {

                    board.display();
                    System.out.println("It is a draw!");

                    output.println("GAME_OVER");
                    break;
                }

                // send board to server
                output.println("BOARD:" + encodeBoard(board));

                // receive computer move
                String response = input.readLine();

                //debug line
                System.out.println("Server response: " + response);

                if (response.startsWith("MOVE:")) {
                    int computerMove =  Integer.parseInt(response.substring(5).trim());

                    if (computerMove != -1) {
                        board.setCell(computerMove, 2);

                        // check computer win
                        if (board.checkWin(2)) {

                            board.display();
                            System.out.println("Player 2 won!");

                            output.println("GAME_OVER");
                            break;
                        }

                        // check draw
                        if (board.isFull()) {

                            board.display();
                            System.out.println("It is a draw!");

                            output.println("GAME_OVER");
                            break;
                        }
                    }
                }
            }

            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert board to string
    private static String encodeBoard(Board board) {

        StringBuilder sb = new StringBuilder();

        for (int cell : board.getCells()) {
            sb.append(cell);
        }

        return sb.toString();
    }
}
