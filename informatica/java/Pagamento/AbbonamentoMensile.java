public class AbbonamentoMensile implements Pagabile {
    private double prezzoMensile;
    private int mesi;

    //constructor
    public AbbonamentoMensile(double prezzoMensile) {
        this.prezzoMensile = prezzoMensile;
        this.mesi = 12;
    }
//getter
    public double getPrezzoMensile() {
        return prezzoMensile;
    }
    public int getMesi() {
        return mesi;
    }
//setter
    public void setPrezzoMensile(double prezzoMensile) {
        this.prezzoMensile = prezzoMensile;
    }
    public void setMesi(int mesi) {
        this.mesi = mesi;
    }
//methods
    public double calcolaTotale() {
        return getPrezzoMensile() * getMesi();
    }
}