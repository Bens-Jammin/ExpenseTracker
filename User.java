import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    public static final int INCREMENT = 10;
    List<Expenses> expenses;
    List<Bills> bills;
    String username;
    String password;

    public User(String username, String password) {
        this.expenses = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    public void addExpense(String name, double amount, String category) {
        this.expenses.add(new Expenses(name, amount, category));
    }

    public void removeExpense(String name) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getExpenseName().equals(name)) {
                expenses.remove(i);
                break;
            }
        }
    }

    public void addBill(String name, double amount, String category, String recipient, LocalDate dueDate) {
        this.bills.add(new Bills(name, amount, category, recipient, dueDate));
    }

    public void removeBill(String name) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getBillName().equals(name)) {
                bills.remove(i);
                break;
            }
        }
    }

    public void displayAllExpenses() {
        for (int i = 0; i < expenses.size(); i++) {
            expenses.get(i).displayBill(i + 1);
            System.out.print("\n");
        }
    }

    public void displayAllBills() {
        for (int i = 0; i < bills.size(); i++) {
            bills.get(i).displayBill(i + 1);
            System.out.print("\n");
        }
    }

    public static boolean attemptSignin(List<User> data, String username, String password) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).username.equals(username)) {
                return data.get(i).password.equals(password);
            }
        }
        return false;
    }
}
