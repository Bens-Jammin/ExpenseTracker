import java.io.Serializable;

public abstract class Transaction implements Serializable {
    protected static final long serialVersionUID = 8606117422906450152L;
    protected static final String SEPARATOR = "##########";
    protected String name;
    protected double amount;
    protected String category;

    public Transaction(String name, double amount, String category){
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    // Getters & Setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public double getAmount(){return amount;}
    public void setAmount(Double amount){this.amount = amount;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public void displayTransation(int i){}
}