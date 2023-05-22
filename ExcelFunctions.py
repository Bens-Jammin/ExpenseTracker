import os
import pandas as pd

def CreateExcelFile(csv_file_path, excel_file_path):
    # Read the CSV file
    df = pd.read_csv(csv_file_path)

    # Create Excel writer using pandas
    writer = pd.ExcelWriter(excel_file_path, engine='xlsxwriter')

    # Write the DataFrame to the Excel file
    df.to_excel(writer, index=False, sheet_name='Sheet1')

    # Close the Pandas Excel writer and output the Excel file
    writer.save()
    print(f"Excel file '{excel_file_path}' created successfully.")

    df = pd.read_csv('Data.csv')

    # Create a writer object to save the DataFrame to an Excel file
    writer = pd.ExcelWriter(file_path)

    # Convert the DataFrame to an Excel sheet named 'Sheet1'
    df.to_excel(writer, sheet_name='Sheet1', index=False)

    # Save the Excel file
    writer.save()

    print("Excel file created successfully.")
