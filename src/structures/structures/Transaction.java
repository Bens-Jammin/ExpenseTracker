package structures;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Transaction implements Serializable, Comparable<Transaction> {
    protected static final long serialVersionUID = 8606117422906450152L;
    protected static final String SEPARATOR = "##########";
    protected String name;
    protected double amount;
    protected String category;
    protected LocalDate timeStamp;

    
    public Transaction(String category, String name, double amount){
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.timeStamp = LocalDate.now();
    }


    public Transaction(String category, String name, double amount, LocalDate dateStamp){
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.timeStamp = dateStamp;
    }


    // Getters & Setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public double getAmount(){return amount;}
    public void setAmount(Double amount){this.amount = amount;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public LocalDate getTimeStamp(){return timeStamp;}

    public void displayTransation(int i){}

    @Override
    public int compareTo(Transaction otherTransaction) {
        // Compare Expenses based on their timestamp
        return this.getTimeStamp().compareTo(otherTransaction.getTimeStamp());
    }
}