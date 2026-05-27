from libro import Libro
from biblioteca import Biblioteca

l = Libro("Delitto e castigo", "Fëdor Dostoevskij", 700)
b = Biblioteca()
b.addLibro(l)
b.addLibro(Libro("Lettera al padre", "Franz Kafka", 100))

print(b.mostraTitoli())

b.rimuoviLibro(l)
print(b.mostraTitoli())