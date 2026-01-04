import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
/**
 * @(#)TestLambdaPunti.java
 * Dimostra l'uso di lambda expressions in Java con classi BiFunction, BinaryOperator e ArrayList di oggetti Punto.
 * @author zAlberto
 * @version 1.00 2025/11/04
 */
/*
 * TestLambdaPunti.java
 * Lambda expression in Java
 * Una lambda expression è un modo compatto per scrivere metodi anonimi, cioè piccole funzioni senza nome, che possono essere passate come argomento a un metodo.
 * Sintassi base:
 * (parametri) -> { corpo }
 * Esempi:
 * (a, b) -> a + b             // restituisce la somma
 * x -> x * x                  // calcola il quadrato
 * () -> System.out.println("Ciao")  // nessun parametro
 * 
 * Serve per: rendere il codice più breve e leggibile e usare la programmazione funzionale con collezioni e stream
 */

/* Dimostra:
 * BiFunction<T, U, R>	(a, b) -> a + b
 * BinaryOperator<T>	(a, b) -> a + b
 * forEach()	p -> System.out.println(p)
 * Ordinamento	sort()	(p1, p2) -> Integer.compare(p1.getX(), p2.getX())
 * Filtro	stream().filter()	p -> p.getY() > 5
 * Rimozione condizionata	removeIf()	p -> p.getX() < 3
*/
public class TestLambdaPunti {
    public static void main(String[] args) {
        int risultato;
        // BiFunction<T, U, R> è un’interfaccia funzionale (del package java.util.function) che rappresenta una funzione che accetta due argomenti (T e U) e restituisce un risultato (R).
        // BiFunction che somma due numeri interi e restituisce la somma.
        BiFunction<Integer, Integer, Integer> somma = (a, b) -> a + b;

        risultato = somma.apply(5, 7);
        System.out.println("La somma è: " + risultato);

        // BiFunction che concatena due stringhe con uno spazio
        BiFunction<String, String, String> concatena = (s1, s2) -> s1 + " " + s2;

        String frase = concatena.apply("Ciao", "Mondo");
        System.out.println("Risultato: " + frase);
        
        // BiFunction più complessa: calcola il prezzo totale (prezzo * quantità)
        BiFunction<Double, Integer, Double> calcolaTotale = (prezzo, quantita) -> prezzo * quantita;
        
        double totale = calcolaTotale.apply(12.5, 3);
        System.out.println("Totale ordine: " + totale);

        // BinaryOperator<T> è un’interfaccia funzionale che estende BiFunction<T, T, T>, ma più specifica: accetta due argomenti dello stesso tipo e restituisce un risultato dello stesso tipo.
        // BinaryOperator che somma due numeri interi
        BinaryOperator<Integer> somma2 = (a, b) -> a + b;

        risultato = somma2.apply(10, 5);
        System.out.println("Somma: " + risultato);

        // BinaryOperator che restituisce la stringa più lunga
        BinaryOperator<String> piuLunga = (s1, s2) -> 
            (s1.length() >= s2.length()) ? s1 : s2;

        String risultato2 = piuLunga.apply("Ciao", "Arrivederci");
        System.out.println("Stringa più lunga: " + risultato2);

        // Impiego di lambda negli ArrayList

        ArrayList<Punto> punti = new ArrayList<>();
        punti.add(new Punto(3, 7));
        punti.add(new Punto(1, 2));
        punti.add(new Punto(5, 5));
        punti.add(new Punto(2, 9));
        punti.add(new Punto(10, 10));

        // Stampa di tutti i punti
        System.out.println("Tutti i punti:");
        punti.forEach(p -> System.out.println(p));

        // Rimozione: elimina punti con x < 3
        punti.removeIf(p -> p.getX() < 3);
        System.out.println("\nDopo rimozione (x < 3):");
        punti.forEach(System.out::println);

        // Modifica: raddoppia la coordinata x di ogni punto
        punti.replaceAll(p -> { p.setX(p.getX() * 2); return p; });
        System.out.println("\nDopo modifica (x * 2):");
        punti.forEach(System.out::println);

        // Trasformazione: .map crea una lista di stringhe con le coordinate dei punti
        ArrayList<String> stringhe = punti.stream()
                              .map(p -> "Coordinate: " + p.getX() + "," + p.getY())
                              .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("\nDopo trasformazione in lista di stringhe: " + stringhe);

        // Verifica condizione: esiste un punto con coordinate uguali?
        boolean contieneCoordinateUguali = punti.stream().anyMatch(p -> p.getX() == p.getY());
        System.out.println("\nContiene numeri pari? " + contieneCoordinateUguali);

        // Riduzione: trova il punto con la massima coordinata X, la funzione lambda è equivalente a comparator
        Punto maxX = punti.stream().max((p1, p2) -> p1.getX() - p2.getX()).get();
        System.out.println("\nMassimo delle x: " + maxX.getX());
        
        // Ordinamento per coordinata X
        punti.sort((p1, p2) -> Integer.compare(p1.getX(), p2.getX()));
        System.out.println("\nOrdinati per X:");
        punti.forEach(System.out::println); // Forma compatta. Come lambda si passa il riferimento a System.out.println
        // Oppure: prova a definire 2 funzioni scelte opportunamente tra BiFunction o BinaryOperator: 1 che ordina per coordinata x una per y e poi usa 2 chiamate punti.sort(...);

        // Filtraggio: punti con y > 5
		/*
		🔹 punti.stream()
			Crea uno Stream a partire dalla lista punti.
			Uno stream è come un flusso di oggetti che puoi elaborare in modo funzionale (senza cicli for espliciti).
			In pratica: trasforma l’ArrayList in una sequenza di elementi elaborabili uno per uno.
			Esempio:
			Se punti = [(3,7), (1,2), (5,5), (2,9)]
			→ lo stream fornisce quegli elementi uno alla volta al passo successivo.

		🔹 .filter(p -> p.getY() > 5)
			Applica un filtro: mantiene solo gli elementi che soddisfano la condizione.
			Qui la lambda expression p -> p.getY() > 5 controlla se la coordinata y del punto è maggiore di 5.
			Solo i punti alti (cioè sopra la linea y = 5) vengono mantenuti.
			→ il flusso ora contiene solo (3,7) e (2,9)
			
		🔹 .collect(Collectors.toCollection(ArrayList::new))
			Dopo aver filtrato, raccoglie i risultati e li mette in una nuova lista (ArrayList<Punto>).
			Collectors.toCollection() è un collector standard che costruisce una lista dagli elementi dello stream.
			Quindi, crea una nuova ArrayList con i punti che hanno superato il filtro
		*/
        ArrayList<Punto> alti = punti.stream()
                                .filter(p -> p.getY() > 5)
                                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("\nPunti con y > 5:");
        alti.forEach(System.out::println); 

    }
}

class Punto {
    private int x, y;

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}