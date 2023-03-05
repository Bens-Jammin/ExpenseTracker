import BILLS as b
import EXPENSES as e

class Account:
    def __init__(self) -> None:
        self.expenses = []
        self.bills = []
    
    def add_expense(self, title, amount, category):
        """
        param: title --> String
        param: amount --> float
        param: category --> String
        return: None
        creates a new instance of Expenses, and appends it to the 'expenses' list 
        """
        new_expense = e.Expenses(title, amount, category)
        self.expenses.append(new_expense)

    def delete_expense(self, title):
        """
        param: title --> String
        return: None
        deletes the expense from the 'expenses' array
        """
        for E in self.expenses:
            if e.getName(E) == title:
                self.expenses.remove(e.getName(E))
