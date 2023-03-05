import datetime

class Expenses:
    def __init__(self, name, amount, category):
        self.name = name
        self.amount = amount
        self.category = category
        self.day = datetime.date.today()

    