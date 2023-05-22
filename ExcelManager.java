import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.List;

public class ExcelManager {
    public static void RunPythonFunction(String functionName, String... functionArgs) {
        try {
            String pythonFilePath = "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/ExcelFunctions.py";
            String functionPath = pythonFilePath + functionName + ".py";

            // Create the ProcessBuilder with the Python command and script path
            ProcessBuilder processBuilder = new ProcessBuilder("python", functionPath);
            
            // Set the function arguments as command-line arguments
            processBuilder.command().addAll(List.of(functionArgs));

            Process process = processBuilder.start();
            
            // Wait for the process to finish
            int exitCode = process.waitFor();
            
            // Check the exit code
            if (exitCode != 0) {
                System.out.println("Python Script failed. Exit Code: " + exitCode);
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* FIXME: 
     * 
     * These have NOT been tested yet, they need to accept a User instance as an input,
     * take their data, then convert into a csv file to be converted to an excel file
     * with RunPythonFunction 
     * 
     */

    public static void CreateCSVFile() {
        String filePath = "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/Data.csv";
        
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.append("Category,Name,Amount\n");

            
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("csv file '" + filePath + "' successfully created.");
    }


    public static void updateCSVFile(User user, String folderPath) {


        // FIXME: this isnt working, may need to redo the whole thing 

        File folder = new File(folderPath);
        File file = new File(folder, "Data.csv");

        if ( !( file.exists() && file.isFile()) )  {
            CreateCSVFile();
        }

        try (FileWriter writer = new FileWriter(file)) {
            // Append data rows
            for (Expenses expense : user.getExpenses()) {
                String row = expense.getCategory() + "," + expense.getExpenseName() + "," + expense.getAmount();
                writer.append(row).append("\n");
            }
            
            System.out.println("CSV file updated successfully.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void CSVToXLConverter(User user, String folderpath){

        updateCSVFile(user, folderpath);

        RunPythonFunction("CreateExcelFile", "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/Data.csv", "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/Data.xlsx");
    }
    /* Here's the demo code for the updateCSVFile function:
      
    public static void main(String[] args) {
        String filePath = "path/to/your/file.csv";
        String[] newData = {
            "Mark Johnson,markjohnson@example.com,1111111111",
            "Emily Brown,emilybrown@example.com,2222222222"
        };
        
        updateCSVFile(filePath, newData);
    }
    
     */
}