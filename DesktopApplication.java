import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DesktopApplication extends JFrame {

    private JPanel contentPane;

    public DesktopApplication() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 200);
        setTitle("Hello, World!");

        contentPane = new JPanel();
        setContentPane(contentPane);

        JLabel label = new JLabel("Testing desktop application");
        contentPane.add(label);
    }

    public static void main(String[] args) {
        DesktopApplication frame = new DesktopApplication();
        frame.setVisible(true);
    }
}