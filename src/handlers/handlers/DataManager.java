package handlers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import structures.User;
import structures.Expenses;
import structures.Income;

public class DataManager {

    public static final String FOLDER_NAME = "UserData/";
    public static final String FILE_EXTENSION = ".ser";

    static {
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }


    public static boolean isUserNameAvailable(String username) {
        File dir = new File(FOLDER_NAME);
        File[] directoryListing = dir.listFiles();
        for (File child : directoryListing) {
            if (child.getName().split("\\.ser")[0].equals(username)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Saves the expenses data for a specific user.
     *
     * @param username The username associated with the expenses.
     * @param expenses The list of Expenses objects to save.
     */
    public static boolean saveUser(User user) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FOLDER_NAME + user.getUserName() + FILE_EXTENSION);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();

            saveExpenses(user.getUserName(), user.getExpenses());
            saveIncome(user.getUserName(), user.getAllIncome());

            System.out.println("User data is saved in " + FOLDER_NAME + user.getUserName() + FILE_EXTENSION);
            return true;
        } catch (IOException i) {
            i.printStackTrace();
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


    public static void saveTransactionCategories(String username, List<String> categories) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FOLDER_NAME + username + "_Transaction_Categories" + FILE_EXTENSION);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(categories);
            out.close();
            fileOut.close();
            System.out.println("Tranasaction Categories are saved in " + FOLDER_NAME + username + "_Transaction_Categories" + FILE_EXTENSION);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static User loadUser(String username) {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream(FOLDER_NAME + username + FILE_EXTENSION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (structures.User) in.readObject();  // I dont think i need the structures.User, I think i can just use User, im too scared to tho

            in.close();
            fileIn.close();

            List<Expenses> loadedExpenses = loadExpenses(username);
            if (loadedExpenses != null) {
                user.setExpenses(loadedExpenses);
            }

            List<Income> loadedIncome = loadIncome(username);
            if (loadedIncome != null) {
                user.setIncome(loadedIncome);
            }

            System.out.println("User data is loaded from " + FOLDER_NAME + username + FILE_EXTENSION);
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
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


    public static List<String> loadTransactionCategories(String username) {
        List<String> categories = null;
        try {
            FileInputStream fileIn = new FileInputStream(FOLDER_NAME + username + "_Transaction_Categories" + FILE_EXTENSION);
            if (fileIn.available() > 0) { // Check if the file is not empty
                ObjectInputStream in = new ObjectInputStream(fileIn);
                categories = (List<String>) in.readObject();
                in.close();
                fileIn.close();
                System.out.println("transaction category data is loaded from " + FOLDER_NAME + username + "_Transaction_Categories" + FILE_EXTENSION);
            } else {
                categories = new ArrayList<String>(); // Initialize an empty list
            }
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return categories;
    }


    public static String deleteAccount(String username) {
        try {
            File directory = new File("UserData");
            File[] files = directory.listFiles();

            String[] targetedFileNames = new String[]{
                    username + "_expenses.ser",
                    username + "_incomes.ser",
                    username + "_Transaction_Categories.ser",
                    username + ".ser"
            };
            int deleteCounter = 0;

            if (files != null) {
                for (File file : files) {
                    for (String targetedFileName : targetedFileNames) {
                        if (file.isFile() && file.getName().equals(targetedFileName)) {
                            if (file.delete()) {
                                System.out.println("Deleted file: " + file.getAbsolutePath());
                                deleteCounter++;
                            } else {
                                System.out.println("Failed to delete file: " + file.getAbsolutePath());
                                throw new Exception();
                            }
                        }
                    }
                }
            }

            if ( deleteCounter != targetedFileNames.length ) {
                int totalFilesNotDeleted = targetedFileNames.length - deleteCounter;
                System.out.println("ERROR: failed to delete "+ totalFilesNotDeleted + "files.");
                throw new Exception("Failed to delete "+ totalFilesNotDeleted + "files");
            }

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
