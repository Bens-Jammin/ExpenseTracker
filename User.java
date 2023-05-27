/* ===== CLASS DESCRIPTION =====
 * 
 * this class is the general manager for a users information.
 * Currently, it stores a username, password, total expenses, and expenses.
 * 
 * Last updated: 10.05.2023
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable{
    
    private static final long serialVersionUID = 8606117422906450152L;
        
    List<Expenses> expenses;
    String username;
    String password;
    double totalExpenses;

    public User(String username, String password) {
        this.expenses = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.totalExpenses = 5;
    }

    // getters
    public String getUserName(){return username;}
    public String getPasword(){return password;}
    public List<Expenses> getExpenses(){return expenses;}
    public Expenses getOneExpense(int i){return expenses.get(i);} // testing purposes only
    public double getTotalExpenses(){return totalExpenses;}


    public void addExpense(String name, double amount, String category) {
        this.expenses.add(new Expenses(name, amount, category));
        this.totalExpenses += amount;
    }


    public void removeExpense(String name) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getExpenseName().equals(name)) {
                this.totalExpenses -= expenses.get(i).getAmount();
                expenses.remove(i);
                return;
            }
        }
        System.out.println("This expense doesn't exist!");
    }


    public void displayAllExpenses() {
        for (int i = 0; i < expenses.size(); i++) {
            expenses.get(i).displayTransation(i + 1);
            System.out.print("\n");
        }
    }


    public Set<String> getExpenseCategories(){
        Set<String> uniqueExpenses = new HashSet<String> ();

        for(int i=0; i< expenses.size(); i++){
            String currentCategory = expenses.get(i).getCategory();

            if ( !uniqueExpenses.contains(currentCategory) ){
                uniqueExpenses.add(currentCategory);
            }
        }

        return uniqueExpenses;
    }


    public boolean attemptSignin(String username, String password) {
        boolean usernameIsCorrect = ( this.username.equals(username) );
        boolean passwordIsCorrect = ( this.password.equals(password) );
        return usernameIsCorrect && passwordIsCorrect;
    }


    public String toString(){
        String toString = username+" has "+expenses.size()+" expense(s),";
        return toString;
    }
}
