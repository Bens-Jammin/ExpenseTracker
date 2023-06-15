import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import src.structures.User;


public class ExcelManager {
    private static final String CSV_FILE_PATH = "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/FormattedDataFiles/Data.csv";
    private static final String EXCEL_FILE_PATH = "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/FormattedDataFiles/Data.xlsx";
    private static final String PYTHON_SCRIPT_PATH = "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/ExcelFunctions.py";

    // FIXME: excel file causes an error, something about incorrect file path or extension

    public static void UpdateAllFiles(User user) {
        checkAndCreateCSV();
        updateCSV(user);
        checkAndCreateExcel();
        updateExcel();
    }

    public static void checkAndCreateCSV() {
        File csvFile = new File(CSV_FILE_PATH);
        
        if (csvFile.exists()) {
            csvFile.delete();
        }

        try {
            boolean created = csvFile.createNewFile();
            if (created) {
                System.out.println("CSV file created successfully.");

                // Write the header row
                FileWriter csvWriter = new FileWriter(csvFile);
                csvWriter.append("Category,Name,Amount");
                csvWriter.append("\n");
                csvWriter.flush();
                csvWriter.close();
            } else {
                System.out.println("Failed to create CSV file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the CSV file: " + e.getMessage());
        }
    }

    public static void updateCSV(User user) {
        try {
            FileWriter csvWriter = new FileWriter(CSV_FILE_PATH, true);

            for(Expenses expense : user.getExpenses()){
                String row = expense.getCategory() + "," + expense.getName() + "," + String.valueOf(expense.getAmount());
                csvWriter.append(row);
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while updating the CSV file: " + e.getMessage());
        }
    }

    public static void checkAndCreateExcel() {
        File excelFile = new File(EXCEL_FILE_PATH);
        
        if (excelFile.exists()) {
            excelFile.delete();
        }

        try {
            boolean created = excelFile.createNewFile();
            if (created) {
                System.out.println("Excel file created successfully.");
                // Add boilerplate code or perform any necessary initializations
                // to the newly created Excel file here.
            } else {
                System.out.println("Failed to create Excel file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the Excel file: " + e.getMessage());
        }
    }

    public static void updateExcel() {

        try {
            // Create the ProcessBuilder with the Python script and CSV file path as arguments
            ProcessBuilder processBuilder = new ProcessBuilder("python", PYTHON_SCRIPT_PATH, CSV_FILE_PATH);

            // Redirect the error stream to the standard output
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Wait for the process to complete
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Python script executed successfully.");
            } else {
                System.out.println("Error occurred while executing the Python script.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred while executing the Python script: " + e.getMessage());
        }
    }
}
