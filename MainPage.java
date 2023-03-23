import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame implements ActionListener 
{
    private JLabel welcomeLabel;
    private JLabel amountLabel;
    private JTextField amountField;
    private JLabel categoryLabel;
    private JComboBox<String> categoryComboBox;
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton showAllButton;
    private JButton logoutButton;
    private JTextArea expenseList;

    private String[] categories = {"Food", "Entertainment", "Transportation", "Shopping", "Other"};

    public MainPage(String username)
    {
        setTitle("Expense Tracker");

        // Set up UI components
        String welcomeScreen = "Welcome, "+username+"!";
        welcomeLabel = new JLabel(welcomeScreen, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);
        categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<String>(categories);
        addButton = new JButton("Add Expense");
        editButton = new JButton("Edit Expense");
        removeButton = new JButton("Remove Expense");
        showAllButton = new JButton("Show All Expenses");
        logoutButton = new JButton("Logout");
        expenseList = new JTextArea(10, 30);

        // Add components to the content pane
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new GridLayout(1,1));
        northPanel.add(welcomeLabel);
        c.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(amountLabel);
        centerPanel.add(amountField);
        centerPanel.add(categoryLabel);
        centerPanel.add(categoryComboBox);
        centerPanel.add(addButton);
        c.add(centerPanel, BorderLayout.CENTER);

        JPanel westPanel = new JPanel(new GridLayout(5,1,10,10));
        westPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        westPanel.add(editButton);
        westPanel.add(removeButton);
        westPanel.add(showAllButton);
        westPanel.add(logoutButton);
        c.add(westPanel, BorderLayout.WEST);

        JPanel southPanel = new JPanel(new GridLayout(1,1));
        southPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        JScrollPane scrollPane = new JScrollPane(expenseList);
        southPanel.add(scrollPane);
        c.add(southPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        removeButton.addActionListener(this);
        showAllButton.addActionListener(this);
        logoutButton.addActionListener(this);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == addButton) {
            // Get the amount and category from the UI components
            String amountString = amountField.getText();
            double amount = Double.parseDouble(amountString);
            String category = (String) categoryComboBox.getSelectedItem();

            // Add the expense to the expense list
            String expense = String.format("%s: $%.2f\n", category, amount);
            expenseList.append(expense);

            // Clear the amount field
            amountField.setText("");
        }
        else if (e.getSource() == editButton) {
            // TODO: Implement editing of expenses
            JOptionPane.showMessageDialog(this, "Editing of expenses is not yet implemented.");
        }
        else if (e.getSource() == removeButton) {
            // TODO: Implement removal of expenses
            JOptionPane.showMessageDialog(this, "Removal of expenses is not yet implemented.");
        }
    }
}
