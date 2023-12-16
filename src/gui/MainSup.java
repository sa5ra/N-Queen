/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class MainSup {
    static JPanel ChessPanel;
    static Image RedImage;
    static Image GreenImage;
    static Image WhiteImage;
    static Image PurpleImage;
    static Image BlueImage;
    static Image CyanImage;
    static Image YellowImage;
    static Image PinkImage;
    static JFrame frame;
    public static void main(String args[]) throws InterruptedException, InvocationTargetException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        //</editor-fold>
        /* Create and display the form */
        CreateGUI();
        
    }
    static void CreateGUI()
    {
        frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(86, 86, 86));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        JButton Submit = new JButton("Submit");
        Submit.setBackground(new Color(45, 175, 0));
        Submit.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        Submit.setBounds(1400, 500, 200, 45);
        JTextField tx1 = new JTextField("Enter N : ");
        tx1.setForeground(Color.WHITE);
        tx1.setBackground(new Color(86, 86, 86));
        tx1.setBounds(1350, 460, 300, 30);
        tx1.setBorder(BorderFactory.createCompoundBorder(tx1.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        frame.add(tx1);
        frame.add(Submit);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        tx1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tx1.setText("");
            }
        });
        Submit.addActionListener((ActionEvent e) -> {
            final int n = Integer.parseInt(tx1.getText());
            SwingWorker B = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    if (ChessPanel != null) {
                        frame.remove(ChessPanel);
                    }
                    FetchInput(n, 400);
                    ChessPanel = new JPanel();
                    ChessPanel.setSize(700, 700);
                    ChessPanel.setBackground(Color.red);
                    ChessPanel.setBorder(new LineBorder(Color.black, 3));
                    int color = (Math.random() <= 0.5) ? -1 : 1;
                    Panel NewPanel;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            NewPanel = new Panel();
                            NewPanel.setLayout(new GridLayout(2, 2));
                            if (color == -1) {
                                color = 1;
                                NewPanel.setBackground(new Color(159, 69, 34));
                            } else {
                                color = -1;
                                NewPanel.setBackground(new Color(232, 210, 159));
                            }
                            ChessPanel.add(NewPanel);
                        }
                        if (n % 2 == 0) {
                            color *= -1;
                        }

                    }
                    ChessPanel.setLayout(new GridLayout(n, n));
                    frame.add(ChessPanel);
                    frame.revalidate();
                    frame.repaint();
                    Thread.sleep(200);
                    return true;
                }

                @Override
                protected void done() {
                    Nqueens.StartAl(n);
                }
            };
            B.execute();
        });
    }
    static void FetchInput(int n,int size) throws IOException
    {
        RedImage = ImageIO.read(new File("Queens\\Red.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
        BlueImage = ImageIO.read(new File("Queens\\Blue.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
        CyanImage = ImageIO.read(new File("Queens\\Cyan.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
        WhiteImage = ImageIO.read(new File("Queens\\White.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
        YellowImage = ImageIO.read(new File("Queens\\Yellow.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
        PinkImage = ImageIO.read(new File("Queens\\Pink.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
        GreenImage = ImageIO.read(new File("Queens\\Green.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
        PurpleImage = ImageIO.read(new File("Queens\\Purple.png")).getScaledInstance(size / n, size / n, java.awt.Image.SCALE_SMOOTH);
    }
    
}
