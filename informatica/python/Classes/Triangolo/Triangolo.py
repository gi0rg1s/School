import math

class Triangolo:
    #constructor
    def __init__(self, p1, p2, p3):
        self.p1 = p1
        self.p2 = p2
        self.p3 = p3

    #to string
    def __str__(self):
        return f"A ({self.p1.getX()},{self.p1.getY()}), B ({self.p2.getX()},{self.p2.getY()}), C ({self.p3.getX()}, {self.p3.getY()})"
    
    #getter
    def getPuntoA(self):
        return self.p1
    def getPuntoB(self):
        return self.p2
    def getPuntoC(self):
        return self.p3
    
    #setter
    def setPuntoA(self, x, y):
        self.p1.setX(x)
        self.p1.setY(y)
    def setPuntoB(self, x, y):
        self.p2.setX(x)
        self.p2.setY(y)
    def setPuntoC(self, x, y):
        self.p3.setX(x)
        self.p3.setY(y)

    #calculate perimeter
    def calcolaPerimetro(self):
        return (self.getPuntoA().calcolaDistanza(self.p2) + self.getPuntoB().calcolaDistanza(self.p3) + self.getPuntoC().calcolaDistanza(self.p1))
    
    def calcolaArea(self):
        return math.sqrt(((self.calcolaPerimetro() / 2) - self.getPuntoA().calcolaDistanza(self.getPuntoB())) * 
                         ((self.calcolaPerimetro() / 2) - self.getPuntoB().calcolaDistanza(self.getPuntoC())) * 
                         ((self.calcolaPerimetro() / 2) - self.getPuntoC().calcolaDistanza(self.getPuntoA())))
                         