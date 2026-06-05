public class Tictactoe {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please, input a valid option [1-2]");
            return;
        }

        try {
            int start = Integer.parseInt(args[0]);

            if (start != 1 && start != 2) {
                System.out.println("Please, input a valid option [1-2]");
                return;
            }

            Player human = new HumanPlayer(1);
            Player computer = new ComputerPlayer(2);

            Game game = new Game(human, computer, start);
            game.play();

        } catch (Exception e) {
            System.out.println("Please, input a valid option [1-2]");
        }
    }
}