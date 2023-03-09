import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        User user = null;
        while (true) {
            if (!loggedIn) {
                int choice = -1;
                try{
                    System.out.println("Enter '1' to sign up, '2' to log in, or '3' to exit:");
                    choice = scanner.nextInt();
                }catch(Exception InputMismatchException){
                    System.out.println("You must enter a number.");
                }
                
                switch (choice) {
                    case 1:
                        System.out.println("Enter a username: ");
                        String username = scanner.next();
                        System.out.println("Enter a password: ");
                        String password = scanner.next();
                        System.out.println("Sign up successful.");
                        loggedIn = true;
                        user = new User(username, password);
                        UserManager.saveUser(user);
                        loggedIn = true;
                        break;
                    case 2:
                        System.out.println("Enter your username: ");
                        String loginUsername = scanner.next();
                        System.out.println("Enter your password: ");
                        String loginPassword = scanner.next();
                        
                        User[] data = UserManager.loadUsers();
                        
                        if (loginUsername != null && User.attemptSignin(data, loginUsername, loginPassword)) {
                            System.out.println("Login successful.");
                            user = new User(loginUsername, loginPassword);
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Try again.");
                        break;
                }
            } else {
                // user is logged in, display options here
                System.out.println("Enter:\n'1' to add an expense\n'2' to remove an expense\n'3' to add a bill\n'4' to remove a bill\n'5' to display all expenses\n'6' to display all bills\n'7' to see statistics\n'8' to log out:");
                int choice = scanner.nextInt();

                Scanner scan = new Scanner(System.in);
                switch (choice){
                    case 1: // add expense
                        System.out.print("Enter the name of the expense: ");
                        String expenseName = scan.next();
                        System.out.print("Enter the amount of the expense: ");
                        double expenseAmount = scan.nextDouble();
                        System.out.print("Enter the category of the expense: ");
                        String expenseCategory = scan.next();
                        user.addExpense(expenseName,expenseAmount,expenseCategory);
                        System.out.println("Expense successfully added!");
                        UserManager.saveUser(user);
                        break;
                    // need to implement case 2, 3, 4
                    case 2: // remove expense
                        System.out.println("This has not been implemented yet.");
                        UserManager.saveUser(user);
                        break;
                    case 3: // add bill
                        System.out.println("This has not been implemented yet.");
                        UserManager.saveUser(user);
                        break;
                    case 4: // remove bill
                        System.out.println("This has not been implemented yet.");
                        UserManager.saveUser(user);
                        break;
                    // cases above need to be implemented
                    case 5:
                        System.out.println("Here are all your current expenses:"); // SAVING USER DATA DOES NOT WORK
                        UserManager.saveUser(user);
                        user.displayAllExpenses();
                        break;
                    case 6:
                        System.out.println("Here are all your current bills:");
                        UserManager.saveUser(user);
                        user.displayAllBills();
                        break;
                    case 7:
                        Statistics.displayStatistics(user);    
                    case 8:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        UserManager.saveUser(user);
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
