import org.junit.Test;

public CheckTest{
    public static void main(String[] args) {

        jh61b.junit.textui.runClasses(CheckTest.class);    

    }

    public static class SpyBoard extends Board {
        public static int selectCount = 0;
        public static int canSelectCount = 0;

        public SpyBoard(boolean blank) {
            super(blank);
        }
    }

    /* Special class that spies on your game. */
    public static class SpyPiece extends Piece {
        public static int moveCount = 0;

        public SpyPiece(boolean isFire, Board b, int x, int y, String type) {
            super(isFire, b, x, y, type);
        }
    }

}