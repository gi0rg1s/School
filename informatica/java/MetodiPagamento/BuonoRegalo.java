public class BuonoRegalo implements MetodoPagamento {
    private double saldo;
    private String codiceBuono;

    //constructor
    public BuonoRegalo(String codiceBuono, double saldoIniziale){
        this.codiceBuono = codiceBuono;
        this.saldo = saldoIniziale;
    }

    //getters
    public String getCodiceBuono(){
        return codiceBuono;
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
        if(importo > saldo || importo <= 0){
            System.out.println("\nerrore durante l'effettuazione del pagamento con Buono Regalo. Codice Buono: " + codiceBuono + ", Importo: " + importo + ", saldo rimanente: " + saldo);
            return false;
        }

        saldo -= importo;
        System.out.println("\nPagamento effettuato con Buono Regalo. Codice Buono: " + codiceBuono + ", Importo: " + importo + ", saldo rimanente: " + saldo);
        return true;
    }
}
