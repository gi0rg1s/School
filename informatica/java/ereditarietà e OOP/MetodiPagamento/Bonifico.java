public class Bonifico implements MetodoPagamento {
    private double saldo;
    private String iban;

//constructor
    public Bonifico(String iban, double saldoIniziale){
        this.iban = iban;
        this.saldo = saldoIniziale;
    }
//getters
    public String getIban(){
        return iban;
    }
    public double getSaldo(){
        return saldo;
    }
//setters
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    @Override
    public boolean paga(double importo){
        try {
            if(importo <= 0 || importo > saldo){
                throw new Exception("\nerrore durante l'effettuazione del pagamento con Bonifico. IBAN: " + iban + ", Importo: " + importo + ", saldo rimanente: " + saldo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        saldo -= importo;
        System.out.println("\nPagamento effettuato con Bonifico. IBAN: " + iban + ", Importo: " + importo + ", saldo rimanente: " + saldo);
        return true;
    }

}
