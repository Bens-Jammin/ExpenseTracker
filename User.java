import java.time.LocalDate;

public class User{
    Expenses[] expenses;
    int expensesCount;
    Bills[] bills;
    int billsCount;


    public User(Expenses expense, Bills bill){
        this.expenses[expensesCount++] = expense;
        this.bills[billsCount++] = bill;
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
}
