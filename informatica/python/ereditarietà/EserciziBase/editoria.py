class Lettore():
    def descrivi(self):
        return "Sono un lettore"
    
class Scrittore():
    def descrivi(self):
        return "Sono uno scrittore"
    
class Amministratore(Lettore, Scrittore):
    def mostra_permessi(self):
        return Amministratore.__mro__



lettore = Lettore()
scrittore = Scrittore()
amministratore = Amministratore()

print("=== Ruoli editoria ===")
print(lettore.descrivi())
print(scrittore.descrivi())
print(amministratore.descrivi())

print("\n=== MRO Amministratore ===")
print(amministratore.mostra_permessi())

