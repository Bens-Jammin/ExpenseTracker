public class Statistics{

    private static final String SEPERATOR = "##########";

    public static void displayStatistics(User user){
        System.out.println(SEPERATOR);
        System.out.println("Statistics for "+user.username);
        System.out.println(SEPERATOR+"\n\n");
        System.out.println(" >> total expenses: $"+totalExpensesAmount(user));
        System.out.println(" >> total number of expenses: "+user.expenses.size());
        System.out.println(" >> average expense cost: $"+averageExpense(user));
        System.out.println(" >> total left to spend this month: "+compareToBudget(user));
        // add more ?? maybe?

    }

    public static double totalExpensesAmount(User user){
        double total = 0;
        for(int i=0; i<user.expenses.size() ;i++){
            total += user.expenses.get(i).getAmount();
        }

        return total;
    }

    public static double averageExpense(User user){
        double total = 0;
        for(int i=0; i<user.expenses.size();i++){
            total += user.expenses.get(i).getAmount();
        }
        total = total/user.expenses.size();

        return total;
    }

    public static double compareToBudget(User user){
        /*
         * NOTE: 
         * A MONTHLY/QUARTERLY/YEARLY budget should be added, comparing isnt too hard:
         * return user.budget - totalExpensesAmount(user); 
         */
        return -1;  // temporary
    }
}