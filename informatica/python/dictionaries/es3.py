grades = {"math": 8,
          "italian": 7,
          "history": 6,
          "english": 9
          }

average = 0
for i in grades:
    print(f"{i} : ", grades[i])
    average += grades[i]

print(f"la media dei voti è {average/len(grades)}")