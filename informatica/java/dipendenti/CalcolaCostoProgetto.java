import java.util.ArrayList;

public class CalcolaCostoProgetto {
    public static void main(String[] args) {
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        dipendenti.add(new Dirigente("Mario", "Rossi", 2010));
        dipendenti.add(new Funzionari("Luigi", "Verdi", 2015));
        dipendenti.add(new TecnicoInformatico("Anna", "Bianchi", 2018, 1));
        dipendenti.add(new TecnicoElettronico("Giovanni", "Neri", 2019, 0));
        int oreProgetto = 100;
        double costoTotale = 0.0;

        for (Dipendente d : dipendenti) {
            double costo = d.costoPerProgetto(oreProgetto);
            costoTotale += costo;
            System.out.println("Costo per " + d.getNome() + " " + d.getCognome() + ": " + costo);
        }
        System.out.println("-------------------------");
        System.out.println("Costo totale progetto per tutti i dipendenti: " + costoTotale);
    }
}
