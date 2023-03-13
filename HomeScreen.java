import java.util.Scanner;

public class HomeScreen {

    public static void displayScreen(String c0, String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8, String c9 ){

        System.out.println("****************************************");
        System.out.println();       // display username?
        System.out.println("(0) "+c0);
        System.out.println("(1) "+c1);
        System.out.println("(2) "+c2);
        System.out.println("(3) "+c3);
        System.out.println("(4) "+c4);
        System.out.println("(5) "+c5);
        System.out.println("(6) "+c6);
        System.out.println("(7) "+c7);
        System.out.println("(8) "+c8);
        System.out.println("(9) "+c9);
        System.out.println();
        System.out.println("****************************************");

    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        User user = null;
        while (true) {
            if (!loggedIn) {
                int choice = -1;
                try{
                    displayScreen("sign up", "login", "","","","","","","", "exit");
                    choice = scanner.nextInt();
                }catch(Exception InputMismatchException){
                    System.out.println("You must enter a number.");
                }
                
                switch (choice) {
                    case 0:
                        System.out.println("Enter a username: ");
                        String username = scanner.next();
                        System.out.println("Enter a password: ");
                        String password = scanner.next();
                        System.out.println("Sign up successful.");
                        loggedIn = true;
                        user = new User(username, password);
                        loggedIn = true;
                        break;
                    case 1:
                        System.out.println("Enter your username: ");
                        String loginUsername = scanner.next();
                        System.out.println("Enter your password: ");
                        String loginPassword = scanner.next();
                        
                        user = UserManager.loadUser(loginUsername);
                        
                        
                        if (loginUsername != null /* and if you can login : attemptLogin() */) {
                            System.out.println("Login successful.");
                            user = new User(loginUsername, loginPassword);
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Try again.");
                        break;
                }
            } else {
                // user is logged in, display options here
                displayScreen("Add an expense", "Remove an Expense","Add a bill","Remove a bill","Display expenses","Display bills","Display statistics","","","Logout");
                int choice = scanner.nextInt();

                Scanner scan = new Scanner(System.in);
                switch (choice){
                    case 0: // add expense
                        System.out.print("Enter the name of the expense: ");
                        String expenseName = scan.next();
                        System.out.print("Enter the amount of the expense: ");
                        double expenseAmount = scan.nextDouble();
                        System.out.print("Enter the category of the expense: ");
                        String expenseCategory = scan.next();
                        user.addExpense(expenseName,expenseAmount,expenseCategory);
                        System.out.println("Expense successfully added!");
                        break;
                    // need to implement case 2, 3, 4
                    case 1: // remove expense
                        System.out.println("This has not been implemented yet.");
                        break;
                    case 2: // add bill
                        System.out.println("This has not been implemented yet.");
                        break;
                    case 3: // remove bill
                        System.out.println("This has not been implemented yet.");
                        break;
                    // cases above need to be implemented
                    case 4:
                        System.out.println("Here are all your current expenses:"); // SAVING USER DATA DOES NOT WORK
                        user.displayAllExpenses();
                        break;
                    case 5:
                        System.out.println("Here are all your current bills:");
                        user.displayAllBills();
                        break;
                    case 6:
                        Statistics.displayStatistics(user);    
                    case 9:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        scan.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Try again.");
                        break;
                    }
                }
        }
    }
}
