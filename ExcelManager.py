def CreateExcelFile():

    file_name = "data.xlsx"
    folder_path = "D:\Python stuff\Projects\FinanceManager" # TODO: Change file path
                                                            # also how do i dynamically do this
    file_path = os.path.join(folder_path, file_name)

    # Check if the file exists
    if os.path.exists(file_path):
        try:
            os.remove(file_path)
            print(f"File '{file_path}' has been deleted successfully.")
        except FileNotFoundError:
            print(f"File '{file_path}' does not exist.")
        except PermissionError:
            print(f"You do not have permission to delete the file '{file_path}'.")
        except Exception as e:
            print(f"An error occurred: {e}")

    df = pd.read_csv('data.csv')

    # Create a writer object to save the DataFrame to an Excel file
    writer = pd.ExcelWriter(file_path)

    # Convert the DataFrame to an Excel sheet named 'Sheet1'
    df.to_excel(writer, sheet_name='Sheet1', index=False)

    # Save the Excel file
    writer.save()

    print("Excel file created successfully.")
