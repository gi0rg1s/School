import random

class Game:
    # constructor
    def __init__(self, lista_personaggi, lista_domande):
        self.lista_personaggi = lista_personaggi
        self.lista_domande = lista_domande
        self.numero_domanda = 0
        self.already_asked = []

    #choose a character randomly 
    def scegli_personaggio(self):
        self.personaggio_segreto = random.choice(self.lista_personaggi)

    # verify if the answer to the question is correct
    def check_answer(self, domanda):
        if getattr(self.personaggio_segreto, domanda.attributo) == domanda.valore_atteso:
            print("Risposta: sì")
        else:
            print("Risposta: no")

    # ask the next question
    def next_question(self):
        verify = False
        attributes = []
        questions = []
        self.numero_domanda = 0
        print("Scegli una domanda (0 per indovinare)")

        while len(questions) < 3 :
            r = random.choice(self.lista_domande)
            # Check if the question's attribute has already been asked or if the question itself has already been asked
            if r.attributo not in attributes and r not in self.already_asked and r not in questions:
                self.already_asked.append(r)
                questions.append(r)
                attributes.append(r.attributo)
                self.numero_domanda += 1
                print(f"{self.numero_domanda}. {r.testo}")

            if len(self.already_asked) == len(self.lista_domande):
                print("Non ci sono più domande disponibili. Devi indovinare!")
                break

        ans = int(input("> "))

        if ans < 0 or ans > len(questions):
            print("Risposta non valida")
        elif ans == 0:
            verify = self.guess_personaggio()
        else:
            self.check_answer(questions[ans - 1])
        return verify

    def guess_personaggio(self):
        print("Chi pensi che sia?")
        ans = input("> ")

        if ans.lower().strip() == self.personaggio_segreto.nome.lower():
            print("Corretto! Hai indovinato il personaggio!")
            return True
        
        print("Hai sbagliato!")
        return False

    def play(self):
        self.scegli_personaggio()
        var = True
        while var:
            if self.next_question():
                var = False