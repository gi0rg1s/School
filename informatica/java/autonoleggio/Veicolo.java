public abstract class Veicolo {
    protected String targa;
    protected int numeroMatricola;
    protected String marca;
    protected String modello;
    protected double cilindrata;
    protected int annoDiAcquisto;
    protected double capacitaSerbatoio;
//constructor
    public Veicolo(String targa, int numeroMatricola, String marca, String modello, double cilindrata, int annoDiAcquisto, double capacitaSerbatoio) {
        this.targa = targa;
        this.numeroMatricola = numeroMatricola;
        this.marca = marca;
        this.modello = modello;
        this.cilindrata = cilindrata;
        this.annoDiAcquisto = annoDiAcquisto;
        this.capacitaSerbatoio = capacitaSerbatoio;
    }

//getters
    public String getTarga() {
        return targa;
    }

    public int getNumeroMatricola() {
        return numeroMatricola;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public double getCilindrata() {
        return cilindrata;
    }

    public int getAnnoDiAcquisto() {
        return annoDiAcquisto;
    }

    public double getCapacitaSerbatoio() {
        return capacitaSerbatoio;
    }

//setters
    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setNumeroMatricola(int numeroMatricola) {
        this.numeroMatricola = numeroMatricola;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public void setCilindrata(double cilindrata) {
        this.cilindrata = cilindrata;
    }

    public void setAnnoDiAcquisto(int annoDiAcquisto) {
        this.annoDiAcquisto = annoDiAcquisto;
    }

    public void setCapacitaSerbatoio(double capacitaSerbatoio) {
        this.capacitaSerbatoio = capacitaSerbatoio;
    }

//methods
    public abstract String toString();
    public abstract double calcolaCostoTotale(int giorniNoleggio, double kilometriPercorsi, double statoSerbatoio);

}
