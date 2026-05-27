public class CalcolaCostoProgetto {
    public static void main(String[] args) {
        Progetto p = new Progetto(100);
        p.aggiungiDipendente(new Dirigente("Mario", "Rossi", 2010));
        p.aggiungiDipendente(new Funzionari("Luigi", "Verdi", 2015));
        p.aggiungiDipendente(new TecnicoInformatico("Anna", "Bianchi", 2018, 1));
        p.aggiungiDipendente(new TecnicoElettronico("Giovanni", "Neri", 2019, 0));
        int oreProgetto = 100;

        System.out.println("-------------------------");
        System.out.println("Costo totale progetto per tutti i dipendenti: " + p.calcolaCostoTotale(oreProgetto));
    }
}
#think i need to commit
