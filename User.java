/* ===== CLASS DESCRIPTION =====
 * 
 * this class is the general manager for a users information.
 * Currently, it stores a username, password, total expenses, and expenses.
 * 
 * Last updated: 10.05.2023
 */


import java.io.Serializable;
import java.text.DecimalFormat;
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
    double totalExpenses;
    double totalIncome;

    public User(String username, String password) {
        this.expenses = new ArrayList<Expenses>();
        this.allIncome = new ArrayList<Income>();
        this.username = username;
        this.password = password;
        this.totalExpenses = 0;
        this.totalIncome = 0;
    }

    // getters
    public String getUserName(){return username;}
    public String getPasword(){return password;}
    
    public List<Expenses> getExpenses(){return (expenses != null) ? expenses : Collections.emptyList();}
    public Expenses getOneExpense(int i){return expenses.get(i);} // testing purposes only
    public double getTotalExpenses(){return totalExpenses;}

    public List<Income> getAllIncome(){return (allIncome != null) ? allIncome : Collections.emptyList();}
    public Income getOneIncome(int i){return allIncome.get(i);} // maybe testing ? idk i have this just in case 
    public double getTotalIncome(){return totalIncome;}

    public double getNetValue(){
        // returns the users profit rounded to 2 decimal places
        return Double.parseDouble(new DecimalFormat("#.##").format(getTotalIncome() - getTotalExpenses()));
    }


    // ********** ALL INCOME ALTERING METHODS ***********

    public void addExpense(String name, double amount, String category) {
        this.expenses.add(new Expenses(name, amount, category));
        this.totalExpenses += amount;
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
        this.allIncome.add(new Income(name, amount, category));
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

        for(int i=0; i< expenses.size(); i++){
            String currentCategory = allIncome.get(i).getCategory();

            if ( !uniqueIncome.contains(currentCategory) ){
                uniqueIncome.add(currentCategory);
            }
        }

        return uniqueIncome;
    }


    public boolean attemptSignin(String password) {
        return password.equals(password);
    }


    public String toString(){
        String toString = username+" has "+expenses.size()+" expense(s),";
        return toString;
    }
}
