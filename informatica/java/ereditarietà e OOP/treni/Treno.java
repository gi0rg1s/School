import java.util.ArrayList;

public class Treno {
    private ArrayList<Vagoni> vagoni;

    public Treno() {
        this.vagoni = new ArrayList<>();
    }

    public void aggiungiVagone(Vagoni vagone) {
        if (vagone == null) throw new IllegalArgumentException("Il vagone non può essere null.");
        if (vagoni.contains(vagone)) throw new IllegalArgumentException("Il vagone è già presente nel treno.");
        if (vagone.getPesoVuoto() <= 0) throw new IllegalArgumentException("Il peso del vagone deve essere maggiore di zero.");
        vagoni.add(vagone);
    }
    public void rimuoviVagone(Vagoni vagone) {
        if (vagone == null) throw new IllegalArgumentException("Il vagone non può essere null.");
        if (!vagoni.contains(vagone)) throw new IllegalArgumentException("Il vagone non è presente nel treno.");
        vagoni.remove(vagone);
    }

    public double getPesoTotaleVagoni(){
        double pesoTotale = 0;
        for (Vagoni vagone : vagoni) {
            pesoTotale += vagone.getPesoTotale();
        }
        return pesoTotale;
    }
    public String toString() {
        String s = "Treno composto da:\n";
        for (Vagoni vagone : vagoni) {
            s += vagone.toString() + "\n";
        }
        return s;
    }
}
