import java.time.LocalDate;
import java.io.Serializable;


public class Bills implements Serializable{
    private static final String SEPARATOR = "##########";
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

    public String getBillName(){return this.name;}
    public double getAmount(){return this.amount;}
    public String getCategory(){return this.category;}
    public String getRecipient(){return this.recipient;}
    public LocalDate getDueDate(){return this.dueDate;}



    public void displayBill(int i){
        System.out.println(SEPARATOR);
        System.out.println("("+i+")"+name+" payment to "+recipient);
        System.out.println(" >> Total amount due: $"+amount);
        System.out.println(" >> Due by "+dueDate.toString());
        if(isOverdue){
            System.out.println("*****");
            System.out.println("WARNING: this payment is currently OVERDUE");
            System.out.println("*****");
        }else{
            System.out.println("This payment is currently not overdue");
        }
        System.out.println(SEPARATOR);
    }
}
