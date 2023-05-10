/* ===== CLASS DESCRIPTION =====
 * 
 * This class will be what is displayed on the terminal.
 * It will have different methods which are the different "pages" you can visit
 * 
 * last updated: 10.05.2023
 */

 import java.util.Scanner;

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

        System.out.println(SEPARATOR + "  logged in as : " + user.getUserName() +"  " +  SEPARATOR + "\n\n");

        System.out.println("To add an expense, enter '1'");
        System.out.println("To edit an existing expense, enter '2'");
        System.out.println("To remove an expense, enter '3'");
        System.out.println("To view all expenses, enter '4'");
        System.out.println("To see spending statistics, enter '5'");

        int choice = scan.nextInt();

        switch (choice){
            case 1: System.out.println("this is a test"); break;
            case 2: System.out.println("this is a test"); break;
            case 3: System.out.println("this is a test"); break;
            case 4: System.out.println("this is a test"); break;
            case 5: System.out.println("this is a test"); break;
        }
    }

    public static void main(String[] args) {
        MainUserPage( HomeScreen() );
    }
}