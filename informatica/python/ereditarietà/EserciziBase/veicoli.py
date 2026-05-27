class Veicolo():
    def __init__(self, marca, max_speed):
        self.marca = marca
        self.max_speed = max_speed
    def descrivi(self):
        return (f"Veicolo [Marca = {self.marca}, Velocità massima =  {self.max_speed} ]")
    
class Auto(Veicolo):
    def __init__(self, marca, max_speed):
        super().__init__(marca, max_speed)

    def descrivi(self):
        return (f"Auto [Marca = {self.marca}, Velocità massima =  {self.max_speed} ]")

class Moto(Veicolo):
    def __init__(self, marca, max_speed):
        super().__init__(marca, max_speed)

    def descrivi(self):
        return (f"Moto [Marca = {self.marca}, Velocità massima =  {self.max_speed} ]")

class Bicicletta(Veicolo):
    def __init__(self, marca, max_speed):
        super().__init__(marca, max_speed)

    def descrivi(self):
        return (f"Bicicletta [Marca = {self.marca}, Velocità massima =  {self.max_speed} ]")





veicoli = [Auto("Fiat", 190), Moto("Yamaha", 240), Bicicletta("Bianchi", 45)]

print("=== Elenco veicoli ===")
for v in veicoli: print(v.descrivi())
