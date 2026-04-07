from mediaItem import MediaItem
from libro import Libro
from rivista import Rivista
from dvd import DVD

class Catalogo:
    def __init__(self):
        self.catalogo = []

    def isInCatalogo(self, title):
        flag = False
        for i in self.catalogo:
            flag = (i.titolo == title)
        return flag
    
    def aggiungi(self, item):
        if self.isInCatalogo(item): return (f"L'elemento {item.__str__()} è già presente all'interno del catalogo")
        self.catalogo.append(item)
        return(f"Elemento {item.__str__()} aggiunto all'interno del catalogo")
    
    def stampaCatalogo(self):
        str = ''
        for i in self.catalogo:
            str += f"{i}\n"
        return str

    def cercaPerTipo(self, tipo):
        risults = []
        for i in self.catalogo:
            if isinstance(i, tipo): risults.append(i)
        return risults
    
    def report(self):
        libri = self.cercaPerTipo(Libro)
        riviste = self.cercaPerTipo(Rivista)
        dvd = self.cercaPerTipo(DVD)

        libriInPrestito = len([i for i in libri if not i.disponibilita])
        rivisteInPrestito = len([i for i in riviste if not i.disponibilita])
        dvdInPrestito = len([i for i in dvd if not i.disponibilita])

        return (
            f"Nel catalogo sono presenti: \n\t- {len(libri)} libri \n\t- {len(riviste)} riviste \n\t- {len(dvd)}"
            f"\nIn prestito: \n\t- {libriInPrestito} libri\n\t- {rivisteInPrestito} riviste \n\t- {dvdInPrestito} dvd"
        )
