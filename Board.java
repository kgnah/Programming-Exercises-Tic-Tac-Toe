public class Board {
    private int[] cells = new int[9];

    public int[] getCells() {
        return cells;
    }

    public boolean isFree(int i) {
        return cells[i] == 0;
    }

    public void setCell(int i, int mark) {
        cells[i] = mark;
    }

    public boolean isFull() {
        for (int c : cells)
            if (c == 0) return false;
        return true;
    }

    public boolean checkWin(int player) {
        int[][] win = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] w : win) {
            if (cells[w[0]] == player &&
                    cells[w[1]] == player &&
                    cells[w[2]] == player)
                return true;
        }
        return false;
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print("| " + cells[i] + " ");
            if ((i + 1) % 3 == 0)
                System.out.println("|");
        }
        System.out.println();
    }
}