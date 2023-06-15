public class Expenses extends Transaction{

    public Expenses(String name, double amount, String category) {
        super(name, amount, category);
    }

    public void displayTransation(int i){
        System.out.println(SEPARATOR);
        System.out.println("("+i+") Product/Service purchased: "+name+ " ("+category+")");
        System.out.println(" >> Total amount due: $"+amount);
        System.out.println(SEPARATOR);
    }

}

