from mediaItem import MediaItem

class DVD(MediaItem):
    def __init__(self, titolo, anno, regista, durata):
        super().__init__(titolo, anno)
        self.regista = regista
        self.durata = durata

    def prestito(self):
        if(self.disponibilita): 
            self.disponibilita = False
            return ("Hai appena preso in prestito il cd \n", self.__str__())
        return(f"Il cd {self.__str__()} al momento non è disponibile\n")

    def restituzione(self):
        if(not self.disponibilita):
            self.disponibilita = True
            return(f"Hai appena restituito il cd {self.__str__()}\n")
        return(f"Il cd {self.__str__()} è già stato restituito\n")

    def __str__(self):
        return f"{super().__str__()}, regista = {self.regista}, durata = {self.durata} ]"