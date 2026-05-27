from abc import abstractmethod

class MediaItem():
    def __init__(self, titolo, anno):
        self.titolo = titolo
        self.anno = anno
        self.disponibilita = True
    
    @abstractmethod
    def prestito(self): pass

    @abstractmethod
    def restituzione(self): pass

    def __str__(self):
        return (f"[ Titolo = {self.titolo}, Anno = {self.anno}, Disponibile = {self.disponibilita}")
    