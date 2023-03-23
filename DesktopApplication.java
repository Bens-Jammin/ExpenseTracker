import javax.swing.*;
import java.awt.event.*;

public class DesktopApplication extends JFrame implements ActionListener {
    JLabel userLabel, passwordLabel;
    JTextField userTextField;
    JPasswordField passwordField;
    JButton loginButton, resetButton, signUpButton;

    public DesktopApplication() {
        setTitle("Login Page");
        userLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        signUpButton = new JButton("Sign Up");

        setLayout(null);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 200, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 200, 150, 30);
        loginButton.setBounds(50, 250, 100, 30);
        signUpButton.setBounds(200, 250, 100, 30);
        resetButton.setBounds(125, 300, 100, 30);

        add(userLabel);
        add(passwordLabel);
        add(userTextField);
        add(passwordField);
        add(loginButton);
        add(resetButton);
        add(signUpButton);

        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        signUpButton.addActionListener(this);

        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = new String(passwordField.getPassword());
            User user = DataManager.loadUser(userText);
            if (user.attemptSignin(userText, pwdText)) {
                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == signUpButton) {

            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = new String(passwordField.getPassword());
            if(!DataManager.isUserNameAvailable(userText)){
                JOptionPane.showMessageDialog(this, "ERROR: Username is not available.");
                return;
            }
            
            User user = new User(userText, pwdText);
            boolean successfullySaved = DataManager.saveUser(user);
            if(successfullySaved){
                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
                JOptionPane.showMessageDialog(this, "Signup successful.");
            }
        }
    }

    public static void main(String[] args) {
        new DesktopApplication();
    }
}