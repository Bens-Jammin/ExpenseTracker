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

        setTitle("Account Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 4)); // Increased rows to accommodate the checkbox

        // set theme
        int mode = user.getColourScheme();
        String theme = (mode == 0) ? "darkMode" : "babyGirlMode";
        ImageIcon buttonImage = ColourSchemeManager.getButtonImage(theme);
        Color buttonTextColour = ColourSchemeManager.getColor(theme, "buttonText");
        Color mainBackgroundColour = ColourSchemeManager.getColor(theme, "mainBackground");
        Color textColour = ColourSchemeManager.getColor(theme, "textColour");

        getContentPane().setBackground(mainBackgroundColour);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(textColour);
        usernameLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        JTextField usernameField = new JTextField(username);
        usernameField.setForeground(textColour);
        usernameField.setBackground(mainBackgroundColour);
        usernameField.setBorder(BorderFactory.createEmptyBorder()); // Remove the border
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        usernameField.setEditable(false);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColour);
        passwordLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        JTextField passwordField = new JTextField(password);
        passwordField.setForeground(textColour);
        passwordField.setBackground(mainBackgroundColour);
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

        String colourSchemeButtonLabel = (user.getColourScheme() == 0) ? "Dark Mode" : "BabyGirl Mode";
        JButton colourScheme = new JButton(colourSchemeButtonLabel);
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

        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.setIcon(buttonImage);
        deleteAccount.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        deleteAccount.setForeground(buttonTextColour);
        deleteAccount.setVerticalTextPosition(SwingConstants.CENTER);
        deleteAccount.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = "Please confirm you want to delete your account";
                String title = "Confirm Account Deletion";

                int confirmDeleteAccount = JOptionPane.showConfirmDialog(AccountPage.this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (confirmDeleteAccount == JOptionPane.NO_OPTION) {
                    return;
                }

                String deleteMessage = DataManager.deleteAccount(user.getUserName());
                if (deleteMessage == null) {
                    JOptionPane.showMessageDialog(AccountPage.this, "Account deleted successfully.",
                            "ACCOUNT DELETED", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(AccountPage.this, "ERROR: ACCOUNT DELETION FAILED \n" + deleteMessage,
                            "ACCOUNT DELETION FAILED", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JTextArea blankArea = new JTextArea();
        blankArea.setBackground(mainBackgroundColour);
        blankArea.setEditable(false);

        JCheckBox staySignedInCheckbox = new JCheckBox("Stay Signed In"); // Checkbox for "Stay Signed In"
        staySignedInCheckbox.setBackground(mainBackgroundColour);
        staySignedInCheckbox.setForeground(textColour);
        
        String lastUsername = DataManager.loadLastSignedUser();
        if(user.getUserName().equals(lastUsername)){
            staySignedInCheckbox.setSelected(true);
        }

        staySignedInCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean staySignedIn = staySignedInCheckbox.isSelected();
                if (!staySignedIn) {
                    DataManager.saveNewLastUser(null);
                    return;
                }

                String lastUser = DataManager.loadLastSignedUser();
                if(lastUser == null){
                    DataManager.saveNewLastUser(user.getUserName());
                    return;
                }

                String message = "Another user is already saved to be signed in, do you want to override?";
                String title = "Confirm override sign in";

                int confirm = JOptionPane.showConfirmDialog(AccountPage.this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (confirm == JOptionPane.NO_OPTION) {
                    return;
                }

                DataManager.saveNewLastUser(user.getUserName());

            }
        });

        // Add components to the JFrame
        add(usernameLabel);
        add(usernameField);
        add(blankArea);
        add(colourScheme);

        add(passwordLabel);
        add(passwordField);
        add(blankArea);
        add(exportButton);

        add(blankArea);
        add(staySignedInCheckbox); // Add the "Stay Signed In" checkbox
        add(blankArea);
        add(deleteAccount);

        setLocationRelativeTo(null);
        pack(); // Adjusts the size of the frame to fit the components
        setVisible(true);
    }
}
