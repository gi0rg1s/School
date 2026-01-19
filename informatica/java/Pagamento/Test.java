import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Pagabile> listaPagabili = new ArrayList<>();
        ProdottoSingolo prodotto1 = new ProdottoSingolo("Libro", 25.0);
        AbbonamentoMensile abbonamento1 = new AbbonamentoMensile(10.0);
        listaPagabili.add(prodotto1);
        listaPagabili.add(abbonamento1);

        double totaleGenerale = 0.0;
        for (Pagabile pagabile : listaPagabili) totaleGenerale += pagabile.calcolaTotale();
        System.out.println("Totale generale da pagare: " + totaleGenerale);

    }
}
