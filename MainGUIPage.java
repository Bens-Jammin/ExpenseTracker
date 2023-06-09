import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUIPage {

    public MainGUIPage(User user) {

        String title = user.getUserName() + " Transaction Account";
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.decode("#3f3f3f"));

        // Add sidebar buttons or components
        JButton button1 = new JButton("View Transactions");
        JButton button2 = new JButton("Create CSV");
        JButton button3 = new JButton("Logout");
        final int BUTTON_WIDTH = 120;
        final int BUTTON_HEIGHT =  30;
        button1.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button2.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button3.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // FIXME: why does this do nothing ??
        ImageIcon buttonImage = new ImageIcon("buttn.png");
        button1.setIcon(buttonImage);
        button2.setIcon(buttonImage);
        button3.setIcon(buttonImage);
        sidebarPanel.add(button1, BorderLayout.CENTER);
        sidebarPanel.add(button2);
        sidebarPanel.add(button3);

        // Create the content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.decode("#232323"));
        
        String bigLabel;
        if(user.getNetValue() < 0){
            bigLabel = "Net Profit : -$"+Math.abs(user.getNetValue());
        }else{
            bigLabel = "Net Profit : $"+user.getNetValue();
        }

        JTable table = createLatestTransactionsTable(user);
        contentPanel.add(table, BorderLayout.CENTER);

        JLabel contentLabel = new JLabel(bigLabel);
        Font textFont = new Font("Roboto",0 ,24);
        contentLabel.setFont(textFont);
        contentLabel.setForeground(Color.WHITE);
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentLabel.setBorder(BorderFactory.createEmptyBorder(48, 0, 0, 0));
        contentPanel.add(contentLabel, BorderLayout.NORTH);

        // Set the preferred sizes
        sidebarPanel.setPreferredSize(new Dimension(frame.getWidth() / 5, frame.getHeight()));
        contentPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        // Add the panels to the main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // Button logic
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for button1 (View All Transactions)
                contentLabel.setText("View All Transactions button clicked");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for button2 (Create CSV)
                contentLabel.setText("Create CSV button clicked");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for button3 (Logout)
                contentLabel.setText("Logout button clicked");
            }
        });

        // loading the app
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }


    private JTable createLatestTransactionsTable(User user){

    List<Expenses> expenses = user.getExpenses();
    int size = expenses.size();

    Transaction t1 = null;
    Transaction t2 = null;
    Transaction t3 = null;

    Object[][] data;
    if (size >= 3) {
        t1 = expenses.get(2);
        t2 = expenses.get(1);
        t3 = expenses.get(0);

        data = new Object[][]{
            {t1.getCategory(), t1.getName(), t1.getAmount()},
            {t2.getCategory(), t2.getName(), t2.getAmount()},
            {t3.getCategory(), t3.getName(), t3.getAmount()}
        };

        // Use t1, t2, t3 as needed
    } else if (size == 2) {
        t1 = expenses.get(1);
        t2 = expenses.get(0);

        data = new Object[][]{
            {t1.getCategory(), t1.getName(), t1.getAmount()},
            {t2.getCategory(), t2.getName(), t2.getAmount()},
            {null, null, null}
        };
    } else if (size == 1) {
        t1 = expenses.get(0);
        
        data = new Object[][]{
            {t1.getCategory(), t1.getName(), t1.getAmount()},
            {null, null, null},
            {null, null, null}
        };
    } else{
        data = new Object[][]{{null, null, null},{null, null, null},{null, null, null}};
    }
    
        // Create column names
        String[] columnNames = {"Name", "Age", "Country"};

        // Create a DefaultTableModel to hold the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);


        // make it pretty

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        JTable table = new JTable(model);
        // Apply the default cell renderer to all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));  //FIXME: 
        table.setBackground(Color.decode("#232323"));
        table.setForeground(Color.WHITE);

        // Create and return a JTable with the model
        return table;
    }
}
