school = {
    "Luca": {"math": 8, "english": 7},
    "Anna": {"math": 9, "english": 8},
    "Marco": {"math": 6, "english": 7}
}

print("voti di Anna")
for i in school["Anna"]:
    print(f'{i} : {school["Anna"][i]}')
    
print(f'\nvoti di Marco \nmath : {school["Marco"]["math"]}')

students = {}
for person in school:
    avr = 0  
    for sbj in school[person]:
        avr += school[person][sbj]
    avr /= len(school[person])
    students[person] = avr
    
print(f"lo studente con la media più alta è : {max(students, key=students.get)}")