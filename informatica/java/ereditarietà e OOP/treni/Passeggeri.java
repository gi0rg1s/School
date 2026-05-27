public class Passeggeri extends Vagoni {
    private String classe;
    private int postiDisponibili;
    private int postiOccupati;
    
//constructor
    public Passeggeri(int codice, double peso_vuoto, String aziendaCostruttrice, int annoCostruzione, String classe, int postiDisponibili) {
        super(codice, peso_vuoto, aziendaCostruttrice, annoCostruzione);
        this.classe = classe;
        this.postiDisponibili = postiDisponibili;
        this.postiOccupati = 0;
    }
//getters
    public String getClasse() {
        return classe;
    }
    public int getPostiDisponibili() {
        return postiDisponibili;
    }
    public int getPostiOccupati() {
        return postiOccupati;
    }
//setters
    public void setClasse(String classe) {
        this.classe = classe;
    }
    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }
    public void setPostiOccupati(int postiOccupati) {
        this.postiOccupati = postiOccupati;
    }

//methods
    @Override
    public double getPesoTotale() {
        return getPesoVuoto() + (postiOccupati * 65);
    }
    @Override
    public String toString() {
        return "Vagone Passeggeri [codice=" + codice + ", peso_vuoto=" + peso_vuoto + ", aziendaCostruttrice="
                + aziendaCostruttrice + ", annoCostruzione=" + annoCostruzione + ", classe=" + classe
                + ", postiDisponibili=" + postiDisponibili + ", postiOccupati=" + postiOccupati + "]";
    }

}
