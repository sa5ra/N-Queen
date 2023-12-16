
package gui;

public class Nqueens {
    public static void StartAl(int N)
    {
        for (int i = 0; i < 4; i++) { // i -> id
            String s;
            switch (i) {
                case 0:
                    s = "White";
                    break;
                case 1:
                    s = "Green";
                    break;
                case 2:
                    s = "Cyan";
                    break;
                default:
                    s = "Purple";
                    break;
            }
            nqueenT o = new nqueenT(N, s, nqueenT.R(N), nqueenT.R(N));
            Thread thread = new Thread(o, s);
            System.out.println(s + " STARTED EXEUCTING");
            thread.start();
        }
    }
}
