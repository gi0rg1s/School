from mediaItem import MediaItem

class Rivista(MediaItem):
    def __init__(self, titolo, anno, numero, mese):
        super().__init__(titolo, anno)
        self.numero = numero
        self.mese = mese

    def prestito(self):
        if(self.disponibilita): 
            self.disponibilita = False
            return ("Hai appena preso in prestito la rivista \n", self.__str__())
        return(f"La rivista {self.__str__()} al momento non è disponibile\n")
    
    def restituzione(self):
        if(not self.disponibilita):
            self.disponibilita = True
            return(f"Hai appena restituito la rivista {self.__str__()}\n")
        return(f"ILa rivista {self.__str__()} è già stato restituito\n")
    
    def __str__(self):
        return f"{super().__str__()}, numero = {self.numero}, mese = {self.mese} ]"