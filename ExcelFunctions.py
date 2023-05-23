import os
import pandas as pd

def create_excel_from_csv(csv_file_path):
    # Read the CSV file into a pandas DataFrame
    df = pd.read_csv(csv_file_path)

    # Extract the folder path and file name from the CSV file path
    folder_path, file_name = os.path.split(csv_file_path)

    # Remove the file extension from the file name
    file_name_without_extension = os.path.splitext(file_name)[0]

    # Create the Excel file path
    excel_file_path = os.path.join(folder_path, file_name_without_extension + '.xlsx')

    # Write the DataFrame to an Excel file
    df.to_excel(excel_file_path, index=False)

    print(f"Excel file created successfully at {excel_file_path}")
