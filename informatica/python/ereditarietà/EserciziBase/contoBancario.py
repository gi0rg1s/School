from abc import ABC, abstractmethod

class ContoBancario(ABC):
    def __init__(self, saldo):
        self.saldo = saldo

    @abstractmethod
    def deposita(self, n): pass
    
    @abstractmethod
    def preleva(self, n): pass
        
    
    @abstractmethod
    def saldo(self):
        pass

class ContoCorrente(ContoBancario):
    def __init__(self, saldo):
        super().__init__(saldo)

    def deposita(self, n):
        self.saldo += n
    
    def preleva(self, n):
        if(n > self.saldo): return (f"non hai abbastanza saldo per prelevare {n}€ dal conto corrente")
    
    def saldo(self):
        return (f"Hai {self.saldo}€ sul tuo conto corrente")
    
class ContoDiRisparmio(ContoBancario):
    def __init__(self, saldo, prelievi_mensili):
        super().__init__(saldo)
        self.prelievi_mensili = prelievi_mensili

    def deposita(self, n):
        self.saldo += n
    
    def preleva(self, n):
        if(n > self.saldo): return (f"non hai abbastanza saldo per prelevare {n}€ dal conto bancario")
    
    def saldo(self):
        return (f"Hai {self.saldo}€ sul tuo conto bancario")
    
    def prelieviMensili(self):
        return (f"Hai {self.prelievi_mensili} prelievi ancora a disposizione fino al prossimo mese")



conti = [ContoCorrente(), ContoDiRisparmio()]

for c in conti: 
    print(c.deposita())
    print(c.preleva())
    print(c.saldo())

cr  = ContoDiRisparmio()
print(cr.prelieviMensili())
