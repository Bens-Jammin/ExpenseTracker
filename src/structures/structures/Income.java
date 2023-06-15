package structures;

public class Income extends Transaction{

    public Income(String name, double amount, String category) {
        super(name, amount, category);
    }

    public void displayTransation(int i){
        System.out.println(SEPARATOR);
        System.out.println("("+i+") You earned $"+amount+" from "+name+" ("+category+")");
        System.out.println(SEPARATOR);
    }

}