import java.util.ArrayList;

public class Progetto {
    ArrayList<Dipendente> dipendenti;

    public Progetto(int oreProgetto) {
        this.dipendenti = new ArrayList<>();
    }
    public void aggiungiDipendente(Dipendente d) {
        dipendenti.add(d);
    }
    public void rimuoviDipendente(Dipendente d) {
        dipendenti.remove(d);
    }
    public double calcolaCostoTotale(int oreProgetto) {
        double costoTotale = 0.0;
        for (Dipendente d : dipendenti) costoTotale += d.costoPerProgetto(oreProgetto);
        return costoTotale;
    }

}
