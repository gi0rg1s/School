from catalogo import Catalogo
from libro import Libro
from rivista import Rivista
from dvd import DVD

c = Catalogo()

l1 = Libro("Il nome della rosa", 1980, "Umberto Eco", 512)
l2 = Libro("1984", 1949, "George Orwell", 328)
r1 = Rivista("Focus", 2026, 123, "Aprile")
r2 = Rivista("National Geographic", 2025, 45, "Marzo")
d1 = DVD("Inception", 2010, "Christopher Nolan", 148)

c.aggiungi(l1)
c.aggiungi(l2)
c.aggiungi(r1)
c.aggiungi(r2)
c.aggiungi(d1)

l1.prestito()
r1.prestito()
d1.prestito()

print("Catalogo completo:")
print(c.stampaCatalogo())

print("Report:")
print(c.report())