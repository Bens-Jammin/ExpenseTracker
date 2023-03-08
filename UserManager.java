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
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(user.username + "," + user.password);
            for (int i = 0; i < user.expensesCount; i++) {
                Expenses expense = user.expenses[i];
                writer.println("expense," + expense.getExpenseName() + "," + expense.getAmount() + "," + expense.getCategory());
            }
            for (int i = 0; i < user.billsCount; i++) {
                Bills bill = user.bills[i];
                writer.println("bill," + bill.getBillName() + "," + bill.getAmount() + "," + bill.getCategory() + "," + bill.getRecipient() + "," + bill.getDueDate());
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
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
    

    public static void addUser(User newUser) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(newUser.username + "," + newUser.password);
            for (int i = 0; i < newUser.expensesCount; i++) {
                Expenses expense = newUser.expenses[i];
                writer.println("expense," + expense.getExpenseName() + "," + expense.getAmount() + "," + expense.getCategory());
            }
            for (int i = 0; i < newUser.billsCount; i++) {
                Bills bill = newUser.bills[i];
                writer.println("bill," + bill.getBillName() + "," + bill.getAmount() + "," + bill.getCategory() + "," + bill.getRecipient() + "," + bill.getDueDate());
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }
}
