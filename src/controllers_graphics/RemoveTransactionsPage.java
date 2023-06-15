import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RemoveTransactionsPage extends JFrame {
    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;

    public RemoveTransactionsPage(User user, JLabel netProfitLabel) {
        setTitle("Remove Transactions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        String[] columnNames = {"", "Type", "Category", "Name", "Amount"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Set the checkbox column to Boolean class
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Allow editing only for checkbox column
            }
        };

        transactionTable = new JTable(tableModel);
        transactionTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        deleteButton = new JButton("Confirm Deletion");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCheckedTransactions(user);
                netProfitLabel.setText(MainGUIPage.updateProfit(user));
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        String type;
        String category;
        String name;
        double amount;

        for (int i = 0; i < user.getExpenses().size(); i++) {
            type = "Expense";
            category = user.getOneExpense(i).getCategory();
            name = user.getOneExpense(i).getName();
            amount = user.getOneExpense(i).getAmount();

            addTransaction(type, category, name, amount);
        }

        for (int i = 0; i < user.getAllIncome().size(); i++) {
            type = "Income";
            category = user.getOneIncome(i).getCategory();
            name = user.getOneIncome(i).getName();
            amount = user.getOneIncome(i).getAmount();

            addTransaction(type, category, name, amount);
        }

        setVisible(true);
    }

    public void addTransaction(String type, String category, String name, double amount) {
        Object[] rowData = {false, type, category, name, amount};
        tableModel.addRow(rowData);
    }

    public void deleteCheckedTransactions(User user) {
        List<Integer> rowsToDelete = new ArrayList<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean checked = (Boolean) tableModel.getValueAt(i, 0);
            if (checked != null && checked) {
                rowsToDelete.add(i);
            }
        }

        for (int i = rowsToDelete.size() - 1; i >= 0; i--) {
            int row = rowsToDelete.get(i);
            String name = (String) tableModel.getValueAt(row, 3); // Assuming name is in the fourth column (index 3)

            String type = (String) tableModel.getValueAt(row, 1); // Assuming type is in the second column (index 1)
            if (type.equals("Expense")) {
                user.removeExpense(name);
            } else if (type.equals("Income")) {
                user.removeIncome(name);
            }

            tableModel.removeRow(row);
        }

        DataManager.saveUser(user);
    }
}
