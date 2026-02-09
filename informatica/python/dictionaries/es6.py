rubrica = {}
continua = True

while continua:
    choice = int(input("seleziona un opzione: \n1. aggiungi un contatto \n2. cerca un contatto \n3. visualizza tutti i contatti \n4. esci \n-->"))
    if choice == 1:
        name = input("inserisci il nome per il contatto : ")
        tel = input("inserisci il numero da memorizzare : ")
        rubrica[name] = tel
    elif choice == 2:
        name = input("che persona stai cercando? ")
        for i in rubrica:
            if name == i:
                print(f"il numero di telefono {tel} appartiene a {i}")
    elif choice == 3:
        for i in rubrica:
            print(f"Nome: {i}, Telefono : {rubrica[i]}")
    else:
        print("exiting the program ...")
        continua = False