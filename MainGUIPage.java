import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUIPage {

    public MainGUIPage(User user) {

        String title = user.getUserName() + " Transaction Account";
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.decode("#3f3f3f"));

        // Add sidebar buttons or components
        JButton button1 = new JButton("View Transactions");
        JButton button2 = new JButton("Create CSV");
        JButton button3 = new JButton("Logout");
        final int BUTTON_WIDTH = 120;
        final int BUTTON_HEIGHT =  30;
        button1.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button2.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button3.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        ImageIcon buttonImage = new ImageIcon("buttn.png");
        button1.setIcon(buttonImage);
        button2.setIcon(buttonImage);
        button3.setIcon(buttonImage);
        sidebarPanel.add(button1, BorderLayout.CENTER);
        sidebarPanel.add(button2);
        sidebarPanel.add(button3);

        // Create the content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.decode("#232323"));
        JLabel contentLabel = new JLabel("Content Area");
        Font textFont = new Font("Roboto",0 ,24);
        contentLabel.setFont(textFont);
        contentLabel.setForeground(Color.WHITE);
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER); // TODO: put this a couple dozen pixels down
        contentLabel.setBorder(BorderFactory.createEmptyBorder(48, 0, 0, 0));
        contentPanel.add(contentLabel, BorderLayout.NORTH);

        // Set the preferred sizes
        sidebarPanel.setPreferredSize(new Dimension(frame.getWidth() / 5, frame.getHeight()));
        contentPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        // Add the panels to the main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // Button logic
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for button1 (View All Transactions)
                contentLabel.setText("View All Transactions button clicked");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for button2 (Create CSV)
                contentLabel.setText("Create CSV button clicked");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for button3 (Logout)
                contentLabel.setText("Logout button clicked");
            }
        });

        // loading the app
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
