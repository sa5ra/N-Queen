/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author karem
 */
public class Safe implements Runnable {

    private final int C;
    private final int R;
    private final int N;
    int[][] board;

    private boolean t = true;

    public Safe(int board[][], int C, int R, int N) {
        this.board = board;
        this.C = C;
        this.R = R;
        this.N = N;
    }

    @Override
    public void run() {
        String ThreadName = Thread.currentThread().getName();
        for (int i = 0; i < C; i++) {
                if (board[R][i] == 1) {
                    t = false;
                }
            }
        for (int i = C + 1; i < N; i++) {
            if (board[R][i] == 1) {
                t = false;
            }
        }
        for (int i = R, j = C; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                t = false;
            }
        }
        for (int i = R, j = C; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                t = false;
            }
        }
        for (int i = R, j = C; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                t = false;
            }
        }
        for (int i = R, j = C; j < N && i < N; i++, j++) {
            if (board[i][j] == 1) {
                t = false;
            }
        }

    }

    public boolean RT() {
        return t;
    }
}
