import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        boolean loggedIn = false;
        
        while (true) {
            if (!loggedIn) {
                System.out.println("Enter '1' to sign up, '2' to log in, or '3' to exit:");
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.println("Enter a username:");
                        String username = scanner.next();
                        System.out.println("Enter a password:");
                        String password = scanner.next();
                        user = new User(username, password);
                        System.out.println("Sign up successful.");
                        loggedIn = true;
                        // add a thing to save class instance info
                        break;
                    case 2:
                        System.out.println("Enter your username:");
                        String loginUsername = scanner.next();
                        System.out.println("Enter your password:");
                        String loginPassword = scanner.next();
                        /* TODO:
                        1. make attemptSignin a static class, checks all saved class data if its available
                        2. if available, user = new User(user, pwd);
                        3. refactor? this looks like I can refactor it
                        */
                        if (user != null && user.attemptSignin(loginUsername, loginPassword)) {
                            System.out.println("Login successful.");
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Try again.");
                        break;
                }
            } else {
                // user is logged in, display options here
                System.out.println("Enter '1' to add an expense, '2' to remove an expense, '3' to add a bill, '4' to remove a bill, '5' to display all expenses, '6' to display all bills, or '7' to log out:");
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        // add expense logic here
                        break;
                    case 2:
                        // remove expense logic here
                        break;
                    case 3:
                        // add bill logic here
                        break;
                    case 4:
                        // remove bill logic here
                        break;
                    case 5:
                        // display all expenses logic here
                        break;
                    case 6:
                        // display all bills logic here
                        break;
                    case 7:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                        break;
                }
            }
        }
    }
}
