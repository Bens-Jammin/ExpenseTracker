import java.time.LocalDate;

public class User{
    public static final int INCREMENT = 10;
    Expenses[] expenses;
    int expensesCount;
    Bills[] bills;
    int billsCount;

    String username;
    String password;


    public User(String username, String password){
        this.expenses = new Expenses[10];
        this.bills = new Bills[10];
        this.username = username;
        this.password = password;
    }

    public void addExpense(String name, double amount, String category){
        this.expenses[expensesCount++] = new Expenses(name, amount, category);
    }
    

    public void removeExpense(String name){
        int emptySpot = 0;
        for(int i=0; i<expenses.length;i++){
            if (expenses[i].getExpenseName() == name){
                expenses[i] = null;
                emptySpot = i;
            }
        }
        for(int i = emptySpot+1; i<expenses.length;i++){
            expenses[i-1] = expenses[i];
        }
    }


    public void addBill(String name, double amount, String category, String recipient, LocalDate dueDate){
        this.bills[billsCount++] = new Bills(name, amount, category, recipient, dueDate);
    }


    public void removeBill(String name){
        int emptySpot = 0;
        for(int i=0; i<bills.length;i++){
            if (bills[i].getBillName() == name){
                bills[i] = null;
                emptySpot = i;
            }
        }
        for(int i = emptySpot+1; i<bills.length;i++){
            bills[i-1] = bills[i];
        }
    }


    public void displayAllExpenses(){
        for(int i=0; i<expensesCount; i++){
            expenses[i].displayBill();
            System.out.print("\n");
        }
    }


    public void displayAllBills(){
        for(int i=0; i<billsCount; i++){
            bills[i].displayBill();
            System.out.print("\n");
        }
    }

    public static boolean attemptSignin(User[] data, String username, String password){
        for(int i = 0; i < data.length; i++){
            if(data[i].username.equals(username)){
                return data[i].password.equals(password);
            }
        }
        return false;
    }    
}
