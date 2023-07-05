import structures.*;

import javax.swing.*;

import handlers.ColourSchemeManager;
import handlers.YTDSummaryManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

}
