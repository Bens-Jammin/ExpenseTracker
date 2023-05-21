import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class ExcelManager {
    public static void RunPythonFunction(String functionName) {
        try {
            String pythonFilePath = "C:/Users/benem/OneDrive/Documents/GitHub/ExpenseTracker/ExcelFunctions.py";
            String functionPath = pythonFilePath + functionName + ".py";

            // Create the ProcessBuilder with the Python command and script path
            ProcessBuilder processBuilder = new ProcessBuilder("python", functionPath);
            
            Process process = processBuilder.start();
            
            // Get the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Wait for the process to finish
            int exitCode = process.waitFor();
            
            // Check the exit code
            if (exitCode != 0){System.out.println("Python Script failed. ");}
            
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
        String filePath = "path/to/your/file.csv";
        
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.append("Name,Email,Phone\n");
            
            // Write data rows
            writer.append("John Doe,johndoe@example.com,1234567890\n");
            writer.append("Jane Smith,janesmith@example.com,9876543210\n");
            
            System.out.println("CSV file created successfully.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void updateCSVFile(String filePath, String[] data) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            // Append data rows
            for (String row : data) {
                writer.append(row).append("\n");
            }
            
            System.out.println("CSV file updated successfully.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
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