import structures.*;

import javax.swing.*;

import handlers.DataManager;
import handlers.YTDSummaryManager;

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
    JButton deleteAccountButton;
    JButton YTDSummaryButton;
    JButton createCSVButton;
    JButton AccountPageButton;

    JPanel contentPanel;
    JLabel netProfitLabel;


    public MainGUIPage(User user) {

        String publicBuildStage = "ALPHA";
        String publicVersionNumber = "0.4.6";

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
        deleteAccountButton = new JButton("DELETE ACCOUNT");
        YTDSummaryButton = new JButton("VIEW YTD SUMMARY");
        createCSVButton = new JButton("Export to CSV");
        AccountPageButton = new JButton("Account");
        toggleColourScheme = new JToggleButton("Dark Mode");
        final int BUTTON_WIDTH = 120;
        final int BUTTON_HEIGHT =  30;
        addTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        viewTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        removeTransactionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        deleteAccountButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        YTDSummaryButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        createCSVButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        AccountPageButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        // addTransactionButton.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // FIXME: why does this do nothing ??
        //buttonImage = resizeIcon(buttonImage,viewTransactionButton.getWidth(),viewTransactionButton.getHeight());
        addTransactionButton.setIcon(buttonImage);
        viewTransactionButton.setIcon(buttonImage);
        removeTransactionButton.setIcon(buttonImage);
        deleteAccountButton.setIcon(buttonImage);
        YTDSummaryButton.setIcon(buttonImage);
        createCSVButton.setIcon(buttonImage);
        AccountPageButton.setIcon(buttonImage);
        addTransactionButton.setForeground(buttonTextColour);
        viewTransactionButton.setForeground(buttonTextColour);
        removeTransactionButton.setForeground(buttonTextColour);
        deleteAccountButton.setForeground(buttonTextColour);
        YTDSummaryButton.setForeground(buttonTextColour);
        createCSVButton.setForeground(buttonTextColour);
        AccountPageButton.setForeground(buttonTextColour);
        // chatGPT says i have to do this for the text to appear on the buttons :(
        addTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        addTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteAccountButton.setVerticalTextPosition(SwingConstants.CENTER);
        deleteAccountButton.setHorizontalTextPosition(SwingConstants.CENTER);
        YTDSummaryButton.setVerticalAlignment(SwingConstants.CENTER);
        YTDSummaryButton.setHorizontalTextPosition(SwingConstants.CENTER);
        createCSVButton.setVerticalAlignment(SwingConstants.CENTER);
        createCSVButton.setHorizontalTextPosition(SwingConstants.CENTER);
        AccountPageButton.setVerticalAlignment(SwingConstants.CENTER);
        AccountPageButton.setHorizontalAlignment(SwingConstants.CENTER);

        deleteAccountButton.setVerticalAlignment(SwingConstants.CENTER);

        sidebarPanel.add(addTransactionButton, BorderLayout.CENTER);
        sidebarPanel.add(viewTransactionButton);
        sidebarPanel.add(removeTransactionButton);
        sidebarPanel.add(YTDSummaryButton);
        sidebarPanel.add(createCSVButton);
        sidebarPanel.add(AccountPageButton);
        sidebarPanel.add(toggleColourScheme);

        // should always be at the bottom
        sidebarPanel.add(deleteAccountButton);

        // Create the content panel
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(mainBackroundColour);



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

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteMessage = DataManager.deleteAccount(user.getUserName());
                if(deleteMessage == null){
                    JOptionPane.showMessageDialog(mainPanel, "Account deleted successfully.",
                    "ACCOUNT DELETED", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(mainPanel, "ERROR: ACCOUNT DELETION FAILED /n"+deleteMessage,
                    "ACCOUNT DELETION FAILED", JOptionPane.ERROR_MESSAGE);
                }
                frame.dispose();
            }
        });

        YTDSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YTDSummaryManager ytdTable = new YTDSummaryManager(user);
                ytdTable.printSummaryTable();
            }
        });

        createCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.convertUserToCSV(user);
                JOptionPane.showMessageDialog(mainPanel, "CSV saved at UserData/"+user.getUserName()+"_transaction_data.csv",
                    "Export Complete", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        AccountPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountPage(user);
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
        sidebarPanel.setBackground(sidePanelColour);
        addTransactionButton.setIcon(buttonImage);
        viewTransactionButton.setIcon(buttonImage);
        removeTransactionButton.setIcon(buttonImage);
        deleteAccountButton.setIcon(buttonImage);
        YTDSummaryButton.setIcon(buttonImage);
        createCSVButton.setIcon(buttonImage);
        AccountPageButton.setIcon(buttonImage);
        addTransactionButton.setForeground(buttonTextColour);
        viewTransactionButton.setForeground(buttonTextColour);
        removeTransactionButton.setForeground(buttonTextColour);
        deleteAccountButton.setForeground(buttonTextColour);
        netProfitLabel.setForeground(textColour);
        YTDSummaryButton.setForeground(buttonTextColour);
        createCSVButton.setForeground(buttonTextColour);
        createCSVButton.setForeground(buttonTextColour);

        // chatGPT says i have to do this for the text to appear on the buttons :(
        addTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        addTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        viewTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setVerticalTextPosition(SwingConstants.CENTER);
        removeTransactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
    }

}
