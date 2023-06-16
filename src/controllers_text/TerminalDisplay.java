
/* ===== CLASS DESCRIPTION =====
 * 
 * This class will be what is displayed on the terminal.
 * It will have different methods which are the different "pages" you can visit
 * 
 * last updated: 10.05.2023
 */

 import java.util.Scanner;

import handlers.Display;
import handlers.ExcelManager;
import structures.*;

class TerminalDisplay extends Display{

    private static final String SEPARATOR = "=====";

    public static User HomeScreen(){
        Display.clearSCreen();
        Scanner scan = new Scanner(System.in);

        System.out.println(SEPARATOR + " welcome ! " + SEPARATOR + "\n\n");
        System.out.println("To login, please enter '1'.\nTo signup, please enter'2'.");
        int loginChoice = scan.nextInt();

        if( loginChoice == 1 ){
            return Display.login(scan);
        }else{
            return Display.createAccount(scan);
        }

    }

    // tbh idk what to call this method, its gonna allow showing/editing expenses, etc
    public static void MainUserPage(User user){
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println(SEPARATOR + "  logged in as : " + user.getUserName() +"  " +  SEPARATOR + "\n\n");

            System.out.println("To add an expense, enter '1'");
            System.out.println("To edit an existing expense, enter '2'");
            System.out.println("To remove an expense, enter '3'");
            System.out.println("To view all expenses, enter '4'");
            System.out.println("To see spending statistics, enter '5'");
            System.out.println("To create an Excel File of all your expenses, enter '6'");
            System.out.println("To logout, enter '9'");

            int choice = scan.nextInt();

            /* TODO:
             * 
             * 1. clear screen before breaking in switch
             */

            switch (choice) {
                case 1: 
                    System.out.print("Enter the name of the expense: ");
                    // idfk why I have to add it, chatGPT says I need it and it doesn't work without it
                    scan.nextLine(); // consumes a newline character that's NOT FUCKING THERE
                    String expenseName = scan.nextLine();
                    System.out.print("Enter the amount paid for the expense: ");
                    double expenseAmount = scan.nextDouble();
                    scan.nextLine(); // consume the leftover newline character
                    System.out.print("Enter the category of the expense (medical, transport, groceries, etc) ");
                    Display.displayCurrentExpenseCategories(user);
                    String expenseCategory = scan.nextLine();
                    user.addExpense(expenseName, expenseAmount, expenseCategory);
                    //DataManager.saveUser(user);
                    break;
                case 2: 
                    System.out.println("This hasn't been implemented yet!!!"); 
                    break;
                case 3: 
                    System.out.print("Enter the name of the expense you want to remove: "); 
                    scan.nextLine(); // consumes a newline character that's NOT FUCKING THERE I HATE JAVA
                    String removedExpense = scan.nextLine();
                    user.removeExpense(removedExpense);
                    //DataManager.saveUser(user);
                    break;
                case 4: 
                    user.displayAllExpenses(); 
                    break;
                case 5: 
                    break;
                case 6:
                    ExcelManager.UpdateAllFiles(user);
                    break;
                case 9: 
                    Display.Logout(scan, user);
            }
        }
    }        

    public static void main(String[] args) {
        MainUserPage( HomeScreen() );
    }
}