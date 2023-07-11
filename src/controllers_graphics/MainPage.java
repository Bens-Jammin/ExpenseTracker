import structures.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import handlers.ColourSchemeManager;
import handlers.YTDSummaryManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Set;


public class MainPage {


    // buttons, pages, frames, etc
    static JFrame frame;
    JPanel mainPanel;
    JPanel sidebarPanel;

    JButton addTransactionButton;
    JButton viewTransactionButton;
    JButton removeTransactionButton;
    JButton YTDSummaryButton;
    JButton AccountPageButton;

    JPanel contentPanel;
    static JLabel netProfitLabel;


    public MainPage(User user) {

        String publicBuildStage = "ALPHA";
        String publicVersionNumber = "0.6.3";

        String title = user.getUserName() + " Transaction Account          [ "+ publicBuildStage +" BUILD  " + publicVersionNumber + " ] ";
        final JFrame frame = new JFrame(title);
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
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(mainBackroundColour);

        JPanel statsPanel = new JPanel(new GridLayout(1,2));


        String[] userStats = calculateUsersStatistics(user);
        JLabel[] allStats = new JLabel[]{
            new JLabel("Most expensive single expense: " + userStats[0]),
            new JLabel("Most expensive expense category: " + userStats[1]),
            new JLabel("Total number of expenses: " + userStats[2]),
            new JLabel("Total expense costs: " + userStats[3]),
            new JLabel("Most valuable single income: " + userStats[4]),
            new JLabel("Most valuable income category: " + userStats[5]),
            new JLabel("Total incomes recorded: " + userStats[6]),
            new JLabel("Total income generated: " + userStats[7]),
        };
        
        Font statFont = new Font("Tahoma", Font.PLAIN, 13);
        
        JPanel expenseStatsPanel = new JPanel(new GridLayout(0,1));
        expenseStatsPanel.setBackground(mainBackroundColour);
        
        JPanel incomeStatsPanel = new JPanel(new GridLayout(0,1));
        incomeStatsPanel.setBackground(mainBackroundColour);
        
        EmptyBorder textBorder = new EmptyBorder(40, 0, 50, 0);

        for (int i = 0; i < 8; i++) {
            allStats[i].setForeground(textColour);
            allStats[i].setFont(statFont);
            allStats[i].setBorder(textBorder);
        
            if (i < 4) {
                allStats[i].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

                expenseStatsPanel.add(allStats[i]);
            } else {
                allStats[i].setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                incomeStatsPanel.add(allStats[i]);
            }
        }

        
        statsPanel.add(expenseStatsPanel);
        statsPanel.add(incomeStatsPanel);
        
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0;
        gbcContent.gridy = 0;
        gbcContent.anchor = GridBagConstraints.NORTHWEST;
        gbcContent.weightx = 1.0;
        gbcContent.weighty = 0.0;
        
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(mainBackroundColour);
        
        JPanel profitPanel = new JPanel(new GridBagLayout());
        profitPanel.setBackground(mainBackroundColour);
        
        Font textFont = new Font("Roboto", Font.BOLD, 24);
        
        double vSpace = 0.5;

        JLabel profitDescriptionLabel = new JLabel("NET PROFIT : ");
        profitDescriptionLabel.setForeground(textColour);
        profitDescriptionLabel.setFont(textFont);
        profitDescriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        profitDescriptionLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        gbcContent.gridx = 0;
        gbcContent.weightx = vSpace;
        gbcContent.fill = GridBagConstraints.HORIZONTAL;
        profitPanel.add(profitDescriptionLabel, gbcContent);
        
        netProfitLabel = updateProfit(user);
        netProfitLabel.setFont(textFont);
        netProfitLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
        netProfitLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gbcContent.gridx = 1;
        gbcContent.weightx = vSpace;
        profitPanel.add(netProfitLabel, gbcContent);
        
        gbcContent.gridy = 0;
        contentPanel.add(profitPanel, gbcContent);
        
        gbcContent.gridy = 1;
        gbcContent.weighty = 1.0;
        contentPanel.add(statsPanel, gbcContent);
        
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
        System.out.println("Profit Panel Size: " + profitPanel.getSize());
        System.out.println("Content Panel Size: " + contentPanel.getSize());
    }

    public static JLabel updateProfit(User user) {
        JLabel bigLabel = new JLabel();
        if (user.getNetValue() < 0) {
            bigLabel.setText("-$" + Math.abs(user.getNetValue()));
            bigLabel.setForeground(Color.RED); // Set the text color to red
        } else {
            bigLabel.setText("$" + user.getNetValue());
            bigLabel.setForeground(Color.GREEN); // Set the text color to green
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


    public static JFrame getFrame(){return frame;}


}
