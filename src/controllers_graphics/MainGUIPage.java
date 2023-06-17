import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import structures.*;

import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUIPage {

    // colour scheme variables
    private boolean isDarkMode = true;
    JToggleButton toggleColourScheme;

    // default colours 
    Color mainBackroundColour = Color.decode("#232323");
    Color sidePanelColour = Color.decode("#3f3f3f");
    Color buttonTextColour = Color.decode("#141414");
    ImageIcon buttonImage = new ImageIcon("button_grey.png");
    Color textColour = Color.decode("#FFFFFF");

    // buttons, pages, frames, etc
    JFrame frame;
    JPanel mainPanel;
    JPanel sidebarPanel;
    JButton addTransactionButton;
    JButton viewTransactionButton;
    JButton removeTransactionButton;
    JPanel contentPanel;
    JLabel netProfitLabel;
    JTable table;


    public MainGUIPage(User user) {

        String publicBuildStage = "ALPHA";
        String publicVersionNumber = "0.1.0";

        String title = user.getUserName() + " Transaction Account     [ "+ publicBuildStage +" BUILD  " + publicVersionNumber + " ] ";
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create sidebar panel
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(sidePanelColour);

        // Add sidebar buttons or components
        addTransactionButton= new JButton("Add Transaction");
        viewTransactionButton = new JButton("View All Transactions");
        removeTransactionButton = new JButton("Remove Transactions");
        final int BUTTON_WIDTH = 120;
        final int BUTTON_HEIGHT =  30;
        addTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        viewTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        removeTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        // addTransactionButton.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // FIXME: why does this do nothing ??
        //buttonImage = resizeIcon(buttonImage,viewTransactionButton.getWidth(),viewTransactionButton.getHeight());
        addTransactionButton.setIcon(buttonImage);
        viewTransactionButton.setIcon(buttonImage);
        removeTransactionButton.setIcon(buttonImage);
        addTransactionButton.setBackground(buttonTextColour);
        viewTransactionButton.setBackground(buttonTextColour);
        removeTransactionButton.setBackground(buttonTextColour);
        // chatGPT says i have to do this for the text to appear on the buttons :(
        addTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        addTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);

        sidebarPanel.add(addTransactionButton, BorderLayout.CENTER);
        sidebarPanel.add(viewTransactionButton);
        sidebarPanel.add(removeTransactionButton);

        // Toggle between pink/dark mode
        toggleColourScheme = new JToggleButton();
        sidebarPanel.add(toggleColourScheme);

        // Create the content panel
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(mainBackroundColour);

        table = createLatestTransactionsTable(user);
        contentPanel.add(table, BorderLayout.CENTER);


        Font textFont = new Font("Roboto",0 ,24);
        netProfitLabel = new JLabel(updateProfit(user));
        netProfitLabel.setFont(textFont);
        netProfitLabel.setForeground(textColour);
        netProfitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        netProfitLabel.setBorder(BorderFactory.createEmptyBorder(48, 0, 0, 0));
        contentPanel.add(netProfitLabel, BorderLayout.NORTH);

        // Set the preferred sizes
        sidebarPanel.setPreferredSize(new Dimension(frame.getWidth() / 5, frame.getHeight()));
        contentPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        // Add the panels to the main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // Button logic
        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTransactionPage(user, netProfitLabel, table);
                frame.repaint();
            }
        });

        viewTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAllTransactionsPage(user);
                frame.repaint();
            }
        });

        removeTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveTransactionsPage(user, netProfitLabel, table);
                frame.repaint();
            }
        });

        toggleColourScheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                toggleMode();
                frame.repaint();
            }
        });


        // loading the app
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    /**
     * Creates a JTable to display the 3 most recent transactions for the user.
     *
     * @param user The User object associated with the current user.
     * @return The JTable with the latest transactions data.
     */
    public static JTable createLatestTransactionsTable(User user){

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
        table.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));  //FIXME: 
        table.setBackground(Color.decode("#232323"));
        table.setForeground(Color.WHITE);
        table.setEnabled(false);
        table.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0)); // Adjust the padding as needed        
        // Create and return a JTable with the model
        return table;
    }


    public static String updateProfit(User user){

        String bigLabel;
        if(user.getNetValue() < 0){
            bigLabel = "Net Profit : -$"+Math.abs(user.getNetValue());
        }else{
            bigLabel = "Net Profit : $"+user.getNetValue();
        }

        return bigLabel;
    }
    
    /**
     * Toggles the theme of the window.
     */
    private void toggleMode() {
        isDarkMode = !isDarkMode;
        
        if (isDarkMode) {
            // Apply dark mode styles
            toggleColourScheme.setText("Dark Mode");
            toggleColourScheme.setSelected(false);

            // Change colours
            mainBackroundColour = Color.decode("#232323");
            sidePanelColour = Color.decode("#3f3f3f");
            buttonTextColour = Color.decode("#141414");
            buttonImage = new ImageIcon("button_grey.png");
            textColour = Color.decode("#FFFFFF");

        } else {
            // Apply light mode styles
            toggleColourScheme.setText("Babygirl Mode");
            toggleColourScheme.setSelected(true);

            // Change colours
            mainBackroundColour = Color.decode("#E11299");
            sidePanelColour = Color.decode("#F5C6EC");
            buttonTextColour = Color.decode("#9A208C");
            buttonImage = new ImageIcon("button_pink.png");
            textColour = Color.decode("#FFEAEA");

        }
        // update everything
        contentPanel.setBackground(mainBackroundColour);
        table.setBackground(mainBackroundColour);
        sidebarPanel.setBackground(sidePanelColour);
        addTransactionButton.setIcon(buttonImage);
        viewTransactionButton.setIcon(buttonImage);
        removeTransactionButton.setIcon(buttonImage);
        addTransactionButton.setForeground(buttonTextColour);
        viewTransactionButton.setForeground(buttonTextColour);
        removeTransactionButton.setForeground(buttonTextColour);
        netProfitLabel.setForeground(textColour);

        // chatGPT says i have to do this for the text to appear on the buttons :(
        addTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        addTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
    }
}
