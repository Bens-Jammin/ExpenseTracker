import java.time.LocalDate;

/* CLASS DESCRIPTION
 * 
 * This class is for general expenses only (ie medical, alcohol, etc)
 * This is to track what you spent, and on what
 * This is also used for INCOME, I dont want to make another class thats the exact same for income
 */


public class Expenses {
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


    
    public void displayBill(){
        System.out.println(SEPARATOR);
        System.out.println("Product/Service purchased: "+name+ " ("+category+")");
        System.out.println(" >> Total amount due: $"+amount);
        System.out.println("This payment was made on "+day.toString());
        System.out.println(SEPARATOR);
    }
}
