import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    
    private static final long serialVersionUID = 8606117422906450152L;
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

    public String getUserName(){return username;}
    public String getPasword(){return password;}


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

    public boolean attemptSignin(String username, String password) {
        boolean usernameIsCorrect = ( this.username.equals(username) );
        boolean passwordIsCorrect = ( this.password.equals(password) );
        return usernameIsCorrect && passwordIsCorrect;
    }
}
