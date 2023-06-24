package structures;

import java.time.LocalDate;

public class Income extends Transaction{

    public Income(String category, String name, double amount) {
        super(category, name, amount);
    }

    public Income(String category, String name, double amount, LocalDate date) {
        super(category, name, amount, date);
    }

    public void displayTransation(int i){
        System.out.println(SEPARATOR);
        System.out.println("("+i+") You earned $"+amount+" from "+name+" ("+category+")");
        System.out.println(SEPARATOR);
    }

}