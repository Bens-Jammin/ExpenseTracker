import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.text.NumberFormatter;

import java.util.ArrayList;
import java.util.List;

import structures.*;
import handlers.DataManager;
import handlers.ColourSchemeManager;

public class AddTransactionPage extends JFrame {

    private JComboBox<String> typeComboBox;
    private JComboBox<String> categoryComboBox;
    private JTextField nameTextField;
    private JFormattedTextField amountField;

    private JPanel mainPanel;

    public AddTransactionPage(User user, JLabel netProfitLabel) {

        // Set up the frame
        setTitle("Transaction Creation Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 250));

        // set theme
        int mode = user.getColourScheme();
        String theme = (mode == 0) ? "darkMode" : "babyGirlMode";
        ImageIcon buttonImage = ColourSchemeManager.getButtonImage(theme);
        Color buttonTextColour = ColourSchemeManager.getColor(theme, "buttonText");
        Color mainBackroundColour = ColourSchemeManager.getColor(theme, "mainBackground");
        Color textColour = ColourSchemeManager.getColor(theme, "textColour");
        Color sidePanelColour = ColourSchemeManager.getColor(theme, "sideBar");


        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 10, 10));
        mainPanel.setBackground(mainBackroundColour);



        // Create and add components to the main panel
        JLabel typeLabel = new JLabel("Type:");
        typeComboBox = new JComboBox<>(new String[]{"Expense", "Income"});
        typeLabel.setForeground(textColour);
        typeComboBox.setBackground(sidePanelColour);
        mainPanel.add(typeLabel);
        mainPanel.add(typeComboBox);

        JLabel newCategoryLabel = new JLabel("New Category:");
        JTextField newCategoryTextField = new JTextField();
        newCategoryLabel.setForeground(textColour);
        newCategoryTextField.setForeground(textColour);
        newCategoryTextField.setBackground(sidePanelColour);
        mainPanel.add(newCategoryLabel);
        mainPanel.add(newCategoryTextField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(textColour);
        categoryComboBox = new JComboBox<String>();
        
        // fills combobox of previously saved categories
        List<String> categories = user.getAllTransactionCategories();
        System.out.println(categories);
        if(categories != null ){
            if(categories != null && categories.size() != 0){
                for(String s : categories){
                    System.out.println("Category: "+s);
                    categoryComboBox.addItem(s);
                }
            }
        }

        mainPanel.add(categoryLabel);
        mainPanel.add(categoryComboBox);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        nameLabel.setForeground(textColour);
        nameTextField.setForeground(textColour);
        nameTextField.setBackground(sidePanelColour);
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);


        JLabel amountLabel = new JLabel("Amount:");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(decimalFormat);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        amountField = new JFormattedTextField(formatter);
        amountLabel.setForeground(textColour);
        amountField.setForeground(textColour);
        amountField.setBackground(sidePanelColour);
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);

        JButton createCategoryButton = new JButton("Submit Category");
        createCategoryButton.setIcon(buttonImage);
        createCategoryButton.setForeground(buttonTextColour);
        createCategoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
        createCategoryButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainPanel.add(createCategoryButton);

        JButton createButton = new JButton("Create Transaction");
        createButton.setIcon(buttonImage);
        createButton.setForeground(buttonTextColour);
        createButton.setHorizontalTextPosition(SwingConstants.CENTER);
        createButton.setVerticalTextPosition(SwingConstants.CENTER);
        mainPanel.add(createButton);

        // Add the main panel to the frame
        add(mainPanel);



        // Button action listeners
        createCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCategory = newCategoryTextField.getText().trim();
                if (!newCategory.isEmpty()) {
                    categoryComboBox.addItem(newCategory);
                    newCategoryTextField.setText("");
                    
                    List<String> updatedCategories = user.getAllTransactionCategories();
                    
                    if (updatedCategories == null) {
                        // If the transaction categories file doesn't exist, create a new list
                        updatedCategories = new ArrayList<>();
                    }

                    if(updatedCategories.size() != 0 && updatedCategories.contains(newCategory)){
                        JOptionPane.showMessageDialog(mainPanel, "Category already exists",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    updatedCategories.add(newCategory);
                    System.out.println(updatedCategories);
                    DataManager.saveUser(user);
                    System.out.println("saved!");
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTransaction(user);

                netProfitLabel.setText(MainPage.updateProfit(user));
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
        String amountText = amountField.getText().trim().replaceAll(",", "");

        System.out.println(type+category+name+amountText);
        // Validate the inputs
        if (name.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name and an amount for the transaction.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            System.out.println(amount);
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
        if ("Income".equals(type) ){
            user.addIncome(name, amount, category);
        } else {
            user.addExpense(name, amount, category);
        }
        DataManager.saveUser(user);

        // Show a success message
        JOptionPane.showMessageDialog(this, "Transaction created successfully.",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }

}
