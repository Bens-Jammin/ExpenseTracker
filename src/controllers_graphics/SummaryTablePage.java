import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

import handlers.YTDSummaryManager;


public class SummaryTablePage extends JFrame {
    private JTable table;

    public SummaryTablePage(YTDSummaryManager summaryManager) {
        setTitle("Summary Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };

        // Add the columns to the table model
        tableModel.addColumn("CATEGORY");
        for (int month = 1; month <= 12; month++) {
            tableModel.addColumn(LocalDate.of(2023, month, 1).getMonth().name().substring(0, 3));
        }
        tableModel.addColumn("Total");

        // Add the rows to the table model
        summaryManager.getSummaryTable().forEach((category, monthMap) -> {
            Object[] rowData = new Object[15]; // Assuming 12 months + total year
            rowData[0] = category;

            for (int month = 1; month <= 12; month++) {
                double amount = monthMap.getOrDefault(month, 0.0);
                rowData[month] = amount;
            }

            double totalYearAmount = monthMap.getOrDefault(13, 0.0);
            rowData[13] = totalYearAmount;

            tableModel.addRow(rowData);
        });

        // Create the table with the table model
        table = new JTable(tableModel);

        // Set table appearance
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        for (int i = 0; i <= 13; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(100);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        Font headerFont = table.getTableHeader().getFont();
        Font boldHeaderFont = new Font(headerFont.getFontName(), Font.BOLD, headerFont.getSize());
        table.getTableHeader().setFont(boldHeaderFont);
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);


        // Set table as uneditable
        table.setEnabled(false);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);
 
    // Pack and display the frame
    setLocationRelativeTo(null);
    setVisible(true);
    }
}