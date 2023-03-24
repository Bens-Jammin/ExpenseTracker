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
            System.out.println("User data is saved in " +FOLDER_NAME + user.getUserName() + FILE_EXTENSION);
            saveExpenses(user.getUserName(),user.getExpenses());
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


    public static User loadUser(String username) {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream(FOLDER_NAME + username + FILE_EXTENSION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("User data is loaded from " + FOLDER_NAME + username + FILE_EXTENSION);
            List<Expenses> loadedExpenses = loadExpenses(username);
            for(Expenses e : loadedExpenses){
                String name = e.getExpenseName();
                double amount = e.getAmount();
                String category = e.getCategory();
                user.addExpense(name, amount, category);
            }

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
}
