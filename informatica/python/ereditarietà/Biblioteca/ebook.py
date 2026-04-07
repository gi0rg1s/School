from prestabile import Prestabile
from mediaItem import MediaItem

class Ebook(MediaItem, Prestabile):
    def __init__(self, titolo, anno, formato, dimensione_mb):
        super().__init__(titolo, anno)
        self.formato = formato
        self.dimensione_mb = self.dimensione_mb
        self.num_prestiti = 0

    def prestito(self):
        if(self.disponibilita): 
            self.disponibilita = False
            self.num_prestiti += 1
            return ("Hai appena preso in prestito il libro \n", self.__str__())
        return(f"Il libro {self.__str__()} al momento non è disponibile\n")
    
    def restituzione(self):
        if(not self.disponibilita):
            self.disponibilita = True
            return(f"Hai appena restituito il libro {self.__str__()}\n")
        return(f"Il libro {self.__str__()} è già stato restituito\n")
    
    def statistiche(self):
        return (f"EBook {self.__str__()}, prestato {self.num_prestiti} volte")

    def __str__(self):
        return (super().__str__(), f", formato = {self.formato}, dimensione = {self.dimensione_mb} Mb ]")
    
