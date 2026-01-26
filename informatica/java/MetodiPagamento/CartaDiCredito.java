public class CartaDiCredito implements MetodoPagamento {
    private double saldo;
    private String numeroCarta;

//constructor
    public CartaDiCredito(String numeroCarta, double saldoIniziale){
        this.numeroCarta = numeroCarta;
        this.saldo = saldoIniziale;
    }

//getters
    public String getNumeroCarta(){
        return numeroCarta;
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
            if(importo + (importo * 0.02) > saldo || importo <= 0){
                System.out.println("\nerrore durante l'effettuazione del pagamento con Carta di Credito. Numero Carta: " + numeroCarta + ", Importo: " + importo + ", saldo rimanente: " + saldo);
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        saldo -= (importo + (importo * 0.02)); // add commission of 2%
        System.out.println("\nPagamento effettuato con Carta di Credito. Numero Carta: " + numeroCarta + ", Importo: " + importo + ", Commissione: " + (importo * 0.02) + ", saldo rimanente: " + saldo);
        return true;
    }
}
