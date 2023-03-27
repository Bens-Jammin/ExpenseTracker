import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    
    private static final long serialVersionUID = 8606117422906450152L;
    
    public static final int INCREMENT = 10;
    
    List<Expenses> expenses;
    String username;
    String password;
    double budget;
    double totalExpenses;

    public User(String username, String password) {
        this.expenses = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.totalExpenses = 5;
        budget = 100;
    }

    // getters
    public String getUserName(){return username;}
    public String getPasword(){return password;}
    public List<Expenses> getExpenses(){return expenses;}
    public Expenses getOneExpense(int i){return expenses.get(i);} // testing purposes only
    public double getBudget(){return budget;}
    public double getTotalExpenses(){return totalExpenses;}

    public void setBudget(double budget){this.budget = budget;}

    public void addExpense(String name, double amount, String category) {
        this.expenses.add(new Expenses(name, amount, category));
        this.totalExpenses += amount;
    }

    public void removeExpense(String name) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getExpenseName().equals(name)) {
                this.totalExpenses -= expenses.get(i).getAmount();
                expenses.remove(i);
                break;
            }
        }
    }

    public void displayAllExpenses() {
        for (int i = 0; i < expenses.size(); i++) {
            expenses.get(i).displayExpense(i + 1);
            System.out.print("\n");
        }
    }




    public boolean attemptSignin(String username, String password) {
        boolean usernameIsCorrect = ( this.username.equals(username) );
        boolean passwordIsCorrect = ( this.password.equals(password) );
        return usernameIsCorrect && passwordIsCorrect;
    }

    public String toString(){
        String toString = username+", "+password+" : "+expenses.size()+" expense(s),";
        return toString;
    }
}
