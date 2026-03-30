from abc import ABC, abstractmethod

class ContoBancario(ABC):
    @abstractmethod
    def deposita(self):
        pass
    
    @abstractmethod
    def preleva(self):
        pass
    
    @abstractmethod
    def saldo(self):
        pass

class ContoCorrente(ContoBancario):
    def deposita(self):
        return "Hai depositato nel conto corrente"
    
    def preleva(self):
        return "Hai prelevato dal conto corrente"
    
    def saldo(self):
        return "Hai 1000€ sul tuo conto corrente"
    
class ContoDiRisparmio(ContoBancario):
    def deposita(self):
        return "Hai depositato nel conto di risparmio"
    
    def preleva(self):
        return "Hai prelevato dal conto di risparmio"
    
    def saldo(self):
        return "Hai 1000€ sul tuo conto di risparmio"
    
    def prelieviMensili(self):
        return "Hai un limite di prelievi mensili pari a: 10"



conti = [ContoCorrente(), ContoDiRisparmio()]

for c in conti: 
    print(c.deposita())
    print(c.preleva())
    print(c.saldo())

cr  = ContoDiRisparmio()
print(cr.prelieviMensili())
