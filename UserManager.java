import java.io.*;
import java.time.LocalDate;

public class UserManager {
    static String filename = "logininfo.txt";    

    // NOTE:
    /*
    Please note, this code is NOT TESTED WHATSOEVER,
    I made it via chatGPT, it almost certainly doesn't work.
    To test; 
    - make several users
    -add them to the file.
    - close and open the program
    - try to login
    
    if it doesn't work, do one of two things:
    1) fix it if you have an idea on how to fix it
    2) notify me (Ben) in some way (here in this comment block, discord, etc)
    2.1) tell me what you did to test (ie screenshots)
    
    thanky
    Ben
    
    
    
    */
    public static void saveUser(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             PrintWriter writer = new PrintWriter(new FileWriter(filename + ".tmp"))) {
    
            boolean userExists = false;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].public static synchronized void saveUser(User user) {
                    String tempFileName = filename + ".tmp";
                    try (BufferedReader reader = new BufferedReader(new FileReader(filename));
                         PrintWriter writer = new PrintWriter(new FileWriter(tempFileName))) {
                
                        boolean userExists = false;
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");
                            if (parts.length > 0 && parts[0].equals(user.username)) {
                                // user already exists, edit the line
                                writer.print(user.username + "," + user.password);
                                for (int i = 0; i < user.expensesCount; i++) {
                                    Expenses expense = user.expenses[i];
                                    writer.print(",expense," + expense.getExpenseName() + "," + expense.getAmount() + "," + expense.getCategory());
                                }
                                for (int i = 0; i < user.billsCount; i++) {
                                    Bills bill = user.bills[i];
                                    writer.print(",bill," + bill.getBillName() + "," + bill.getAmount() + "," + bill.getCategory() + "," + bill.getRecipient() + "," + bill.getDueDate());
                                }
                                writer.println("");
                                userExists = true;
                            } else {
                                writer.println(line);
                            }
                        }
                
                        if (!userExists) {
                            // user does not exist in the file, append it to the end
                            writer.print(user.username + "," + user.password);
                            for (int i = 0; i < user.expensesCount; i++) {
                                Expenses expense = user.expenses[i];
                                writer.print(",expense," + expense.getExpenseName() + "," + expense.getAmount() + "," + expense.getCategory());
                            }
                            for (int i = 0; i < user.billsCount; i++) {
                                Bills bill = user.bills[i];
                                writer.print(",bill," + bill.getBillName() + "," + bill.getAmount() + "," + bill.getCategory() + "," + bill.getRecipient() + "," + bill.getDueDate());
                            }
                            writer.println("");
                        }
                    } catch (IOException e) {
                        System.out.println("Error saving user data: " + e.getMessage());
                        return;
                    }
                
                    // replace the original file with the updated file
                    try {
                        Files.move(Paths.get(tempFileName), Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println("Error replacing file: " + e.getMessage());
                    }
                }
                equals(user.username)) {
                    // user already exists, edit the line
                    writer.print(user.username + "," + user.password);
                    for (int i = 0; i < user.expensesCount; i++) {
                        Expenses expense = user.expenses[i];
                        writer.print(",expense," + expense.getExpenseName() + "," + expense.getAmount() + "," + expense.getCategory());
                    }
                    for (int i = 0; i < user.billsCount; i++) {
                        Bills bill = user.bills[i];
                        writer.print(",bill," + bill.getBillName() + "," + bill.getAmount() + "," + bill.getCategory() + "," + bill.getRecipient() + "," + bill.getDueDate());
                    }
                    writer.println("");
                    userExists = true;
                } else {
                    writer.println(line);
                }
            }
    
            if (!userExists) {
                // user does not exist in the file, append it to the end
                writer.print(user.username + "," + user.password);
                for (int i = 0; i < user.expensesCount; i++) {
                    Expenses expense = user.expenses[i];
                    writer.print(",expense," + expense.getExpenseName() + "," + expense.getAmount() + "," + expense.getCategory());
                }
                for (int i = 0; i < user.billsCount; i++) {
                    Bills bill = user.bills[i];
                    writer.print(",bill," + bill.getBillName() + "," + bill.getAmount() + "," + bill.getCategory() + "," + bill.getRecipient() + "," + bill.getDueDate());
                }
                writer.println("");
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    
        // replace the original file with the updated file
        File originalFile = new File(filename);
        originalFile.delete();
        File updatedFile = new File(filename + ".tmp");
        updatedFile.renameTo(originalFile);
    }

    public static User[] loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            User[] users = new User[count];
            int i = 0;
            try (BufferedReader newReader = new BufferedReader(new FileReader(filename))) {
                while ((line = newReader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields[0].equals("expense")) {
                        users[i].addExpense(fields[1], Double.parseDouble(fields[2]), fields[3]);
                    } else if (fields[0].equals("bill")) {
                        LocalDate dueDate = LocalDate.parse(fields[5]);
                        users[i].addBill(fields[1], Double.parseDouble(fields[2]), fields[3], fields[4], dueDate);
                    } else {
                        users[i] = new User(fields[0], fields[1]);
                    }
                    i++;
                }
                return users;
            } catch (IOException e) {
                System.out.println("Error loading user data: " + e.getMessage());
                return new User[0];
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
            return new User[0];
        }
    }
}
