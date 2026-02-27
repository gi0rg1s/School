public class PayPal implements MetodoPagamento {
    private double saldo;
    private String email;

    //constructor
    public PayPal(String email, double saldoIniziale){
        this.email = email;
        this.saldo = saldoIniziale;
    }
    //getters
    public String getEmail(){
        return email;
    }
    public double getSaldo(){
        return saldo;
    }
    //setters
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    //methods
    @Override
    public boolean paga(double importo){
        try {
            if(importo + 0.50 > saldo || importo <= 0){
                throw new Exception("\nerrore durante l'effettuazione del pagamento con PayPal. Email: " + email + ", Importo: " + importo + ", saldo rimanente: " + saldo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        saldo -= (importo + 0.50);

        System.out.println("\nPagamento effettuato con PayPal. Email: " + email + ", Importo: " + importo + ", Commissione: 0.50" + ", saldo rimanente: " + saldo);
        return true;
    }
}