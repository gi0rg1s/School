public abstract class Vagoni {
    protected int codice;
    protected double peso_vuoto;
    protected String aziendaCostruttrice;
    protected int annoCostruzione;
    public static int num = 0;
// Constructor
    public Vagoni(int codice, double peso_vuoto, String aziendaCostruttrice, int annoCostruzione) {
        this.codice = num++;
        this.peso_vuoto = peso_vuoto;
        this.aziendaCostruttrice = aziendaCostruttrice;
        this.annoCostruzione = annoCostruzione;
    }
//getters
    public int getCodice() {
        return codice;
    }
    public double getPesoVuoto() {
        return peso_vuoto;
    }
    public String getAziendaCostruttrice() {
        return aziendaCostruttrice;
    }
    public int getAnnoCostruzione() {
        return annoCostruzione;
    }
//setters
    public void setCodice(int codice) {
        this.codice = codice;
    }
    public void setPesoVuoto(double peso_vuoto) {
        this.peso_vuoto = peso_vuoto;
    }
    public void setAziendaCostruttrice(String aziendaCostruttrice) {
        this.aziendaCostruttrice = aziendaCostruttrice;
    }
    public void setAnnoCostruzione(int annoCostruzione) {
        this.annoCostruzione = annoCostruzione;
    }

    public abstract double getPesoTotale();

    public abstract String toString();
}