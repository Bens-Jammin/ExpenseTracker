import structures.*;

import javax.swing.*;

import handlers.DataManager;

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

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(username);
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        usernameField.setEditable(false);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(password);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setEditable(false);

        // Export Button
        JButton exportButton = new JButton("Export to CSV");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataManager.convertUserToCSV(user);
            }
        });

        JButton colourScheme = new JButton("Dark Mode");
        colourScheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (colourScheme.getText()) {
                    case "Dark Mode":
                        user.setColourScheme(User.DARK_MODE);
                        colourScheme.setText("BabyGirl Mode");
                        break;
                        
                    case "BabyGirl Mode":
                        user.setColourScheme(User.BABYGIRL_MODE);
                        colourScheme.setText("Dark Mode");
                        break;
                }
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
