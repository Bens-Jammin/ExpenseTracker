import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPage extends JFrame {

    public MainPage(User user) {
        // Set the title of the main page
        setTitle("Expense Tracker");
        // Set the size of the main page
        setSize(500, 500);
        // Center the main page on the screen
        setLocationRelativeTo(null);
        // Make the main page visible
        setVisible(true);
        // Set the layout of the main page to BorderLayout
        setLayout(new BorderLayout());

        // Create a label to display the welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUserName() + "!");
        // Set the font size of the welcome label to 24
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        // Set the alignment of the welcome label to center
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a container to hold the progress bar
        JPanel progressBarContainer = new JPanel();
        // Set the layout of the container to FlowLayout
        progressBarContainer.setLayout(new FlowLayout());
        // Set the preferred size of the container to 50% of the screen width and 10px tall
        progressBarContainer.setPreferredSize(new Dimension(250, 20));

        // Create a progress bar to show the expenses vs budget progress
        JProgressBar progressBar = new JProgressBar(0, (int) user.getBudget());
        // Set the value of the progress bar to the total expenses of the user
        progressBar.setValue((int) user.getTotalExpenses());
        // Set the orientation of the progress bar to horizontal
        progressBar.setOrientation(SwingConstants.HORIZONTAL);
        // Set the size of the progress bar to fill the container
        progressBar.setPreferredSize(progressBarContainer.getPreferredSize());

        // Add the progress bar to the container
        progressBarContainer.add(progressBar);

        // Add the welcome label and container with progress bar to the main page
        add(welcomeLabel, BorderLayout.NORTH);
        add(progressBarContainer, BorderLayout.CENTER);

        // Create a container to hold the buttons
        JPanel buttonContainer = new JPanel();
        // Set the layout of the container to GridLayout with 4 rows and 1 column
        buttonContainer.setLayout(new GridLayout(4, 1));

        // Create buttons for the different options
        JButton viewAllExpensesButton = new JButton("View All Expenses");
        JButton viewAllBillsButton = new JButton("View All Bills");
        JButton showAdditionalInfoButton = new JButton("Show Additional Information");
        JButton settingsButton = new JButton("Settings");

        // Add the buttons to the button container
        buttonContainer.add(viewAllExpensesButton);
        buttonContainer.add(viewAllBillsButton);
        buttonContainer.add(showAdditionalInfoButton);
        buttonContainer.add(settingsButton);

        // Add the button container to the left side of the main page
        add(buttonContainer, BorderLayout.WEST);
    }
}
