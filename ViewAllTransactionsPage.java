import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewAllTransactionsPage extends JFrame {
    private JTable transactionTable;
    private DefaultTableModel tableModel;

    public ViewAllTransactionsPage(User user) {
        setTitle("All Transactions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        String[] columnNames = {"Type", "Category", "Name", "Amount"};

        tableModel = new DefaultTableModel(columnNames, 0);
        transactionTable = new JTable(tableModel);
        transactionTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        transactionTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        String type;
        String category;
        String name;
        double amount;

        for(int i=0; i< user.getExpenses().size(); i++){
            type = "Expense";
            category = user.getOneExpense(i).getCategory();
            name = user.getOneExpense(i).getName();
            amount = user.getOneExpense(i).getAmount();
            
            addTransaction(type, category, name, amount);
        }

        for(int i=0; i< user.getAllIncome().size(); i++){
            type = "Expense";
            category = user.getOneIncome(i).getCategory();
            name = user.getOneIncome(i).getName();
            amount = user.getOneIncome(i).getAmount();
            
            addTransaction(type, category, name, amount);
        }

        setVisible(true);
    }

    public void addTransaction(String type, String category, String name, double amount) {
        Object[] rowData = {type, category, name, amount};
        tableModel.addRow(rowData);
    }
}
