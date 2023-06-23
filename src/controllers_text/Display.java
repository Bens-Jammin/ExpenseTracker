
import java.util.Scanner;
import java.util.Set;

import handlers.DataManager;
import structures.User;

public class Display{

    public static void addExpense(Scanner scan, User user){
        System.out.print("Enter the name of the expense: ");
        String expenseName = scan.next();
        System.out.print("Enter the amount of the expense: ");
        double expenseAmount = scan.nextDouble();
        System.out.print("Enter the category of the expense: ");
        String expenseCategory = scan.next();
        user.addExpense(expenseName,expenseAmount,expenseCategory);
        //DataManager.saveUser(user);
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

//          ===== LOGIN / SIGNUP METHODS =====

    public static User login(Scanner scan){
        // LEGACY / TEXT BASED UI METHOD
        User user = null;

        while(true){
            System.out.print("Enter username: ");
            String username = scan.next();
            System.out.print("Enter password: ");
            String password = scan.next();
            
            // check if password matches saved data
            user = DataManager.loadUser(username);
            if( user.attemptSignin(password) ){
                clearSCreen();
                System.out.println("Welcome back " + username + "!");
                break;
            }else{
                clearSCreen();
                System.out.println("Incorrect credential provided!");
            }
        }

        return user;
    }


    public static User login(String username, String password){
        User user = null;

        // check if password matches saved data
        user = handlers.DataManager.loadUser(username);  // grabs a user saved
        if(user != null && user.attemptSignin(password) ){   // checks if the password is correct AND user exists
            System.out.println("Welcome back " + username + "!");
        }
        
        return user;
    }


    public static User createAccount(Scanner scan) {
        // ALSO A LEGACY METHOD
        boolean acceptableCredentials = false;
        String username = null;
        String password = null;
        User user = null;

        while(!acceptableCredentials){
            System.out.print("Enter a username: ");
            username = scan.next();
            System.out.print("Enter a password: ");
            password = scan.next();

            acceptableCredentials = DataManager.isUserNameAvailable(username);

            if(acceptableCredentials){
                user = new User(username, password);
                //DataManager.saveUser(user);
                clearSCreen();
                System.out.print("Account successfully created!");
            }else{
                clearSCreen();
                System.out.println("username is already in use!");
            }
        }

        return user;

    }


    public static User createAccount(String username, String password){
        boolean acceptableCredentials = false;
        User user = null;

        while(!acceptableCredentials){

            acceptableCredentials = DataManager.isUserNameAvailable(username);

            if(acceptableCredentials){
                user = new User(username, password);
                //DataManager.saveUser(user);
                clearSCreen();
                System.out.print("Account successfully created!");
            }else{
                clearSCreen();
                System.out.println("username is already in use!");
            }
        }

        return user;

    }

    public static void displayCurrentExpenseCategories(User user){
        System.out.println("\nHere are your current expense categories, to select one to use again, please enter the number beside the category:");

        Set<String> expenseCategories = user.getExpenseCategories();

        int categoryNumber = 1;
        for(String category : expenseCategories){
            System.out.println(" >> ("+categoryNumber+") : "+category);
        }

    }


    public static void clearSCreen(){System.out.print("\033c");}

    public static void Invalid() {System.out.println("Invalid input, try again.");}

    public static void Logout(Scanner scan, User user){
        System.out.println("Logging out...");
        //DataManager.saveUser(user);
        scan.close();
        System.exit(0);
    }
}
