import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DesktopApplication extends JFrame 
{
        public static void main(String[] args)
    {
        // initialize the frame
        JFrame frame = new JFrame("Frame Title");
        frame.setVisible(true);
        int width = 800;
        int height = 400;
        frame.setSize(width, height);

        JLabel usernameLabel = new JLabel("Username: ");
        frame.add(usernameLabel, );
        JLabel passwordLabel = new JLabel("Password: ");
        frame.add(passwordLabel);

    }
}