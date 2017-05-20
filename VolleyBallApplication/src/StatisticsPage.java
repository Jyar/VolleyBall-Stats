
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class StatisticsPage extends JFrame {

    private static Player player;

    int killsCompleted = 0;
    int killsTotal = 0;
    int digsCompleted = 0;
    int digsTotal = 0;
    int servesCompleted = 0;
    int servesTotal = 0;
    // int passesCompleted = 0;
    // int passesTotal = 0;
    int assistsCompleted = 0;
    int assistsTotal = 0;
    int blocksCompleted = 0;
    int blocksTotal = 0;
    int illegalContacts = 0;
    int touchedNetNumber = 0;

    Timer stopwatch;
    boolean running;
    private static int time;
    int seconds = 00;
    int minutes = 00;
    int hours = 00;

    int rowsP1 = 5;
    int rowsPTotals = 2;
    JPanel p1 = new JPanel(new GridLayout(rowsP1, 5, 3, 3));
    JPanel pTotals = new JPanel(new GridLayout(rowsPTotals, 3, 3, 3));

    boolean choseNotAttemptedButtons;
    boolean choseAttemptedButtons;

    ArrayList<JButton> attemptedButtons = new ArrayList<JButton>();
    ArrayList<JButton> notAttemptedButtons = new ArrayList<JButton>();

    public StatisticsPage() {
        super("Statistics");

        JPanel p3 = new JPanel();
        JPanel pTimer = new JPanel(new GridLayout(3, 1, 3, 3));
        JPanel pATimer = new JPanel();

        p3.add(pTimer, BorderLayout.WEST);
        p3.add(pATimer, BorderLayout.CENTER);
        p3.add(pTotals, BorderLayout.EAST);
        add(p1, BorderLayout.CENTER);
        add(p3, BorderLayout.SOUTH);

        // ----------------------
        // MenuBar
        // ----------------------

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu infoTab = new JMenu("More");
        JMenuItem currentPlayer = infoTab.add("Info");
        menuBar.add(infoTab);

        // JMenuItem addButtonAttempts = infoTab.add("With attempts");
        // addButtonAttempts.addActionListener(new ActionListener() {
        //
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // JTextField attemptedButtonName;
        //
        // JOptionPane.showMessageDialog(StatisticsPage.this,
        // attemptedButtonName = new JTextField("Please enter category you wish
        // to add"),
        // "Message", JOptionPane.INFORMATION_MESSAGE);
        //
        // attemptedButtons.add( new JButton(attemptedButtonName.getText()));
        // choseAttemptedButtons= false;
        // // addButtons();
        //
        // }
        //
        // });

        // JMenuItem addButtons = infoTab.add("Without attempts");
        // addButtons.addActionListener(new ActionListener() {
        //
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // JTextField notAttemptedButtonName;
        //
        // JOptionPane.showMessageDialog(StatisticsPage.this,
        // notAttemptedButtonName = new JTextField("Please enter category you
        // wish to add"),
        // "Message", JOptionPane.INFORMATION_MESSAGE);
        //
        // notAttemptedButtons.add( new
        // JButton(notAttemptedButtonName.getText()));
        // choseNotAttemptedButtons = true;
        // //addButtons();
        //
        // }
        // });

        JMenu switchUsers = new JMenu("Done");
        menuBar.add(switchUsers);

        // JMenuItem upload = switchUsers.add("Upload");
        JMenuItem save = switchUsers.add("Save");

        // ----------------------
        // Buttons
        // ----------------------

        JButton kills = new JButton(killsCompleted + "/" + killsTotal);

        JButton digs = new JButton(digsCompleted + "/" + digsTotal);

        JButton serves = new JButton(servesCompleted + "/" + servesTotal);
        // int passesCompleted = 5;
        // int passesTotal = 5;
        // JButton passes = new JButton(passesCompleted + "/" + passesTotal);

        JButton assists = new JButton(assistsCompleted + "/" + assistsTotal);

        JButton blocks = new JButton(blocksCompleted + "/" + blocksTotal);

        JButton illegalBC = new JButton(illegalContacts + "");

        JButton touchedNet = new JButton(touchedNetNumber + "");

        JButton timerLabel = new JButton("00:00:00");

        // ----------------------
        // Disable Numbers
        // ----------------------

        JButton[] array = { kills, serves, blocks, digs, assists, timerLabel,
                illegalBC, touchedNet };

        for (int i = 0; i < 8; i++) {
            array[i].setEnabled(false);
        }

        // ----------------------
        // Buttons
        // ----------------------

        // ----- Kills/points
        JButton points = new JButton("+ kills");
        points.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                killsCompleted++;
                killsTotal++;
                kills.setText(killsCompleted + "/" + killsTotal);

            }
        });

        JButton subtractPoints = new JButton("- kills");
        subtractPoints.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (killsCompleted > 0 && killsTotal > 0) {
                    killsCompleted--;
                    killsTotal--;
                    kills.setText(killsCompleted + "/" + killsTotal);
                }
            }
        });

        JButton missedPoints = new JButton("+ missed kills");
        missedPoints.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                killsTotal++;
                kills.setText(killsCompleted + "/" + killsTotal);
            }
        });

        JButton subtractMissedPoints = new JButton("- missed kills");
        subtractMissedPoints.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (killsTotal > killsCompleted) {
                    killsTotal--;
                    kills.setText(killsCompleted + "/" + killsTotal);
                }
            }
        });

        p1.add(points);
        p1.add(subtractPoints);
        p1.add(missedPoints);
        p1.add(subtractMissedPoints);
        p1.add(kills);
        // -------

        // ----- Digs
        JButton returns = new JButton("+ digs");
        returns.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                digsCompleted++;
                digsTotal++;
                digs.setText(digsCompleted + "/" + digsTotal);
            }
        });

        JButton subtractDigs = new JButton("- digs");
        subtractDigs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (digsCompleted > 0 && digsTotal > 0) {
                    digsCompleted--;
                    digsTotal--;
                    digs.setText(digsCompleted + "/" + digsTotal);
                }
            }
        });

        JButton missedDigs = new JButton("+ missed digs");
        missedDigs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                digsTotal++;
                digs.setText(digsCompleted + "/" + digsTotal);
            }
        });

        JButton subtractMissedDigs = new JButton("- missed digs");
        subtractMissedDigs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (digsTotal > digsCompleted) {
                    digsTotal--;
                    digs.setText(digsCompleted + "/" + digsTotal);
                }
            }
        });

        p1.add(returns);
        p1.add(subtractDigs);
        p1.add(missedDigs);
        p1.add(subtractMissedDigs);
        p1.add(digs);
        // -------

        // ----- Serves
        JButton serve = new JButton("+ serves");
        serve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                servesCompleted++;
                servesTotal++;
                serves.setText(servesCompleted + "/" + servesTotal);
            }
        });

        JButton subtractServes = new JButton("- serves");
        subtractServes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (servesCompleted > 0 && servesTotal > 0) {
                    servesCompleted--;
                    servesTotal--;
                    serves.setText(servesCompleted + "/" + servesTotal);
                }
            }
        });

        JButton missedServes = new JButton("+ missed serves");
        missedServes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                servesTotal++;
                serves.setText(servesCompleted + "/" + servesTotal);
            }
        });

        JButton subtractMissedServes = new JButton("- missed serves");
        subtractMissedServes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (servesTotal > servesCompleted) {
                    servesTotal--;
                    serves.setText(servesCompleted + "/" + servesTotal);
                }
            }
        });

        p1.add(serve);
        p1.add(subtractServes);
        p1.add(missedServes);
        p1.add(subtractMissedServes);
        p1.add(serves);
        // -------

        // ----- Assists
        JButton assist = new JButton("+ assists");
        assist.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                assistsCompleted++;
                assistsTotal++;
                assists.setText(assistsCompleted + "/" + assistsTotal);
            }
        });

        JButton subtractAssists = new JButton("- assists");
        subtractAssists.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (assistsCompleted > 0 && assistsTotal > 0) {
                    assistsCompleted--;
                    assistsTotal--;
                    assists.setText(assistsCompleted + "/" + assistsTotal);
                }
            }
        });

        JButton missedAssists = new JButton("+ missed assists");
        missedAssists.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                assistsTotal++;
                assists.setText(assistsCompleted + "/" + assistsTotal);
            }
        });

        JButton subtractMissedAssists = new JButton("- missed assists");
        subtractMissedAssists.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (assistsTotal > assistsCompleted) {
                    assistsTotal--;
                    assists.setText(assistsCompleted + "/" + assistsTotal);
                }
            }
        });

        p1.add(assist);
        p1.add(subtractAssists);
        p1.add(missedAssists);
        p1.add(subtractMissedAssists);
        p1.add(assists);
        // -------

        // ----- Blocks
        JButton block = new JButton("+ blocks");
        block.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                blocksCompleted++;
                blocksTotal++;
                blocks.setText(blocksCompleted + "/" + blocksTotal);

            }
        });

        JButton subtractBlocks = new JButton("- blocks");
        subtractBlocks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (blocksCompleted > 0 && blocksTotal > 0) {
                    blocksCompleted--;
                    blocksTotal--;
                    blocks.setText(blocksCompleted + "/" + blocksTotal);
                }
            }
        });

        JButton missedBlocks = new JButton("+ missed blocks");
        missedBlocks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                blocksTotal++;
                blocks.setText(blocksCompleted + "/" + blocksTotal);
            }
        });

        JButton subtractMissedBlocks = new JButton("- missed blocks");
        subtractMissedBlocks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (blocksTotal > blocksCompleted) {
                    blocksTotal--;
                    blocks.setText(blocksCompleted + "/" + blocksTotal);
                }
            }
        });

        p1.add(block);
        p1.add(subtractBlocks);
        p1.add(missedBlocks);
        p1.add(subtractMissedBlocks);
        p1.add(blocks);
        // --------------

        // p1.add(passes);

        // ----- Illegal contact
        JButton illegalContact = new JButton("+ illegal contact");
        illegalContact.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                illegalContacts++;
                illegalBC.setText(illegalContacts + "");

            }
        });

        JButton subtractIllegalC = new JButton("- illegal contact");
        subtractIllegalC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (illegalContacts > 0) {
                    illegalContacts--;
                    illegalBC.setText(illegalContacts + "");
                }
            }
        });

        pTotals.add(illegalContact);
        pTotals.add(subtractIllegalC);
        pTotals.add(illegalBC);
        // ----

        // ----- Illegal contact
        JButton touchedN = new JButton("+ touched Net");
        touchedN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                touchedNetNumber++;
                touchedNet.setText(touchedNetNumber + "");

            }
        });

        JButton subtractTouchedNet = new JButton("- touched Net");
        subtractTouchedNet.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (touchedNetNumber > 0) {
                    touchedNetNumber--;
                    touchedNet.setText(touchedNetNumber + "");
                }
            }
        });

        pTotals.add(touchedN);
        pTotals.add(subtractTouchedNet);
        pTotals.add(touchedNet);
        // ----
        // ----- Timer

        class TimerActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                incrementDisplay();

            }

            private void incrementDisplay() {
               
                setTime(getTime() + 1);
                int mins = getTime() / 60;
                int numberOfHours = mins/60;
                int seconds = getTime() % 60;
                int hours = getTime() / 3600;

                if ( mins > numberOfHours){
                        mins = mins - numberOfHours*60;
                }
                
                if (seconds < 10 && mins < 10)
                    timerLabel.setText(
                            "0" + hours + ":0" + mins + ":0" + seconds);

                else if (mins > 10 && seconds < 10)
                    timerLabel.setText(
                            "0" + hours + ":" + mins + ":0" + seconds);
                else if (mins < 10 && seconds > 10)
                    timerLabel.setText(
                            "0" + hours + ":0" + mins + ":" + seconds);
                else if (mins > 10 && seconds > 10)
                    timerLabel
                            .setText("0" + hours + ":" + mins + ":" + seconds);
                else if (mins == 10 && seconds < 10) {
                    timerLabel.setText(
                            "0" + hours + ":" + mins + ":0" + seconds);
                }
                else if (mins == 10 && seconds > 10) {
                    timerLabel
                            .setText("0" + hours + ":" + mins + ":" + seconds);
                }
            }

        }

        stopwatch = new Timer(1000, new TimerActionListener());

        JButton stop = new JButton("stop");
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                stopwatch.stop();

            }
        });

        JButton start = new JButton("start");

        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch.start();

            }

        });
        JButton reset = new JButton("reset");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                hours = 0;
                minutes = 0;
                seconds = 0;
                stopwatch.stop();
                setTime(0);
                timerLabel.setText("00:00:00");
            }
        });

        pTimer.add(start);
        pTimer.add(stop);
        pTimer.add(reset);
        // PATIMER panel Actual Timer
        timerLabel.setPreferredSize(new Dimension(130, 80));
        Font font = new Font("Times New Roman", Font.BOLD, 30);
        timerLabel.setFont(font);
        pATimer.add(timerLabel);
        // ----

        // ----------------------
        // Setting Button Dimensions
        // ----------------------

        JButton[] dimensions = { subtractPoints, points, serves, blocks, kills,
                digs, assists, illegalBC, touchedNet, returns, subtractDigs,
                serve, subtractServes, assist, subtractAssists, block,
                subtractBlocks };

        JButton[] dimensions2 = { subtractMissedPoints, missedPoints,
                missedDigs, subtractMissedDigs, missedServes,
                subtractMissedServes, missedAssists, subtractMissedAssists,
                missedBlocks, subtractMissedBlocks, };

        JButton[] dimensions3 = { illegalContact, subtractIllegalC, touchedN,
                subtractTouchedNet };

        for (int i = 0; i < 17; i++) {
            dimensions[i].setPreferredSize(new Dimension(70, 40));
        }
        for (int i = 0; i < 10; i++) {
            dimensions2[i].setPreferredSize(new Dimension(100, 40));
        }
        for (int i = 0; i < 4; i++) {
            dimensions3[i].setPreferredSize(new Dimension(120, 40));
        }

        // ----------------------
        // Action Listeners
        // ----------------------

        currentPlayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(StatisticsPage.this,
                        "<html>" + "Current player: " + player.getName()
                                + "<br>" + " The date: " + player.getDate()
                                + "<br>" + "The opponent: " + player.getGame()
                                + "</html>",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // -------------------
        // Upload
        // -------------------
        // upload.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        //
        //
        // JFileChooser chooser = new JFileChooser();
        //
        // // makes sure the person presses open
        // int result = chooser.showOpenDialog(StatisticsPage.this);
        // // gets selected file
        // File file = chooser.getSelectedFile();
        //
        //
        //
        // try {
        // Scanner scanner = new Scanner(file);
        // scanner.useDelimiter(",|\n");
        // if (!scanner.hasNext()) {
        //
        // }
        // else if (result == JFileChooser.OPEN_DIALOG) {
        //
        // scanner.next();
        // String Name = scanner.next();
        // player.setName(Name);
        //
        // }
        //
        // scanner.close();
        // }
        // catch (Exception error) {
        //
        // }
        // }
        //
        // });

        // ---------------------
        // Save
        // ---------------------
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // something to invoke executable file choosers
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        JOptionPane.showMessageDialog(StatisticsPage.this,
                                "Please Save your file with the suffix '.html' ",
                                "Message", JOptionPane.OK_OPTION);

                        JFileChooser chooser = new JFileChooser();
                        int result = chooser
                                .showSaveDialog(StatisticsPage.this);
                        // Why is this approve and not Save
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File file = chooser.getSelectedFile();
                            // TODO save data to file
                            try {

                                PrintWriter output = new PrintWriter(file);
                                output.println("<html>" + "<br>"
                                        + "Current player: " + player.getName()
                                        + "<br>" + " The date: "
                                        + player.getDate() + "<br>"
                                        + "The opponent: " + player.getGame()
                                        + "<br>" + "Number of Points: "
                                        + killsCompleted + "/" + killsTotal
                                        + "<br>" + "Number of Digs: "
                                        + digsCompleted + "/" + digsTotal
                                        + "<br>" + "Number of Serves: "
                                        + servesCompleted + "/" + servesTotal
                                        + "<br>" + "Number of Assists: "
                                        + assistsCompleted + "/" + assistsTotal
                                        + "<br>" + "Number of Blocks: "
                                        + blocksCompleted + "/" + blocksTotal
                                        + "<br>"
                                        + "Number of Illegal Ball Contacts: "
                                        + illegalContacts + "<br>"
                                        + "Number of Net Violations: "
                                        + touchedNetNumber + "<br>"
                                        + "</html>");
                                output.close();
                                // "Number of Passes: "+ passesCompleted + "/" +
                                // passesTotal +
                            }
                            catch (Exception e) {
                            }
                        }
                    }

                });
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // //-----------------
    // // Automation
    // //-------------------
    // public void addButtons() {
    // if(choseAttemptedButtons){
    // rowsP1++;
    // for (int i = 0; i< attemptedButtons.size(); i++){
    // p1.add(attemptedButtons.get(i));
    // }
    // choseAttemptedButtons = false;
    // }
    // if(choseNotAttemptedButtons){
    // rowsPTotals++;
    // for (int index = 0; index< attemptedButtons.size(); index++){
    // pTotals.add(notAttemptedButtons .get(index));
    // }
    //
    // choseNotAttemptedButtons = false;
    // }
    //
    // }
    //
    public static int getTime() {
        return time;
    }

    public void setTime(int time) {
        StatisticsPage.time = time;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new StatisticsPage();
                frame.setVisible(true);
                frame.setSize(600, 400);

                player = new Player(NewPlayer.getNameT(), NewPlayer.getDateT(),
                        NewPlayer.getGameT());

            }
        });

    }
}
