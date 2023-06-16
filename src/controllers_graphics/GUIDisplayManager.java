import javax.swing.*;

import handlers.Display;
import structures.User;

public class GUIDisplayManager extends JFrame {

    private User user = null;

    private JButton loginButton;
    private JButton signupButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private static final String NERD_EMOJI = "\uD83E\uDD13";

    public GUIDisplayManager() {
        setTitle("Login or Signup");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());

        signupButton = new JButton("Signup");
        signupButton.addActionListener(e -> handleSignup());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if(username == "" || password == ""){
            JOptionPane.showMessageDialog(this, "You need to enter a username and password!!!\n" + NERD_EMOJI, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println(username + " " +  password);

        user = handlers.Display.login(username, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "You entered your username or password wrong!!\n" + NERD_EMOJI, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        openMainPage(user);
        
    }

    private void handleSignup() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if(username == "" || password == ""){
            user = Display.createAccount(username, password);
        }else{
            JOptionPane.showMessageDialog(this, "You need to enter a username and password!!!\n" + NERD_EMOJI, "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Welcome " + user.getUserName() + "!");
            openMainPage(user);
        }else{
            JOptionPane.showMessageDialog(this, "There was an error in your credentials, try again!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openMainPage(User user) {
        // Open the main page
        new MainGUIPage(user);
        
        // Close the current GUI window
        dispose();
    }

    public static void main(String[] args) {
        new GUIDisplayManager();
    }
}
