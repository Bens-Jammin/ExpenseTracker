package handlers;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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

            System.out.println(user.getColourScheme());

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


    public static User loadUser(String username) {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream(FOLDER_NAME + username + FILE_EXTENSION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();  // I dont think i need the structures.User, I think i can just use User, im too scared to tho

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


    public static String deleteAccount(String username) {
        try {
            File directory = new File("UserData");
            File[] files = directory.listFiles();

            String[] targetedFileNames = new String[]{
                    username + "_expenses.ser",
                    username + "_incomes.ser",
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
                System.out.println("ERROR: failed to delete "+ totalFilesNotDeleted + "file(s).");
                throw new Exception("Failed to delete "+ totalFilesNotDeleted + "file(s)");
            }

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    /**
     * Converts a user's transaction data into a CSV file.
     *
     * @param user User object containing the transaction data.
     */
    public static void convertUserToCSV(User user) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save CSV File");

        // Set file extension filter to only show CSV files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".csv")) {
                    // Append the .csv extension if not already present
                    filePath += ".csv";
                }

                FileWriter writer = new FileWriter(filePath);
                // Write the CSV file headers
                writer.write("Username,Password,ColourScheme,Transaction Type,Category Name,Transaction Name,Amount,Date\n");

                // Write the user's information
                writer.write(user.getUserName() + ",");
                writer.write(user.getPassword() + ",");
                writer.write(user.getColourScheme() + "\n");

                // Write the user's transaction data to the CSV file
                List<Expenses> expenses = user.getExpenses();
                if (expenses != null) {
                    for (Expenses expense : expenses) {
                        writer.write(",");  // these account for the username/pwd columns
                        writer.write(",");
                        writer.write(",");
                        writer.write("Expense,");
                        writer.write(expense.getCategory() + ",");
                        writer.write(expense.getName() + ",");
                        writer.write(expense.getAmount() + ",");
                        writer.write(expense.getTimeStamp() + "\n");
                    }
                }

                List<Income> income = user.getAllIncome();
                if (income != null) {
                    for (Income inc : income) {
                        writer.write(",");
                        writer.write(",");  // these account for the username/pwd columns
                        writer.write(",");
                        writer.write("Income,");
                        writer.write(inc.getCategory() + ",");
                        writer.write(inc.getName() + ",");
                        writer.write(inc.getAmount() + ",");
                        writer.write(inc.getTimeStamp() + "\n");
                    }
                }

                writer.close();
                System.out.println("User transaction data has been converted to CSV and saved successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while converting user transaction data to CSV: " + e.getMessage());
            }
        }
    }

    /**
     * Converts a CSV file to a User object containing transaction data.
     *
     * @param filename The name of the CSV file to be converted.
     * @return User object containing the transaction data.
     */
    public static User convertCSVToUser(String filename) {
        User user = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            boolean isFirstLine = true; // Flag to skip the CSV header line

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    String[] userInfo = line.split(",");
                    if (userInfo.length >= 2) {
                        user = new User(userInfo[0], userInfo[1]);
                        user.setColourScheme(Integer.parseInt(userInfo[2]));
                    } else {
                        System.out.println("Invalid format for user info in CSV file.");
                        break; // Exit the loop if the user info is invalid
                    }
                } else {
                    String[] data = line.split(",");
                    if (data.length >= 7) {
                        // Extract transaction data from each line of the CSV
                        String transactionType = data[3];
                        String category = data[4];
                        String transactionName = data[5];
                        double amount = Double.parseDouble(data[6]);
                        LocalDate date = LocalDate.parse(data[7]);

                        // Create Expenses or Income objects based on the transaction type
                        if (transactionType.equalsIgnoreCase("Expense")) {
                            if (user != null) {
                                user.addExpense(category, transactionName, amount, date);
                            }
                        } else if (transactionType.equalsIgnoreCase("Income")) {
                            if (user != null) {
                                user.addIncome(category, transactionName, amount, date);
                            }
                        }
                    } else {
                        System.out.println("Invalid format for transaction data in CSV file: " + line);
                    }
                }
            }

            System.out.println("CSV file has been converted to User object.");
        } catch (Exception e) {
            System.out.println("An error occurred while converting CSV file to User object: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while closing the file reader: " + e.getMessage());
                }
            }
        }

        return user;
    }


    /**
     * Saves the new last signed-in user to a file.
     * If the file already exists and is not empty, the method returns false.
     * If the username is null, the method empties the file and returns true.
     * Otherwise, the username is written to the file and the method returns true.
     * If there is an error while performing the file operations, null is returned.
     *
     * @param username the username of the new last signed-in user
     * @return true if the new last signed-in user is successfully saved or the file is emptied,
     *         false if the file already has content,
     *         null if there is an error while performing file operations
     */
    public static boolean saveNewLastUser(String username) {
        File file = new File("UserData/LastUserinformation.txt");


        // empty the file
        if (username == null) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                // clears the file
                fileWriter.write("");

                fileWriter.flush();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // user is actually trying to be saved
            try (FileWriter fileWriter = new FileWriter(file)) {
                // Write the username and password to the file
                fileWriter.write(username);
                fileWriter.flush();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    /**
     * Loads the username of the last signed-in user from a file.
     * If the file does not exist or is empty, null is returned.
     * Otherwise, the username stored in the file is returned.
     * If there is an error while performing the file operations, null is returned.
     *
     * @return the username of the last signed-in user,
     *         null if the file does not exist, is empty, or there is an error while performing file operations
     */
    public static String loadLastSignedUser() {
        File file = new File("UserData/LastUserinformation.txt");

        // Check if the file exists and is not empty
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
