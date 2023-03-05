import datetime

class Bills:
    def __init__(self, name, amount, category, recipient, due_date):
        self.name = name
        self.amount = amount
        self.category = category
        self.recipient = recipient
        self.due_date = due_date
        self.overdue = False