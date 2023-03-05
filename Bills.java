import java.time.LocalDate;

public class Bills{

    String name;
    double amount;
    String category;
    String recipient;
    LocalDate dueDate;
    boolean isOverdue;
    public Bills(String name, double amount, String category, String recipient, LocalDate dueDate){
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.recipient = recipient;
        this.dueDate = dueDate;
    }

    public String getBillName(){
        return this.name;
    }
}