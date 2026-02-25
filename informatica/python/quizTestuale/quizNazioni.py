from nazioni import countries
import random

def schermataInit():
    print("+===============================+")
    print("|     benvenuto nel gioco!      |")
    print("+===============================+")
    print("| ecco la tua domanda:          |")
    

def randomQuestion(points):
    temp = []
    
    question = random.randint(0, 1)
    country = countries[random.randint(0, len(countries) - 1)]
    temp.append(country)    
    
    while (len(temp) < 4):
        c = random.randint(0, len(countries) - 1)
        if(countries[c] not in temp): temp.append(countries[c])
        
    random.shuffle(temp)
    
    if(question == 0):
        randomNazione(country, temp)
        choice  = int(input("--> ")) -1
        if(temp[choice]['name'] == country['name']): 
            return (points + 1)
    else:
        randomCapitale(country, temp)
        choice = int(input("--> ")) - 1
        if(temp[choice]['capital'] == country["capital"]): 
            return (points + 1)
    
    return points

    
def randomNazione(country, arr):
    print(f"| in quale nazione si trova {country['capital']}?")
    printOptions(arr, "name")

def randomCapitale(country, arr):
    print(f"|  qual è la capitale della nazione {country['name']}?")
    printOptions(arr, "capital")

def printOptions(arr, option):
    for i in range(0, 4):
        print(f"{i + 1}. {arr[i][option]}")
        
schermataInit()
points = 0

while(True):
    new_points = randomQuestion(points)
    if(points < new_points):
        points = new_points
        print(f"Congratulazioni hai indovinato! Ora hai {points} punti")
    else:
        print(f"Mi dispiace, hai perso! hai ottenuto un totale di {points} punti")
        break