import javax.swing.*;

public class GUIDisplayManager extends JFrame {

    private JButton loginButton;
    private JButton signupButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

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

        // Add your login validation logic here
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login successful");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    private void handleSignup() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Add your signup logic here

        JOptionPane.showMessageDialog(this, "Signup successful");
    }

    public static void main(String[] args) {
        new GUIDisplayManager();
    }
}
