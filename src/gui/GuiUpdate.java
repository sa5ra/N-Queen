/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.MainSup.BlueImage;
import static gui.MainSup.ChessPanel;
import static gui.MainSup.CyanImage;
import static gui.MainSup.GreenImage;
import static gui.MainSup.PinkImage;
import static gui.MainSup.PurpleImage;
import static gui.MainSup.RedImage;
import static gui.MainSup.WhiteImage;
import static gui.MainSup.YellowImage;
import static gui.MainSup.frame;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;


public class GuiUpdate extends SwingWorker {
    String ID;
    int N;
    int i;
    int col;
    boolean done = false;
    int[][] board;
    int Operation;
    
    GuiUpdate(String ID,int n,int i,int col,int[][] board,int OP)
    {
        this.ID = ID;
        this.N =n;
        this.i = i;
        this.col = col;
        this.done = false;
        this.board = board;
        this.Operation = OP;
    }

    @Override
    protected Object doInBackground() throws Exception {
        switch (Operation) {
            case 0:
                if (nqueenT.Safe(board, i, col)) {
                    done = true;
                    Image im = null;
                    switch (ID) {
                        case "White":
                            im = WhiteImage;
                            break;
                        case "Green":
                            im = GreenImage;
                            break;
                        case "Cyan":
                            im = CyanImage;
                            break;
                        case "Purple":
                            im = PurpleImage;
                            break;
                        case "Yellow":
                            im = YellowImage;
                            break;
                        case "Blue":
                            im = BlueImage;
                            break;
                        case "Pink":
                            im = PinkImage;
                            break;
                        case "Red":
                            im = RedImage;
                            break;
                    }
                    Panel CurrentPanel = (Panel) ChessPanel.getComponent(i * N + col);
                    ImageIcon ic = new ImageIcon(im);
                    JLabel imgCont = new JLabel(ic);
                    imgCont.setName(ID);
                    imgCont.setVisible(true);
                    CurrentPanel.add(imgCont);
                    CurrentPanel.revalidate();
                    CurrentPanel.repaint();
                    frame.revalidate();
                    frame.repaint();
                    Thread.sleep(500);
                }   break;
            case 1:
                Panel CurrentPanel = (Panel) ChessPanel.getComponent(i*N + col);
                JLabel TestLabel;
                for (int i = 0; i < CurrentPanel.getComponentCount(); i++) {
                    TestLabel = (JLabel) CurrentPanel.getComponent(i);
                    if (TestLabel.getName().equals(ID)) {
                        CurrentPanel.remove(TestLabel);
                        CurrentPanel.revalidate();
                        CurrentPanel.repaint();
                        frame.revalidate();
                        frame.repaint();
                    }
                }
                Thread.sleep(450);
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    protected void done()
    {
        if (done)
        {
            //System.out.println("Done");
        }
    }
    public boolean returnDone()
    {
        return done;
    }
    
    
    
}
