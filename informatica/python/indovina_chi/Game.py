import random

class Game:
    def __init__(self, lista_personaggi, lista_domande):
        self.lista_personaggi = lista_personaggi
        self.lista_domande = lista_domande
        self.numero_domanda = 0

    def scegli_personaggio(self):
        self.personaggio_segreto = random.choice(self.lista_personaggi)

    def check_answer(self, domanda):
        if(getattr(self.personaggio_segreto, domanda.attributo) == domanda.valore_atteso):
            print("Risposta: sì")
        else: print("Risposta: no")

    def next_question(self):
        verify = False
        attributes = []
        questions = []
        self.numero_domanda = 0
        print("Scegli una domanda (0 per indovinare)")
        while self.numero_domanda < 3:
            r = random.choice(self.lista_domande)
            if(r.attributo not in attributes):
                questions.append(r)
                attributes.append(r.attributo)
                self.numero_domanda += 1

                print(f"{self.numero_domanda}. {r.testo}")
                
        ans = int(input("> "))

        if(ans < 0 or ans > 3): 
            print("Risposta non valida")
        elif(ans == 0): 
            verify = self.guess_personaggio()
        
        else:
            self.check_answer(questions[ans - 1])
        return verify

    def guess_personaggio(self):
        print("Chi pensi che sia?")
        ans = input("> ")

        if(ans.lower().strip() == self.personaggio_segreto.nome.lower()):
            print("Corretto! Hai indovinato il personaggio!")
            return True
        else: 
            print("Hai sbagliato!")
            False

    def play(self):
        self.scegli_personaggio()
        var = True
        while(var):
            if self.next_question(): var = False