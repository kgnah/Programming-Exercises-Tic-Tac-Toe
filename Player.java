public abstract class Player {
    protected int mark;
    protected boolean quit = false;

    public boolean hasQuit() {
        return quit;
    }

    public Player(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public abstract void makeMove(Board board);
}