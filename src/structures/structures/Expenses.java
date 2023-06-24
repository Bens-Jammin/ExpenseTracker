package structures;

import java.time.LocalDate;

public class Expenses extends Transaction{

    public Expenses(String category, String name, double amount) {
        super(category, name, amount);
    }

    public Expenses(String category, String name, double amount, LocalDate date) {
        super(category, name, amount, date);
    }

    public void displayTransation(int i){
        System.out.println(SEPARATOR);
        System.out.println("("+i+") Product/Service purchased: "+name+ " ("+category+")");
        System.out.println(" >> Total amount due: $"+amount);
        System.out.println(SEPARATOR);
    }

}

