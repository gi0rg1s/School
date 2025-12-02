public class Furgone extends Veicolo {
    private double capacitaDiCarico;

// constructor
    public Furgone(String targa, int numeroMatricola, String marca, String
            modello, double cilindrata, int annoDiAcquisto, double capacitaSerbatoio, double capacitaDiCarico) {
        super(targa, numeroMatricola, marca, modello, cilindrata, annoDiAcquisto, capacitaSerbatoio);
        this.capacitaDiCarico = capacitaDiCarico;
    }

// getter
    public double getCapacitaDiCarico() {
        return capacitaDiCarico;
    }

// setter
    public void setCapacitaDiCarico(double capacitaDiCarico) {
        this.capacitaDiCarico = capacitaDiCarico;
    }
//methods
    @Override
    public String toString() {
        return "Furgone{" +
                "targa='" + targa + '\'' +
                ", numeroMatricola=" + numeroMatricola +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", cilindrata=" + cilindrata +
                ", annoDiAcquisto=" + annoDiAcquisto +
                ", capacitaSerbatoio=" + capacitaSerbatoio +
                ", capacitaDiCarico=" + capacitaDiCarico +
                '}';
    }
    @Override
    public double calcolaCostoTotale(int giorniNoleggio, double kilometriPercorsi, double statoSerbatoio){
        if (kilometriPercorsi <= 100) kilometriPercorsi = 100;
        return ((70 * giorniNoleggio) + ((kilometriPercorsi - 100) / 30) + 2 * (getCapacitaSerbatoio() - statoSerbatoio));
    }
}