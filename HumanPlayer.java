import java.io.*;

public class HumanPlayer extends Player {
    private BufferedReader reader;

    public HumanPlayer(int mark) {
        super(mark);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void makeMove(Board board) {
        while (true) {
            try {
                String input = reader.readLine();

                if (input.equals("q")) {
                    System.out.println("End of the game");
                    quit = true;
                    return;
                }

                int move = Integer.parseInt(input);

                if (move < 1 || move > 9) {
                    System.out.println("Please, input a valid number [1-9]");
                    System.out.println("Player#1's turn");
                    continue;
                }

                if (!board.isFree(move - 1)) {
                    System.out.println("The cell is occupied!");
                    System.out.println("Player#1's turn");
                    continue;
                }

                board.setCell(move - 1, mark);
                break;

            } catch (Exception e) {
                System.out.println("Please, input a valid number [1-9]");
                System.out.println("Player#1's turn");
            }
        }
    }
}