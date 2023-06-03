import javax.swing.*;
import java.awt.*;

public class MainGUIPage {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sidebar Example");
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
        sidebarPanel.setBackground(Color.LIGHT_GRAY);

        // Add sidebar buttons or components
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        final int BUTTON_WIDTH = 100;
        final int BUTTON_HEIGHT =  30;
        button1.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button2.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button3.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        sidebarPanel.add(button1);
        sidebarPanel.add(button2);
        sidebarPanel.add(button3);

        // Create welcome panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.BLUE);

        // welcome panel components
        Label welcomeText = new Label("Welcome, user!");
        welcomeText.setAlignment(SwingConstants.CENTER);
        welcomePanel.add(welcomeText);

        // Create the content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        JLabel contentLabel = new JLabel("Content Area");
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(contentLabel, BorderLayout.CENTER);
        
        // Set the preferred sizes
        welcomePanel.setPreferredSize(new Dimension(frame.getWidth() * (5 / 6), (int) (frame.getHeight() * 0.12)));
        sidebarPanel.setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));

        // Add the panels to the main panel
        mainPanel.add(welcomePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // loading the app
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
