public class Main {
    public static void main(String[] args) {
        Autonoleggio autonoleggio = new Autonoleggio();

        Vettura vettura1 = new Vettura("AB123CD", 1, "Fiat", "Panda", 1.2, 2018, 35, 5);
        Vettura vettura2 = new Vettura("EF456GH", 2, "Ford", "Focus", 1.5, 2020, 50, 5);
        Furgone furgone1 = new Furgone("IJ789KL", 3, "Mercedes", "Sprinter", 2.0, 2019, 70, 1000);

        autonoleggio.aggiungiVeicolo(vettura1);
        autonoleggio.aggiungiVeicolo(vettura2);
        autonoleggio.aggiungiVeicolo(furgone1);

        System.out.println(autonoleggio);

        double costoNoleggioVettura = autonoleggio.noleggioVeicolo(vettura1, 3, 150, 20);
        System.out.println("Costo noleggio vettura1: " + costoNoleggioVettura);

        double costoNoleggioFurgone = autonoleggio.noleggioVeicolo(furgone1, 2, 250, 30);
        System.out.println("Costo noleggio furgone1: " + costoNoleggioFurgone);

        autonoleggio.rimuoviVeicolo(vettura2);
        System.out.println(autonoleggio);
    }
}