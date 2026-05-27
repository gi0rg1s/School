class Biblioteca:
    def __init__(self):
        self.books = []

    def addLibro(self, libro):
        if(libro not in self.books):
            self.books.append(libro)

    def rimuoviLibro(self, book):
        if(book in self.books):
            self.books.pop(self.books.index(book))
        
    def mostraTitoli(self):
        s = ''
        for b in self.books:
            s += b.descrittore() + '\n'
        return s

    def libriDisponibili(self):
        for l in self.books:
            if(l.disponibile): print(l.descrittore() + "\n")