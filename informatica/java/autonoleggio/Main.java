public class Main {
    public static void main(String[] args) {
        Vettura vettura = new Vettura("AB123CD", 1001, "Fiat", "Panda", 1.2, 2020, 35.0, 5);
        Furgone furgone = new Furgone("EF456GH", 2001, "Ford", "Transit", 2.0, 2019, 80.0, 1000.0); 
        
        System.out.println(vettura.toString());
        System.out.println("Costo totale noleggio vettura: " + vettura.calcolaCostoTotale(3, 150.0, 10.0));
        
        System.out.println(furgone.toString());
        System.out.println("Costo totale noleggio furgone: " + furgone.calcolaCostoTotale(2, 250.0, 20.0));
    }
}
