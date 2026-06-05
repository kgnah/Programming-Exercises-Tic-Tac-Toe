public class ComputerPlayer extends Player {

    public ComputerPlayer(int mark) {
        super(mark);
    }

    @Override
    public void makeMove(Board board) {
        for (int i = 0; i < 9; i++) {
            if (board.isFree(i)) {
                board.setCell(i, mark);
                break;
            }
        }
    }
}