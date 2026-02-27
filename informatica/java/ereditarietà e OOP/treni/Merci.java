public class Merci extends Vagoni {
    private double volumeCarico;
    private double pesoMassimoCarico;
    private double pesoCarico;
// Constructor
    public Merci(int codice, double peso_vuoto, String aziendaCostruttrice, int annoCostruzione, double volumeCarico, double pesoMassimoCarico) {
        super(codice, peso_vuoto, aziendaCostruttrice, annoCostruzione);
        this.volumeCarico = volumeCarico;
        this.pesoMassimoCarico = pesoMassimoCarico;
        this.pesoCarico = 0; // Inizialmente il carico è vuoto
    }
//getters
    public double getVolumeCarico() {
        return volumeCarico;
    }
    public double getPesoMassimoCarico() {
        return pesoMassimoCarico;
    }
    public double getPesoCarico() {
        return pesoCarico;
    }
//setters
    public void setVolumeCarico(double volumeCarico) {
        this.volumeCarico = volumeCarico;
    }
    public void setPesoMassimoCarico(double pesoMassimoCarico) {
        this.pesoMassimoCarico = pesoMassimoCarico;
    }
    public void setPesoCarico(double pesoCarico) {
        if(pesoCarico < 0 || pesoCarico > pesoMassimoCarico) 
            throw new IllegalArgumentException("Il peso del carico deve essere compreso tra 0 e il peso massimo consentito.");
        this.pesoCarico = pesoCarico;
    }
    @Override
    public double getPesoTotale() {
        return getPesoVuoto() + pesoCarico;
    }
    @Override
    public String toString() {
        return "Vagone Merci [codice=" + codice + ", peso_vuoto=" + peso_vuoto + ", aziendaCostruttrice="
                + aziendaCostruttrice + ", annoCostruzione=" + annoCostruzione + ", volumeCarico=" + volumeCarico
                + ", pesoMassimoCarico=" + pesoMassimoCarico + ", pesoCarico=" + pesoCarico + "]";
    }
}
