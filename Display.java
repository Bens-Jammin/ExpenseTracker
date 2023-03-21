import java.util.Scanner;

public class Display{

    public static void addExpense(Scanner scan, User user){
        System.out.print("Enter the name of the expense: ");
        String expenseName = scan.next();
        System.out.print("Enter the amount of the expense: ");
        double expenseAmount = scan.nextDouble();
        System.out.print("Enter the category of the expense: ");
        String expenseCategory = scan.next();
        user.addExpense(expenseName,expenseAmount,expenseCategory);
        DataManager.saveUser(user);
        System.out.println("Expense successfully added!");
    }

    public static void removeExpense(Scanner scan, User user){
        System.out.println("This has not been implemented yet.");
    }


    public static void addBill(Scanner scan, User user){
        System.out.println("This has not been implemented yet.");
    }

    public static void removeBill(Scanner scan, User user){
        System.out.println("This has not been implemented yet.");
    }

    public static void displayExpenses(Scanner scan, User user) {
        System.out.println("Here are all your current expenses:"); 
        System.out.println(user.getOneExpense(0).toString());
        user.displayAllExpenses();
    }


    public static void displayStats(Scanner scan, User user) {Statistics.displayStatistics(user);}

    public static void Invalid() {System.out.println("Invalid input, try again.");}

    public static void Logout(Scanner scan, User user){
        System.out.println("Logging out...");
        DataManager.saveUser(user);
        scan.close();
        System.exit(0);
    }
}
