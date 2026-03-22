from Game import Game
import sqlite3
from Personaggio import Personaggio
from Domanda import Domanda

def init_db(connection):
    #Initialize the database by running the SQL file if tables don't exist or are empty
    cursor = connection.cursor()
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table' AND name='people'")
    table_exists = cursor.fetchone()

    needs_init = True
    if table_exists:
        cursor.execute("SELECT COUNT(*) FROM people")
        if cursor.fetchone()[0] > 0:
            needs_init = False

    if needs_init:
        f = open("datas.sql", "r")
        sql = f.read()
        connection.executescript(sql)
        connection.commit()
 
def load_data(connection):
    #Load characters and questions from the database
    cursor = connection.cursor()
 
    # Fetch characters from the database
    cursor.execute("SELECT nome, professione, nazionalita, epoca, genere FROM people")
    characters = cursor.fetchall()
 
    # Fetch questions from the database
    cursor.execute("SELECT testo, attributo, valore_atteso FROM questions")
    questions = cursor.fetchall()
 
    return characters, questions
 
# Connect to the database
connection = sqlite3.connect("datas.db")
 
# Initialize DB 
init_db(connection)
 
# Load data from DB
characters, questions = load_data(connection)

#create a list of chars
personaggi_list = [
    Personaggio(nome, professione, nazionalita, epoca, genere) for nome, professione, nazionalita, epoca, genere in characters]
#create a list of questions
questions_list = [Domanda(testo, attributo, valore_atteso) for testo, attributo, valore_atteso in questions]

#close the connection to the database
connection.close()

# create a game instance and start the game
game = Game(personaggi_list, questions_list)
game.play()