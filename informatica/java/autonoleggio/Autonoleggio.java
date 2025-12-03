import java.util.ArrayList;

public class Autonoleggio {
    ArrayList<Veicolo> veicoli;

    public Autonoleggio() {
        this.veicoli = new ArrayList<>();
    }
    public void aggiungiVeicolo(Veicolo veicolo) {
        veicoli.add(veicolo);
    }
    public void rimuoviVeicolo(Veicolo veicolo) {
        veicoli.remove(veicolo);
    }
    
    public double noleggioVeicolo(Veicolo veicolo, int giorniNoleggio, double kilometriPercorsi, double statoSerbatoio) {
        try {
            if (!veicoli.contains(veicolo)) throw new Exception("Veicolo non disponibile per il noleggio.");
            return veicolo.calcolaCostoTotale(giorniNoleggio, kilometriPercorsi, statoSerbatoio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public String toString(){
        String s = "Autonoleggio{" +
                "veicoli=\n";
        for (Veicolo v : veicoli) 
            s += v.toString() + "\n";
        s += "}";
        return s;
    }
}
