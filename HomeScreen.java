import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeScreen {

    public static void displayScreen(String[] options){
        System.out.println("****************************************");
        System.out.println("");       // display username?
        System.out.println("(0) "+options[0]);
        System.out.println("(1) "+options[1]);
        System.out.println("(2) "+options[2]);
        System.out.println("(3) "+options[3]);
        System.out.println("(4) "+options[4]);
        System.out.println("(5) "+options[5]);
        System.out.println("(6) "+options[6]);
        System.out.println("(7) "+options[7]);
        System.out.println("(8) "+options[8]);
        System.out.println("(9) "+options[9]);
        System.out.println();
        System.out.println("****************************************");

    }

    public static void Menu(Scanner scanner, User user){
        while(true){
            String[] options = {"Add an expense", "Remove an Expense","Add a bill","Remove a bill","Display expenses","Display bills","Display statistics","","","Logout"};
            displayScreen(options);
            int choice = scanner.nextInt();

            Scanner scan = new Scanner(System.in);
            switch (choice){
                case 0:     Display.addExpense(scan, user);         break;
                case 1:     Display.removeExpense(scan, user);      break;
                case 2:     Display.addBill(scan, user);            break;
                case 3:     Display.removeBill(scan, user);         break;
                case 4:     Display.displayExpenses(scan, user);    break;
                case 5:     Display.displayBills(scan, user);       break;
                case 6:     Display.displayStats(scan, user);       break;    
                case 9:     Display.Logout(scan, user);             break;
                default:    Display.Invalid();                      break;
        }
    }
}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        while (true) {
            int choice = -1;
            try{
                String[] options = {"sign up", "login", "","","","","","","", "exit"};
                displayScreen(options);
                choice = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("You must enter a number.");
            }
            
            switch (choice) {
                case 0:     // signup
                    System.out.println("Enter a username: ");
                    String username = scanner.next();
                    System.out.println("Enter a password: ");
                    String password = scanner.next();
                    user = new User(username, password);
                    boolean successfullySaved = DataManager.saveUser(user);
                    if(successfullySaved){
                        System.out.println("Sign up successful.");
                        Menu(scanner, user);
                    }else{System.out.println("ERROR: User did not save properly.");}
                    break;
                case 1:     // login
                    System.out.println("Enter your username: ");
                    String loginUsername = scanner.next();
                    System.out.println("Enter your password: ");
                    String loginPassword = scanner.next();
                    
                    user = DataManager.loadUserFromUsername(loginUsername);
                    boolean loginSuccessful = user.attemptSignin(loginUsername, loginPassword);
                    
                    
                    if (!loginSuccessful ) {
                        System.out.println("Invalid username or password.");
                        break;
                    }
                    System.out.println("Login successful.");
                    Menu(scanner, user);
                case 6:
                    DataManager.ViewAllSavedUsers();
                case 9:     //logout
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        }
    }
}
