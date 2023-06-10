import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.NumberFormatter;

public class AddTransactionPage extends JFrame {

    private JComboBox<String> typeComboBox;
    private JComboBox<String> categoryComboBox;
    private JTextField nameTextField;
    private JFormattedTextField amountField;

    private List<String> categories;

    public AddTransactionPage(User user) {
        // Initialize categories list
        categories = new ArrayList<>();

        // Set up the frame
        setTitle("Transaction Creation Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));

        // Create and add components to the main panel
        JLabel typeLabel = new JLabel("Type:");
        typeComboBox = new JComboBox<>(new String[]{"Income", "Expense"});
        mainPanel.add(typeLabel);
        mainPanel.add(typeComboBox);

        JLabel categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<>();
        mainPanel.add(categoryLabel);
        mainPanel.add(categoryComboBox);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);

        JLabel amountLabel = new JLabel("Amount:");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        amountField = new JFormattedTextField(formatter);
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);

        JButton createButton = new JButton("Create Transaction");
        mainPanel.add(createButton);

        // Add the main panel to the frame
        add(mainPanel);

        // Set up category creation dialog
        final JDialog categoryCreationDialog = new JDialog(this, "Create New Category", true);
        categoryCreationDialog.setPreferredSize(new Dimension(300, 150));
        categoryCreationDialog.setResizable(false);
        categoryCreationDialog.setLayout(new FlowLayout());

        JLabel newCategoryLabel = new JLabel("New Category:");
        JTextField newCategoryTextField = new JTextField(15);
        JButton createCategoryButton = new JButton("Create");
        categoryCreationDialog.add(newCategoryLabel);
        categoryCreationDialog.add(newCategoryTextField);
        categoryCreationDialog.add(createCategoryButton);

        // Button action listeners
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTransaction(user);
            }
        });

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Create New Category".equals(categoryComboBox.getSelectedItem())) {
                    categoryCreationDialog.setVisible(true);
                }
            }
        });

        createCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCategory = newCategoryTextField.getText().trim();
                if (!newCategory.isEmpty() && !categories.contains(newCategory)) {
                    categories.add(newCategory);
                    categoryComboBox.addItem(newCategory);
                    categoryComboBox.setSelectedItem(newCategory);
                    categoryCreationDialog.dispose();
                }
            }
        });

        // Pack and display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createTransaction(User user) {
        String type = typeComboBox.getSelectedItem().toString();
        String category = categoryComboBox.getSelectedItem().toString();
        String name = nameTextField.getText().trim();
        String amountText = amountField.getText().trim();

        // Validate the inputs
        if (name.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name and an amount for the transaction.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount for the transaction.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (amount <= 0) {
            JOptionPane.showMessageDialog(this, "Amount must be greater than zero.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the transaction using the collected information
        if ("Income".equals(type)) {
            user.addIncome(name, amount, category);
        } else {
            user.addExpense(name, amount, category);
        }
        DataManager.saveUser(user);

        // Show a success message
        JOptionPane.showMessageDialog(this, "Transaction created successfully.",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        // Reset the form
        resetForm();
    }

    private void resetForm() {
        typeComboBox.setSelectedIndex(0);
        categoryComboBox.setSelectedIndex(0);
        nameTextField.setText("");
        amountField.setText("");
    }
}
