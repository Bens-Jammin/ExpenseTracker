package handlers;

import structures.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class YTDSummaryManager {
    private Map<String, Map<Integer, Double>> summaryTable;

    public YTDSummaryManager(User user) {
        summaryTable = new HashMap<>();

        Set<String> expenseCategories = user.getExpenseCategories();
        Set<String> incomeCategories = user.getIncomeCategories();

        // Add expense categories to the summary table
        for (String category : expenseCategories) {
            summaryTable.put(category, new HashMap<>());
        }

        // Add income categories to the summary table
        for (String category : incomeCategories) {
            summaryTable.put(category, new HashMap<>());
        }

        List<Expenses> expenses = user.getExpenses();
        List<Income> income = user.getAllIncome();

        // Populate the summary table with expenses
        if(expenses != null){
            for (Expenses expense : expenses) {
                String category = expense.getCategory();
                LocalDate date = expense.getTimeStamp();
                int month = date.getMonthValue();
                double amount = expense.getAmount();
    
                Map<Integer, Double> categoryMap = summaryTable.get(category);
                categoryMap.put(month, categoryMap.getOrDefault(month, 0.0) + amount);
                categoryMap.put(13, categoryMap.getOrDefault(13, 0.0) + amount); // Total year column
            }
        }

        // Populate the summary table with income
        if(income != null){
            for (Income inc : income) {
                String category = inc.getCategory();
                LocalDate date = inc.getTimeStamp();
                int month = date.getMonthValue();
                double amount = inc.getAmount();
    
                Map<Integer, Double> categoryMap = summaryTable.get(category);
                categoryMap.put(month, categoryMap.getOrDefault(month, 0.0) + amount);
                categoryMap.put(13, categoryMap.getOrDefault(13, 0.0) + amount); // Total year column
            }
        }
    }


    public Map<String, Map<Integer, Double>> getSummaryTable() {
        return summaryTable;
    }
        


    public void printSummaryTable() {
        // Print the summary table
        System.out.printf("%-20s", "CATEGORY"); // Category header

        // Print the month headers
        for (int month = 1; month <= 12; month++) {
            System.out.printf("%-10s", LocalDate.of(2023, month, 1).getMonth().name().substring(0, 3));
        }
        System.out.printf("%-10s", "Total");

        System.out.println(); // New line

        // Print the rows for each category
        for (Map.Entry<String, Map<Integer, Double>> entry : summaryTable.entrySet()) {
            String category = entry.getKey();
            Map<Integer, Double> monthMap = entry.getValue();

            System.out.printf("%-20s", category); // Category name

            // Print the monthly amounts
            for (int month = 1; month <= 12; month++) {
                double amount = monthMap.getOrDefault(month, 0.0);
                System.out.printf("%-10.2f", amount);
            }

            // Print the total year amount
            double totalYearAmount = monthMap.getOrDefault(13, 0.0);
            System.out.printf("%-10.2f", totalYearAmount);

            System.out.println(); // New line
        }
    }

}
