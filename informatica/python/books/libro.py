class Libro:
    def __init__(self, title, author, pages):
        self.title = title
        self.author = author
        self.pages = pages
        self.disponibile = True

    def presta(self):
        self.disponibile = False

    def restituisci(self):
        self.disponibile = True

    def descrittore(self):
        return (f"Book[ Title = {self.title}, Author = {self.author}, Pages = {self.pages}, Available = {self.disponibile}]")
