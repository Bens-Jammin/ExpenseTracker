import datetime

class Expenses:
    def __init__(self, name, amount, category):
        self.name = name
        self.amount = amount
        self.category = category
        self.day = datetime.date.today()

    def getName(self):
        return self.name
    
    def getAmount(self):
        return self.amount
    
    def getCategory(self):
        return self.category
    
    def getDate(self):
        return self.date
