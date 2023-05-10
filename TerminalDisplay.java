/* ===== CLASS DESCRIPTION =====
 * 
 * This class will be what is displayed on the terminal.
 * It will have different methods which are the different "pages" you can visit
 * 
 * last updated: 10.05.2023
 */

 import java.util.Scanner;

class TerminalDisplay extends Display{

    private static final String SEPERATOR = "=====";

    public static void HomeScreen(){
        Display.clearSCreen();
        Scanner scan = new Scanner(System.in);

        System.out.println(SEPERATOR + " welcome ! " + SEPERATOR + "\n\n");
        System.out.println("To login, please enter '1'\nTo signup, please enter'2'.");
        int loginChoice = scan.nextInt();

        if( loginChoice == 1 ){
            Display.login(scan);
        }else{
            Display.createAccount(scan);
        }


    }
}