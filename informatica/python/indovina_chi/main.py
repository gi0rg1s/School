from data import dati_personaggi, dati_domande
from Personaggio import Personaggio
from Domanda import Domanda
from Game import Game

characters = dati_personaggi
questions_list = dati_domande

personaggi_list = [Personaggio(i["nome"], i["professione"], i["nazionalita"], i["epoca"], i["genere"]) for i in dati_personaggi]
questions_list = [Domanda(i["testo"], i["attributo"], i["valore_atteso"]) for i in dati_domande]

game = Game(personaggi_list, questions_list)
game.play()