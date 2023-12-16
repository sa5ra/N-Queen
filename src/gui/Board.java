
package gui;


public class Board {

    private final int N;
    private int[][] Board;

    public Board(int N) {
        this.N = N;
        this.Board = new int[N][N];
    }

    public int[][] getBoard() {
        return this.Board;
    }

}
