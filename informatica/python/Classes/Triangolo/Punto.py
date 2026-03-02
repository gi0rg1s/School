import math

class Punto:
    #constructor
    def __init__(self, x, y):
        self.x = x
        self.y = y

    #to string
    def __str__(self, x, y):
        return f"X : {x}, Y : {y}"
    
    #getters
    def getX(self):
        return self.x
    def getY(self):
        return self.y
    
    #setters
    def setX(self, x):
        self.x = x
    def setY(self, y):
        self.y = y

    #calculate distance
    def calcolaDistanza(self, p2):
        return math.sqrt(math.pow((self.getX() - p2.getX()), 2) + math.pow((self.getY() - p2.getY()), 2))

    #equals
    def equals(self, p2):
        if (self.getX() == p2.getX()) and (self.getY() == p2.getY()): return True
        return False