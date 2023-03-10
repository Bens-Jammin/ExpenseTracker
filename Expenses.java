import java.time.LocalDate;
import java.io.Serializable;

public class Expenses implements Serializable{
    private static final String SEPARATOR = "##########";
    private String name;
    private double amount;
    private String category;
    private LocalDate day;  // yyyy-mm-dd format

    public Expenses(String name, double ammount, String category){
        this.name = name;
        this.amount = ammount;
        this.category = category;
        this.day = LocalDate.now();
    }

    // Getters & Setters
    public String getExpenseName() {return name;}
    public void setExpenseName(String name) {this.name = name;}
    
    public double getAmount(){return amount;}
    public void setAmount(Double amount){this.amount = amount;}
    
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public LocalDate getDate(){return day;}


    
    public void displayBill(int i){
        System.out.println(SEPARATOR);
        System.out.println("("+i+")Product/Service purchased: "+name+ " ("+category+")");
        System.out.println(" >> Total amount due: $"+amount);
        System.out.println(" >> This payment was made on "+day.toString());
        System.out.println(SEPARATOR);
    }
}
