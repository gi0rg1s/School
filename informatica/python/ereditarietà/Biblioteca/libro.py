from mediaItem import MediaItem
class Libro(MediaItem):
    def __init__(self, titolo, anno, autore, pagine):
        super().__init__(titolo, anno)
        self.autore = autore
        self.pagine = pagine
    
    def prestito(self):
        if(self.disponibilita): 
            self.disponibilita = False
            return ("Hai appena preso in prestito il libro \n", self.__str__())
        return(f"Il libro {self.__str__()} al momento non è disponibile\n")
    
    def restituzione(self):
        if(not self.disponibilita):
            self.disponibilita = True
            return(f"Hai appena restituito il libro {self.__str__()}\n")
        return(f"Il libro {self.__str__()} è già stato restituito\n")
    
    def __str__(self):
        return f"{super().__str__()}, autore = {self.autore}, numero di pagine = {self.pagine} ]"