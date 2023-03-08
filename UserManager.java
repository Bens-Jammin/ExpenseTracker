import java.io.*;
import java.time.LocalDate;

public class UserManager {
    private String filename;

    public UserManager(String filename) {
        this.filename = filename;
    }

    public void saveUser(User user) {
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

    public User[] loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            User[] users = new User[count];
            reader.close();
            reader = new BufferedReader(new FileReader(filename));
            int i = 0;
            while ((line = reader.readLine()) != null) {
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
    }
}
