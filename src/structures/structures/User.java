package structures;

/* ===== CLASS DESCRIPTION =====
 * 
 * this class is the general manager for a users information.
 * Currently, it stores a username, password, total expenses, and expenses.
 * 
 * Last updated: 10.05.2023
 */


import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable{
    
    private static final long serialVersionUID = 8606117422906450152L;
        
    List<Expenses> expenses;
    List<Income> allIncome;
    String username;
    String password;
    int colourScheme;
    double totalExpenses;
    double totalIncome;


    public static final int DARK_MODE = 0;
    public static final int BABYGIRL_MODE = 1;

    public User(String username, String password) {
        this.expenses = new ArrayList<Expenses>();
        this.allIncome = new ArrayList<Income>();
        this.username = username;
        this.password = password;
        this.colourScheme = 0;
        this.totalExpenses = 0;
        this.totalIncome = 0;
    }

    // getters
    public String getUserName(){return username;}
    public String getPassword(){return password;}
    public int getColourScheme(){return colourScheme;}
    

    /**
     * Determines if the given numerical representation for the GUI color scheme is valid.
     *
     * @param c The numerical representation for the GUI color scheme. The default value is 0.
     * Valid color scheme options (as of 06-27-2023):
     * - black
     * -  babygirl
     * 
     * @return {@code true} if {@code c} is a valid number.
     *
     */
    public boolean setColourScheme(int c){
        int[] options = new int[]{0, 1};

        for(int i = 0; i<options.length; i++){
            if(c == i){
                colourScheme = c;
                return true;
            }
        }
        return false;
    }
    
    public List<Expenses> getExpenses(){return expenses;}
    public Expenses getOneExpense(int i){return expenses.get(i);} // testing purposes only
    public double getTotalExpenses(){return totalExpenses;}

    public List<Income> getAllIncome(){return allIncome;}
    public Income getOneIncome(int i){return allIncome.get(i);} // maybe testing ? idk i have this just in case 
    public double getTotalIncome(){return totalIncome;}

    public double getNetValue(){
        // returns the users profit rounded to 2 decimal places
        return Double.parseDouble(new DecimalFormat("#.##").format(getTotalIncome() - getTotalExpenses()));
    }

    // setters
    public void setExpenses(List<Expenses> e){
        this.expenses = e;
                
        totalIncome = 0;
        if(e.size() == 0){return;}

        for (Expenses expense : e) {
            totalExpenses += expense.getAmount();
        }
    }
    public void setIncome(List<Income> i){
        this.allIncome = i;
        
        totalIncome = 0;
        if(i.size() == 0){return;}

        for (Income income : i) {
            totalIncome += income.getAmount();
        }
    }


    // ********** ALL INCOME ALTERING METHODS ***********

    public void addExpense(String name, double amount, String category) {
        expenses.add(new Expenses(category, name, amount));
        totalExpenses += amount;
    }


    public void addExpense(String category, String name, double amount, LocalDate date){
        this.allIncome.add(new Income(category, name, amount, date));
        this.totalIncome += amount;
    }


    public void removeExpense(String name) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getName().equals(name)) {
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


    // ********** ALL INCOME ALTERING METHODS ***********

    public void addIncome(String name, double amount, String category) {
        this.allIncome.add(new Income(category, name, amount));
        this.totalIncome += amount;
    }


    public void addIncome(String category, String name, double amount, LocalDate date){
        this.allIncome.add(new Income(category, name, amount, date));
        this.totalIncome += amount;
    }


    public void removeIncome(String name) {
        for (int i = 0; i < allIncome.size(); i++) {
            if (allIncome.get(i).getName().equals(name)) {
                this.totalIncome -= allIncome.get(i).getAmount();
                allIncome.remove(i);
                return;
            }
        }
        System.out.println("This income doesn't exist!");
    }


    public void displayAllIncome() {
        for (int i = 0; i < allIncome.size(); i++) {
            allIncome.get(i).displayTransation(i + 1);
            System.out.print("\n");
        }
    }


    public Set<String> getIncomeCategories(){
        Set<String> uniqueIncome = new HashSet<String> ();

        for(int i=0; i< allIncome.size(); i++){
            String currentCategory = allIncome.get(i).getCategory();

            if ( !uniqueIncome.contains(currentCategory) ){
                uniqueIncome.add(currentCategory);
            }
        }

        return uniqueIncome;
    }

    /**
     * Retrieves all the transactions (both expenses and income) for the user.
     * The transactions are sorted based on their timestamp in descending order,
     * so the most recent transactions appear first in the list.
     *
     * @return a list of all transactions, sorted by timestamp in descending order.
     */
    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();

        // Adding expenses to the transaction list
        for (Expenses expense : expenses) {
            allTransactions.add(expense);
        }

        // Adding income to the transaction list
        for (Income income : allIncome) {
            allTransactions.add(income);
        }

        // Sorting the transactions based on timestamp in descending order
        Collections.sort(allTransactions, Collections.reverseOrder());

        System.out.println(allTransactions.toString());

        return allTransactions;
    }


    public List<String> getAllTransactionCategories(){
        List<String> categories = new ArrayList<String>();
        categories.addAll(getExpenseCategories());
        categories.addAll(getIncomeCategories());
        System.out.println(categories.toString());
        return categories;
    }


    public boolean attemptSignin(String password) {
        return password.equals(password);
    }


    public String toString(){
        String toString = username+" has "+expenses.size()+" expense(s) and "+allIncome.size()+" income(s) recorded.";
        return toString;
    }
}
