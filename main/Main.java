

package main;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.Timer;
import java.util.TimerTask;


public class Main
{
    JFrame frame = new JFrame("PLAGUEWARE");
    JPanel panelCont = new JPanel();
    Icon backIcon = new ImageIcon("main/pngFile/BACK.png");
    String nickname = "";
    
    JPanel letsRoll = new JPanel();
    JPanel mainMenu = new JPanel();
    JPanel chooseMode = new JPanel();
    JPanel information = new JPanel();
    JPanel credits = new JPanel();
    JPanel leaderboard = new JPanel();
    JTextArea leaderboardText = new JTextArea();
    ArrayList<User> x = User.LeaderboardToArray();
    JPanel playLogin = new JPanel();
    JPanel endGameWinSaviour = new JPanel();
    JPanel endGameLoseSaviour = new JPanel();
    JPanel endGameWinEndBringer = new JPanel();
    JPanel endGameLoseEndbringer = new JPanel();
    JPanel endGameSimulation = new JPanel();



    
    Icon letsrollicon = new ImageIcon("main/pngFile/letsrollbutton.png");
    JButton letsTOmain = new JButton(letsrollicon);

    Icon playIcon = new ImageIcon("main/pngFile/mainmenuPLAY.png");
    JButton mainTOplayLogin = new JButton(playIcon);
    Icon infoIcon = new ImageIcon("main/pngFile/mainmenuINFOv3.png");
    JButton mainTOInfo = new JButton(infoIcon);
    Icon creditIcon = new ImageIcon("main/pngFile/mainmenuCREDITSv2.png");
    JButton mainTOCredits = new JButton(creditIcon);
    Icon leaderIcon = new ImageIcon("main/pngFile/mainmenuLEADv2.png");
    JButton mainTOLead = new JButton(leaderIcon);
    JButton mainTOlets = new JButton(backIcon);

    
    Icon EndIcon = new ImageIcon("main/pngFile/choosemodeENDv2.png");
    JButton chooseEndBringer = new JButton(EndIcon);
    Icon simIcon = new ImageIcon("main/pngFile/choosemodeSIMUv2.png");
    JButton chooseSimulation = new JButton(simIcon);
    Icon savIcon = new ImageIcon("main/pngFile/choosemodeSAVv2.png");
    JButton chooseSaviour = new JButton(savIcon);
    JButton chooseTOmain = new JButton(backIcon);
    
    
    JButton creditTOmain = new JButton(backIcon);
    JButton leaderTOmain = new JButton(backIcon);
    JButton infoTOmain = new JButton(backIcon);
    JButton logTOmain = new JButton(backIcon);
    JButton endGameToSIMMain = new JButton(backIcon);
    JButton endGameToSWMain = new JButton(backIcon);
    JButton endGameToSLMain = new JButton(backIcon);
    JButton endGameToEWMain = new JButton(backIcon);
    JButton endGameToELMain = new JButton(backIcon);
    


    Icon logIcon = new ImageIcon("main/pngFile/log.png");
    Icon userIcon = new ImageIcon("main/pngFile/username.png");
    JButton playLoginToChooseMode = new JButton(logIcon);
    JLabel userName = new JLabel(userIcon);
    JTextField tfUser = new JTextField(20);



    
    

    CardLayout cl = new CardLayout();

    public Main()
    {
        try 
        {
            panelCont.setLayout(cl);
            
            letsTOmain.setPreferredSize(new Dimension(660,150));
            letsRoll.add(letsTOmain);
            BufferedImage myPicture = ImageIO.read(new File("main/pngFile/letsroll.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            letsRoll.add(picLabel);
            letsRoll.setBackground(Color.BLACK);
            
            mainTOplayLogin.setPreferredSize(new Dimension(350,120));
            mainTOCredits.setPreferredSize(new Dimension(350,120));
            mainTOCredits.setBackground(Color.BLACK);
            mainTOInfo.setPreferredSize(new Dimension(330,120));
            mainTOInfo.setBackground(Color.BLACK);
            mainTOLead.setPreferredSize(new Dimension(570,120));
            mainTOlets.setPreferredSize(new Dimension(95,95));
            mainMenu.add(mainTOplayLogin);
            mainMenu.add(mainTOCredits);
            mainMenu.add(mainTOInfo);
            mainMenu.add(mainTOLead);
            mainMenu.add(mainTOlets);
            BufferedImage myPicture2 = ImageIO.read(new File("main/pngFile/mainmenu.png"));
            JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
            mainMenu.add(picLabel2);
            mainMenu.setBackground(Color.BLACK);

            
            chooseEndBringer.setPreferredSize(new Dimension(600,120));
            chooseEndBringer.setBackground(Color.BLACK);
            chooseSimulation.setPreferredSize(new Dimension(560,120));
            chooseSimulation.setBackground(Color.BLACK);
            chooseSaviour.setPreferredSize(new Dimension(550,120));
            chooseSaviour.setBackground(Color.BLACK);
            chooseTOmain.setPreferredSize(new Dimension(95,95));
            chooseMode.add(chooseEndBringer);
            chooseMode.add(chooseSimulation);
            chooseMode.add(chooseSaviour);
            chooseMode.add(chooseTOmain);
            BufferedImage myPicture3 = ImageIO.read(new File("main/pngFile/choosemodev2.png"));
            JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
            chooseMode.add(picLabel3);
            chooseMode.setBackground(Color.BLACK);

            
            infoTOmain.setPreferredSize(new Dimension(95,95));
            infoTOmain.setBackground(Color.WHITE);
            information.add(infoTOmain,BorderLayout.EAST);
            BufferedImage myPicture4 = ImageIO.read(new File("main/pngFile/info.png"));
            JLabel picLabel4 = new JLabel(new ImageIcon(myPicture4));
            information.add(picLabel4);
            information.setBackground(Color.WHITE);

            
            creditTOmain.setPreferredSize(new Dimension(95,95));
            creditTOmain.setBackground(Color.BLACK);
            credits.add(creditTOmain);
            BufferedImage myPicture5 = ImageIO.read(new File("main/pngFile/creditLast.png"));
            JLabel picLabel5 = new JLabel(new ImageIcon(myPicture5));
            credits.add(picLabel5);
            credits.setBackground(Color.BLACK);


            leaderTOmain.setPreferredSize(new Dimension(95,95));
            leaderTOmain.setBackground(Color.BLACK);
            leaderboard.add(leaderTOmain);
            BufferedImage myPicture6 = ImageIO.read(new File("main/pngFile/leaderboard.png"));
            myPicture6 = myPicture6.getSubimage(0, 0, myPicture6.getWidth(), myPicture6.getHeight()/3);
            JLabel picLabel6 = new JLabel(new ImageIcon(myPicture6));




            
            playLogin.setBackground(Color.BLACK);
            userName.setLabelFor(tfUser);
            userName.setPreferredSize(new Dimension(600,120));
            
            userName.setForeground(Color.RED);
            tfUser.setFont(new Font("Serif", Font.BOLD, 40));
            tfUser.setBackground(Color.RED);
            playLoginToChooseMode.setPreferredSize(new Dimension(300,120));

            playLoginToChooseMode.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    nickname = tfUser.getText();
                }
             });
            playLogin.add(userName);
            playLogin.add(tfUser);
            playLogin.add(playLoginToChooseMode);
            logTOmain.setPreferredSize(new Dimension(95,95));
            playLogin.add(logTOmain);
            playLogin.setBackground(Color.BLACK);
            BufferedImage myPicture7 = ImageIO.read(new File("main/pngFile/login.png"));
            JLabel picLabel7 = new JLabel(new ImageIcon(myPicture7));
            playLogin.add(picLabel7);

            endGameToSWMain.setPreferredSize(new Dimension(95,95));
            endGameWinSaviour.setBackground(Color.WHITE);
            BufferedImage myPicture8 = ImageIO.read(new File("main/pngFile/10.png"));
            JLabel picLabel8 = new JLabel(new ImageIcon(myPicture8));
            endGameWinSaviour.add(endGameToSWMain);
            endGameWinSaviour.add(picLabel8);    
            
            endGameToSLMain.setPreferredSize(new Dimension(95,95));
            endGameLoseSaviour.setBackground(Color.BLACK);
            BufferedImage myPicture9 = ImageIO.read(new File("main/pngFile/11.png"));
            JLabel picLabel9 = new JLabel(new ImageIcon(myPicture9));
            endGameLoseSaviour.add(endGameToSLMain);
            endGameLoseSaviour.add(picLabel9);


            endGameToEWMain.setPreferredSize(new Dimension(95,95));
            endGameWinEndBringer.setBackground(Color.BLACK);
            BufferedImage myPicture10 = ImageIO.read(new File("main/pngFile/12.png"));
            JLabel picLabel10 = new JLabel(new ImageIcon(myPicture10));
            endGameWinEndBringer.add(endGameToEWMain);
            endGameWinEndBringer.add(picLabel10);


            endGameToELMain.setPreferredSize(new Dimension(95,95));
            endGameLoseEndbringer.setBackground(Color.BLACK);
            BufferedImage myPicture11 = ImageIO.read(new File("main/pngFile/13.png"));
            JLabel picLabel11 = new JLabel(new ImageIcon(myPicture11));
            endGameLoseEndbringer.add(endGameToELMain);
            endGameLoseEndbringer.add(picLabel11);

            endGameToSIMMain.setPreferredSize(new Dimension(95,95));
            endGameSimulation.setBackground(Color.BLACK);
            BufferedImage myPicture12 = ImageIO.read(new File("main/pngFile/14.png"));
            JLabel picLabel12 = new JLabel(new ImageIcon(myPicture12));
            endGameSimulation.add(endGameToSIMMain);
            endGameSimulation.add(picLabel12);


            for (User user : x) {
                if(user.toString().length()>0)
                    leaderboardText.append(user.toString());
            }
            leaderboardText.setForeground(Color.RED);
            leaderboardText.setBackground(Color.black);
            leaderboardText.setSize(700, 700);
            leaderboardText.setFont(new Font("Serif", Font.ITALIC, 26));
            JScrollPane j = new JScrollPane(leaderboardText);
            leaderboard.add(picLabel6);
            leaderboard.add(j);
            
            
            
            leaderboard.setBackground(Color.BLACK);


            panelCont.add(letsRoll, "1");
            panelCont.add(mainMenu, "2");
            panelCont.add(playLogin, "0");
            panelCont.add(chooseMode, "3");
            panelCont.add(information, "4");
            panelCont.add(credits, "5");
            panelCont.add(leaderboard, "6");
            panelCont.add(endGameWinSaviour,"100");
            panelCont.add(endGameLoseSaviour,"101");
            panelCont.add(endGameWinEndBringer,"102");
            panelCont.add(endGameLoseEndbringer,"103");
            panelCont.add(endGameSimulation,"104");

            /**/
            
            

           /**/

            
            cl.show(panelCont,"1");
            letsTOmain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            
            mainTOplayLogin.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "0");
                }
            });
            playLoginToChooseMode.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "3");
                }
            });
            mainTOInfo.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "4");
                }
            });
            mainTOCredits.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "5");
                }
            });
            mainTOLead.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "6");
                }
            });
            mainTOlets.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "1");
                }
            });
            chooseTOmain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "0");
                }
            });
            creditTOmain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            infoTOmain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            leaderTOmain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            logTOmain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            endGameToSIMMain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            endGameToSLMain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            endGameToSWMain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            endGameToELMain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });
            endGameToEWMain.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    cl.show(panelCont, "2");
                }
            });

            chooseEndBringer.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    Game endBringer = new Game(new User(nickname),1);
                    panelCont.add(endBringer,"7");
                    cl.show(panelCont, "7");
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            endBringer.start();
                            if(endBringer.hasWon())
                                cl.show(panelCont, "102");
                            else
                            {
                                cl.show(panelCont, "103");
                            }
                        }
                        
                    }, 1000);
                    
                    
                }
            });
            chooseSimulation.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    Game simulation = new Game(new User(nickname),0);
                panelCont.add(simulation,"8");
                    
                    cl.show(panelCont, "8");
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            simulation.start();
                            cl.show(panelCont, "104");
                            
                        }
                        
                    }, 1000);
                }
            });

            chooseSaviour.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    Game saviour = new Game(new User(nickname),2);
                    panelCont.add(saviour,"9");
                    cl.show(panelCont, "9");
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            saviour.start();
                            if(saviour.hasWon())
                                cl.show(panelCont, "100");
                            else
                            {
                                cl.show(panelCont, "101");
                            }
                            
                        }
                        
                    }, 1000);
                    
                            
                    
                    
                }
            });
            
            
            frame.add(panelCont);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setResizable(true); 
            frame.setVisible(true);
        } 
        catch (IOException e) {
             System.err.println("Images are not found!"); }
    }



    public static void main(String[] args) {
        
        new Main();
        

    }
}