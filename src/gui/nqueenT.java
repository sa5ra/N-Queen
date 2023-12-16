
package gui;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;


public class nqueenT implements Runnable {

    private final String ID;
    private static int N;
    private final int R;
    private final int C;
    public static Lock key = new ReentrantLock();
    public volatile static boolean AnswerFound = false;
    public nqueenT(int N, String ID, int R, int C) {
        nqueenT.N = N;
        this.ID = ID;
        this.R = R;
        this.C = C;
        
    }
    public static int R(int N) {
        return (int) (Math.random() * N);
    }

    public static boolean Safe(int board[][], int row, int col) throws InterruptedException {
        Safe safeL = new Safe(board, col, row, N);
        Thread Left = new Thread(safeL, "White");
        Left.start();
        try {
            Left.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(nqueenT.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (safeL.RT())
        {
            return true;
        }
        return false;

    }

    @SuppressWarnings("empty-statement")
    boolean NQRecur(int board[][], int col, int CC, int row, int CR) throws IOException, InterruptedException, InvocationTargetException, ExecutionException {
        if (AnswerFound)
        {
            return false;
        }
        if (CC >= N ) {                 // CC Number of Queens Placed
            if(key.tryLock())
            {
                return true;
            }
            System.out.println(ID + "Tried to aqcuire lock");
            return false;
        }  
        col = (col) % N;
        for (int i = row; CR < N & !AnswerFound; i++, CR++) {
            i %= N;
            GuiUpdate g = new GuiUpdate(ID,N,i,col,board,0);
            g.execute();
            g.get();
            try {
                if (g.returnDone())
                {
                    board[i][col] = 1;
                    if (NQRecur(board, col + 1, CC + 1, 0, 0) == true) {
                        return true;
                    }
                    //Failed
                    g = new GuiUpdate(ID,N,i,col,board,1);
                    g.execute();
                    g.get();
                    board[i][col] = 0; // BACKTRACK
                }                
            } catch (InterruptedException ex) {
                Logger.getLogger(nqueenT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /* If the queen can not be placed in any row in
		this colum col, then return false */
        
        return false;
    }

    boolean StartSolving(int board[][]) throws IOException, InterruptedException, InvocationTargetException, ExecutionException {
        System.out.println("-------------------"+ ID + " " + "Beginning Of Execution ---------------------");
        if (NQRecur(board, C, 0, R, 0) == false) {
            System.out.println("Solution does not exist");
            return false;
        }
        
        if (!AnswerFound)
        {   
            AnswerFound = true;
            key.unlock();
            //printSolution(board);
        }
        else
        {
            System.out.println(ID + " Allegedly Stopped");
        }
        return true;
    }

//    void printSolution(int board[][]) {
//        System.out.println(this.ID + " Found solution\n");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(" " + board[i][j]
//                        + " ");
//            }
//            System.out.println();
//        }
//    }
    
    @Override

    public void run() {
        AnswerFound = false;
        Board NewBoard;
        NewBoard = new Board(N);
        try {
            try {
                try {
                    StartSolving(NewBoard.getBoard());
                } catch (ExecutionException ex) {
                    Logger.getLogger(nqueenT.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InvocationTargetException ex) {
                Logger.getLogger(nqueenT.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(nqueenT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
