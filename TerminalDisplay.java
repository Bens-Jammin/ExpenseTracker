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

    // tbh idk what to call this method, its gonna show editing expenses, etc
    public static void MainUserPage(User user){
        System.out.println(SEPARATOR + "  logged in as : " + user.getUserName() +"  " +  SEPARATOR + "\n\n");

        System.out.println("this is a test");

    }

    public static void main(String[] args) {
        MainUserPage( HomeScreen() );
    }
}