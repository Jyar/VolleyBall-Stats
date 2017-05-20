
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Start extends JFrame {

    public Start() {
        super("VolleyBall Statistics");

        JPanel startingPanel = new JPanel();
        startingPanel.setLayout(new GridLayout(2,1));
        startingPanel.setBackground(Color.ORANGE);
        add(startingPanel);

      
        
        JButton newPlayer = new JButton("Start");
        newPlayer.setPreferredSize(new Dimension(10, 10));
        startingPanel.add(newPlayer);
        
        JButton about = new JButton("About");
        about.setPreferredSize(new Dimension(10, 10));
        startingPanel.add(about);

//        JButton existingPlayer = new JButton("Existing Player");
//        startingPanel.add(existingPlayer);

        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(Start.this,
                        new JLabel("<html>" + "<hr> "
                                + "<b>VolleyBall Statistics</b><br>by Jordan Yarros"
                                + "<hr></html>"),
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        newPlayer.addActionListener(new ActionListener() {

            @SuppressWarnings("static-access")
            @Override
            public void actionPerformed(ActionEvent e) {
                NewPlayer newPlayerFrame = new NewPlayer();
                newPlayerFrame.main(null);
                // System.exit(0);
                dispose();
            }
        });

//        existingPlayer.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO Auto-generated method stub
//
//            }
//        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        JFrame frame = new Start();
        frame.setVisible(true);
        frame.setSize(190, 160);
    }
}
