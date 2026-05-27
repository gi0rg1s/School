from menu import Menu, MenuItem
from coffee_maker import CoffeeMaker
from money_machine import MoneyMachine

moneyMachine = MoneyMachine()
coffeMaker= CoffeeMaker()
menu = Menu()

continueOrdering = True

while(continueOrdering):
    print("Welcome to the Coffee Maker Machine!\n")
    print("What would you like to order?\n")
    
    menuItems = menu.menu
    howManyItems = 0

    for i in menuItems:
        print(f"{howManyItems + 1}. {i.name}  {i.cost}$")
        howManyItems += 1
    
    print("-----------------------------")
    choice = input("Insert the order name: ").lower()

    if choice == "report":
        coffeMaker.report()
        moneyMachine.report()

    else:
        drink = menu.find_drink(choice)

        if(not drink):
            continueOrdering = False

        else: 
            print(f"The cost for {choice} is {drink.cost}$")
            print("----------------------------------------")
            continueOrdering = moneyMachine.make_payment(drink.cost)
            
            print("----------------------------------------")

            if not (coffeMaker.is_resource_sufficient(drink)):
                continueOrdering = False
            else:
                coffeMaker.make_coffee(drink)

            if continueOrdering:
                print("--------------------------------------")
                newOrder = input("\nDo you want to order something else?(Y/N) ").lower()

                if newOrder == "n":
                    continueOrdering = False

print("Exiting the machine...")