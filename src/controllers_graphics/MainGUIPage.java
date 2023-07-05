import structures.*;

import javax.swing.*;

import handlers.ColourSchemeManager;
import handlers.YTDSummaryManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;


public class MainGUIPage {


    // buttons, pages, frames, etc
    JFrame frame;
    JPanel mainPanel;
    JPanel sidebarPanel;

    JButton addTransactionButton;
    JButton viewTransactionButton;
    JButton removeTransactionButton;
    JButton YTDSummaryButton;
    JButton AccountPageButton;

    JPanel contentPanel;
    JLabel netProfitLabel;


    public MainGUIPage(User user) {

        String publicBuildStage = "ALPHA";
        String publicVersionNumber = "0.4.7.3";

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

        // set theme
        int mode = user.getColourScheme();
        String colourScheme = (mode == 0) ? "darkMode" : "babyGirlMode";
        ImageIcon buttonImage = ColourSchemeManager.getButtonImage(colourScheme);
        Color buttonTextColour = ColourSchemeManager.getColor(colourScheme, "buttonText");
        Color mainBackroundColour = ColourSchemeManager.getColor(colourScheme, "mainBackground");
        Color sidePanelColour = ColourSchemeManager.getColor(colourScheme, "sideBar");
        Color textColour = ColourSchemeManager.getColor(colourScheme, "textColour");


        // Create sidebar panel
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(sidePanelColour);

        // Add sidebar buttons or components
        addTransactionButton= new JButton("Add Transaction");
        viewTransactionButton = new JButton("View All Transactions");
        removeTransactionButton = new JButton("Remove Transactions");
        YTDSummaryButton = new JButton("VIEW YTD SUMMARY");
        AccountPageButton = new JButton("Account");
        final int BUTTON_WIDTH = 120;
        final int BUTTON_HEIGHT =  30;
        addTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        viewTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        removeTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        YTDSummaryButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        AccountPageButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        // addTransactionButton.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // FIXME: why does this do nothing ??
        //buttonImage = resizeIcon(buttonImage,viewTransactionButton.getWidth(),viewTransactionButton.getHeight());


        addTransactionButton.setIcon(buttonImage);
        viewTransactionButton.setIcon(buttonImage);
        removeTransactionButton.setIcon(buttonImage);
        YTDSummaryButton.setIcon(buttonImage);
        AccountPageButton.setIcon(buttonImage);
        addTransactionButton.setForeground(buttonTextColour);
        viewTransactionButton.setForeground(buttonTextColour);
        removeTransactionButton.setForeground(buttonTextColour);
        YTDSummaryButton.setForeground(buttonTextColour);
        AccountPageButton.setForeground(buttonTextColour);
        // chatGPT says i have to do this for the text to appear on the buttons :(
        addTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        addTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        YTDSummaryButton.setVerticalTextPosition(SwingConstants.CENTER);
        YTDSummaryButton.setHorizontalTextPosition(SwingConstants.CENTER);
        
        AccountPageButton.setVerticalTextPosition(SwingConstants.CENTER);        
        AccountPageButton.setHorizontalTextPosition(SwingConstants.CENTER);


        sidebarPanel.add(AccountPageButton);
        sidebarPanel.add(addTransactionButton);
        sidebarPanel.add(viewTransactionButton);
        sidebarPanel.add(removeTransactionButton);
        sidebarPanel.add(YTDSummaryButton);


        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(mainBackroundColour);

        JPanel statsPanel = new JPanel(new GridLayout(2,0));
        contentPanel.add(statsPanel, BorderLayout.CENTER);

        String[] userStats = calculateUsersStatistics(user);
        JLabel expenseStatLabel1 = new JLabel(userStats[0]); 
        JLabel expenseStatLabel2 = new JLabel(userStats[1]); 
        JLabel expenseStatLabel3 = new JLabel(userStats[2]); 
        JLabel expenseStatLabel4 = new JLabel(userStats[3]); 
        JLabel incomeStatLabel1 = new JLabel(userStats[4]); 
        JLabel incomeStatLabel2 = new JLabel(userStats[5]); 
        JLabel incomeStatLabel3 = new JLabel(userStats[6]); 
        JLabel incomeStatLabel4 = new JLabel(userStats[7]); 

        expenseStatLabel1.setForeground(textColour);
        expenseStatLabel2.setForeground(textColour);
        expenseStatLabel3.setForeground(textColour);
        expenseStatLabel4.setForeground(textColour);
        incomeStatLabel1.setForeground(textColour);
        incomeStatLabel2.setForeground(textColour);
        incomeStatLabel3.setForeground(textColour);
        incomeStatLabel4.setForeground(textColour);

       // Create panels for expense and income stats
        JPanel expenseStatsPanel = new JPanel(new GridLayout(0, 1));  // 0 rows to automatically adjust based on labels
        JPanel incomeStatsPanel = new JPanel(new GridLayout(0, 1));   // 0 rows to automatically adjust based on labels

        expenseStatsPanel.setBackground(mainBackroundColour);
        incomeStatsPanel.setBackground(mainBackroundColour);


        // Add expense stat labels to the expenseStatsPanel
        expenseStatsPanel.add(expenseStatLabel1);
        expenseStatsPanel.add(expenseStatLabel2);
        expenseStatsPanel.add(expenseStatLabel3);
        expenseStatsPanel.add(expenseStatLabel4);

        // Add income stat labels to the incomeStatsPanel
        incomeStatsPanel.add(incomeStatLabel1);
        incomeStatsPanel.add(incomeStatLabel2);
        incomeStatsPanel.add(incomeStatLabel3);
        incomeStatsPanel.add(incomeStatLabel4);

        // Add the expenseStatsPanel to the left side and incomeStatsPanel to the right side of contentPanel
        statsPanel.add(expenseStatsPanel);
        statsPanel.add(incomeStatsPanel);



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
                new AddTransactionPage(user, netProfitLabel);
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
                new RemoveTransactionsPage(user, netProfitLabel);
                frame.repaint();
            }
        });

        YTDSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YTDSummaryManager ytd = new YTDSummaryManager(user);
                new SummaryTablePage(ytd);
            }
        });

        AccountPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountPage(user);
            }
        });


        // loading the app
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
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
     * <pre>
     * @param user  the user whos stats are being calculated <br>
     * @return a list of stats in a string where the indexes are the following stats:
     * 0. Most expensive single expense 
     * 1. Most expensive expense category
     * 2. Total expenses
     * 3. Total expense costs
     * 4. Most valuable income 
     * 5. Most valuable income category
     * 6. Total incomes recorded
     * 7. Total income generated 
     * </pre>
     */
    public static String[] calculateUsersStatistics(User user) {
        String[] stats = new String[8];

        // Calculate most expensive single expense
        double maxExpenseAmount = 0;
        String maxExpenseName = "";
        for (Expenses expense : user.getExpenses()) {
            if (expense.getAmount() > maxExpenseAmount) {
                maxExpenseAmount = expense.getAmount();
                maxExpenseName = expense.getName();
            }
        }
        stats[0] = maxExpenseName;

        // Calculate most expensive expense category
        double maxExpenseCategoryAmount = 0;
        String maxExpenseCategory = "";
        Set<String> expenseCategories = user.getExpenseCategories();
        for (String category : expenseCategories) {
            double categoryTotal = 0;
            for (Expenses expense : user.getExpenses()) {
                if (expense.getCategory().equals(category)) {
                    categoryTotal += expense.getAmount();
                }
            }
            if (categoryTotal > maxExpenseCategoryAmount) {
                maxExpenseCategoryAmount = categoryTotal;
                maxExpenseCategory = category;
            }
        }
        stats[1] = maxExpenseCategory;

        // Calculate total expenses
        int totalExpenses = user.getExpenses().size();
        stats[2] = String.valueOf(totalExpenses);

        // Calculate total expense costs
        double totalExpenseCosts = user.getTotalExpenses();
        stats[3] = String.valueOf(totalExpenseCosts);

        // Calculate most 'expensive' income
        double maxIncomeAmount = 0;
        String maxIncomeName = "";
        for (Income income : user.getAllIncome()) {
            if (income.getAmount() > maxIncomeAmount) {
                maxIncomeAmount = income.getAmount();
                maxIncomeName = income.getName();
            }
        }
        stats[4] = maxIncomeName;

        // Calculate most 'expensive' income category
        double maxIncomeCategoryAmount = 0;
        String maxIncomeCategory = "";
        Set<String> incomeCategories = user.getIncomeCategories();
        for (String category : incomeCategories) {
            double categoryTotal = 0;
            for (Income income : user.getAllIncome()) {
                if (income.getCategory().equals(category)) {
                    categoryTotal += income.getAmount();
                }
            }
            if (categoryTotal > maxIncomeCategoryAmount) {
                maxIncomeCategoryAmount = categoryTotal;
                maxIncomeCategory = category;
            }
        }
        stats[5] = maxIncomeCategory;

        // Calculate total incomes recorded
        int totalIncomes = user.getAllIncome().size();
        stats[6] = String.valueOf(totalIncomes);

        // Calculate total income generated
        double totalIncomeGenerated = user.getTotalIncome();
        stats[7] = String.valueOf(totalIncomeGenerated);

        return stats;
    }


}
