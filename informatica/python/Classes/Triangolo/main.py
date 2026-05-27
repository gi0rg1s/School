from Triangolo import Triangolo
from Punto import Punto

a = Punto(0, 0)
b = Punto(4, 0)
c = Punto(2, 2)

t = Triangolo(a, b, c)

print(t)
print(round(t.calcolaPerimetro(), 2))
print(round(t.calcolaArea(), 2))