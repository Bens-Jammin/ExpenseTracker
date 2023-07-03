import structures.*;

import javax.swing.*;

import handlers.DataManager;
import handlers.ColourSchemeManager;

import java.awt.*;
import java.awt.event.*;

public class AccountPage extends JFrame {
    private String username;
    private String password;


    public AccountPage(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();

        // Set up the JFrame
        setTitle("Account Page");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        
        // set theme
        int mode = user.getColourScheme();
        String theme = (mode == 0) ? "darkMode" : "babyGirlMode";
        ImageIcon buttonImage = ColourSchemeManager.getButtonImage(theme);
        Color buttonTextColour = ColourSchemeManager.getColor(theme, "buttonText");
        Color mainBackroundColour = ColourSchemeManager.getColor(theme, "mainBackground");
        Color textColour = ColourSchemeManager.getColor(theme, "textColour");

        
        getContentPane().setBackground(mainBackroundColour);


        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(textColour);
        usernameLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        JTextField usernameField = new JTextField(username);
        usernameField.setForeground(textColour);
        usernameField.setBackground(mainBackroundColour);
        usernameField.setBorder(BorderFactory.createEmptyBorder()); // Remove the border
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        usernameField.setEditable(false);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColour);
        passwordLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        JTextField passwordField = new JTextField(password);
        passwordField.setForeground(textColour);
        passwordField.setBackground(mainBackroundColour);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setEditable(false);

        final int BUTTON_WIDTH = 120;
        final int BUTTON_HEIGHT = 30;

        // Export Button
        JButton exportButton = new JButton("Export to CSV");
        exportButton.setIcon(buttonImage);
        exportButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        exportButton.setForeground(buttonTextColour);
        exportButton.setVerticalTextPosition(SwingConstants.CENTER);
        exportButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataManager.convertUserToCSV(user);
            }
        });

        JButton colourScheme = new JButton("Dark Mode");
        colourScheme.setIcon(buttonImage);
        colourScheme.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        colourScheme.setForeground(buttonTextColour);
        colourScheme.setVerticalTextPosition(SwingConstants.CENTER);
        colourScheme.setHorizontalTextPosition(SwingConstants.CENTER);
        colourScheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (colourScheme.getText()) {
                    case "Dark Mode":
                        user.setColourScheme(User.BABYGIRL_MODE);
                        colourScheme.setText("BabyGirl Mode");
                        break;
                        
                    case "BabyGirl Mode":
                        user.setColourScheme(User.DARK_MODE);
                        colourScheme.setText("Dark Mode");
                        break;
                }
                DataManager.saveUser(user);
            }
        });


        // Add components to the JFrame
        
        add(usernameLabel); add(usernameField);
        add(passwordLabel); add(passwordField); 
        add(exportButton);  add(colourScheme); 
        
        

        setLocationRelativeTo(null);
        // Display the JFrame
        setVisible(true);
    }
}
