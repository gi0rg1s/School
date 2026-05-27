public class Vettura extends Veicolo {
    private int numeroPosti;

// constructor
    public Vettura(String targa, int numeroMatricola, String marca, String
            modello, double cilindrata, int annoDiAcquisto, double capacitaSerbatoio, int numeroPosti) {
        super(targa, numeroMatricola, marca, modello, cilindrata, annoDiAcquisto, capacitaSerbatoio);
        this.numeroPosti = numeroPosti;
    }

// getter
    public int getNumeroPosti() {
        return numeroPosti;
    }

// setter
    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }
//methods
    @Override
    public String toString() {
        return "Vettura{" +
                "targa='" + targa + '\'' +
                ", numeroMatricola=" + numeroMatricola +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", cilindrata=" + cilindrata +
                ", annoDiAcquisto=" + annoDiAcquisto +
                ", capacitaSerbatoio=" + capacitaSerbatoio +
                ", numeroPosti=" + numeroPosti +
                '}';
    }
    @Override
    public double calcolaCostoTotale(int giorniNoleggio, double kilometriPercorsi, double statoSerbatoio){
        return ((50 * giorniNoleggio) + (kilometriPercorsi / 25)) + 2 * (getCapacitaSerbatoio() - statoSerbatoio);
    }
}