/* ===== CLASS DESCRIPTION =====
 * 
 * This class is used for saving/storing/fetching userdata.
 * if a username is available (checked via isUserNameAvailable() method ), it can be saved
 * into two different .ser files to store the instance data.
 * The files are saved into the UserData file, and are labeled using as follows:
 * General user information = "{username}.ser"
 * user expense information = "{username}_expenses.ser"
 * 
 * TODO: 
 * 1. add a bill saving and loading (need to be implemented in the user class first!)
 * 2. add a delete user feature
 * 3. change username and passwords? 
 * 4. password overhaul (suggest passwords, ensure passwords are strong, etc)
 * 
 * Last updated: 10.05.2023
 * 
 */


import java.io.*;
import java.util.List;

public class DataManager {

    public static final String FOLDER_NAME = "UserData/";

    public static final String FILE_EXTENSION = ".ser";

    
    public static boolean isUserNameAvailable(String username)
    {
        // this code stolen from:
        // https://stackoverflow.com/questions/4917326/how-to-iterate-over-the-files-of-a-certain-directory-in-java
        File dir = new File(FOLDER_NAME);
        File[] directoryListing = dir.listFiles();
        for (File child : directoryListing) {
            if(child.getName().split(".ser")[0].equals(username)){
                return false;
            }
        }
        return true;
    }

    public static boolean saveUser(User user) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FOLDER_NAME + user.getUserName() + FILE_EXTENSION);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
            saveExpenses(user.getUserName(),user.getExpenses());
            saveIncome(user.getUserName(),user.getAllIncome());
            System.out.println("User data is saved in " +FOLDER_NAME + user.getUserName() + FILE_EXTENSION);
            return true;
        } catch (IOException i) {
            return false;
        }
    }


    public static void saveExpenses(String username, List<Expenses> expenses) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FOLDER_NAME + username + "_expenses" + FILE_EXTENSION);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(expenses);
            out.close();
            fileOut.close();
            System.out.println("Expenses data is saved in " + FOLDER_NAME + username + "_expenses" + FILE_EXTENSION);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    
    public static void saveIncome(String username, List<Income> income) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FOLDER_NAME + username + "_incomes" + FILE_EXTENSION);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(income);
            out.close();
            fileOut.close();
            System.out.println("Incomes data is saved in " + FOLDER_NAME + username + "_incomes" + FILE_EXTENSION);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /* 
     *      ################################
     * 
     *              LOADING USER DATA
     * 
     *      ################################
     */

    public static User loadUser(String username) {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream(FOLDER_NAME + username + FILE_EXTENSION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            in.close();
            fileIn.close();
            List<Expenses> loadedExpenses = loadExpenses(username);
            if(loadedExpenses != null){
                for(Expenses e : loadedExpenses){
                    if(e != null){
                        String name = e.getName();
                        double amount = e.getAmount();
                        String category = e.getCategory();
                        user.addExpense(name, amount, category);
                    }
                }
            }
            
            List<Income> loadedIncome = loadIncome(username);
            if (loadedIncome != null) {
                for (Income i : loadedIncome) {
                    if (i != null) {
                        String name = i.getName();
                        double amount = i.getAmount();
                        String category = i.getCategory();
                        user.addIncome(name, amount, category);
                    }
                }
            }

            
            
            System.out.println("User data is loaded from " + FOLDER_NAME + username + FILE_EXTENSION);

        } catch (IOException i) {
            i.printStackTrace();
        } catch(ClassNotFoundException i){
            return null;
        }
        return user;
    }

    
    public static List<Expenses> loadExpenses(String username) {
        List<Expenses> expenses = null;
        try {
            FileInputStream fileIn = new FileInputStream(FOLDER_NAME + username + "_expenses" + FILE_EXTENSION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            expenses = (List<Expenses>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Expenses data is loaded from " + FOLDER_NAME + username + "_expenses" + FILE_EXTENSION);
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return expenses;
    }


    public static List<Income> loadIncome(String username) {
        List<Income> income = null;
        try {
            FileInputStream fileIn = new FileInputStream(FOLDER_NAME + username + "_incomes" + FILE_EXTENSION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            income = (List<Income>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Income data is loaded from " + FOLDER_NAME + username + "_incomes" + FILE_EXTENSION);
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return income;
    }
}
