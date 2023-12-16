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
