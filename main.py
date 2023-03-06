# Author: Ben Miller
# Project: Expenses Tracker
# Last Edited: March 04 2023

import re

SEPERATOR = "=============================="


def login():
    """
    """
    enteredPassword, actualPassword = " ", ""
    while enteredPassword != actualPassword:
        username = input("Enter username: ")
        enteredPassword = input("Enter password: ")

        with open('logininfo.txt', 'r') as f:
            data = f.read()

        # Extract the password for the specified username
        if username in data:
            start_index = data.index(username + '&&') + len(username + '&&')
            end_index = data.index('\n', start_index)
            actualPassword = data[start_index:end_index]

        if(enteredPassword != actualPassword):
            print("Username or password is ncorrect. \n\n")
    
    print("Welcome back "+username+"!")


def signUp():
    while True:
        username = input("Enter username: ")
        password = input("Enter password: ")
        with open('logininfo.txt', 'r') as f:
            data = f.read()
        
        if username not in data:
            print("Welcome "+username+"!")
            with open('logininfo.txt', 'w') as f:
                f.write(username+"&&"+password)
            return

        print("ERROR: "+username+" is already registered")
        



def displayOptions():
    print(SEPERATOR)
    print("MENU")
    print(SEPERATOR)
    print("1. Add a new expense")
    print("2. View all expenses")
    print("3. View a specific expense")
    print("4. Edit a specific expense")
    print("5. Delete a specific expense")
    selection = int(input("Enter the number of the option you would like: "))
    match selection:
        case 1:
            print("You choce number 1")
        case 2:
            print("You chose number 2")
        case 3:
            print("You chose number 3")
        case 4:
            print("You chose number 4")
        case 5:
            print("You chose number 5")
        case _:
            raise Exception("Not a valid option")





def main():
    print(SEPERATOR)
    print("NOTE: this is only temporary, in the not so near future there is a plan to make a gui.")
    print(SEPERATOR)
    login_or_signup = input("If you are logging in, enter 'LOGIN'\nIf you are signing up, enter 'SIGNUP'. ")
    if login_or_signup.upper() == "LOGIN":
        login()
    elif login_or_signup.upper() == "SIGNUP":
        signUp()
    else:
        print("WRONG")
    displayOptions()


if __name__ == "__main__":
    main()
