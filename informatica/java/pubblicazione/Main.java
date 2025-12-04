import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static void stampaBiblioteca(Biblioteca b) {
        System.out.println("--- Biblioteca ---");
        for (Pubbliczione p : b.getBiblioteca()) {
            System.out.println(p);
        }
        System.out.println("------------------");
    }

    private static Pubbliczione trovaPerId(Biblioteca b, int id) {
        for (Pubbliczione p : b.getBiblioteca()) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Biblioteca biblioteca = new Biblioteca(false);

        // dati d'esempio
        Libro l1 = new Libro("Il Signore degli Anelli", LocalDate.of(1954, 7, 29), 1216, false, "J.R.R. Tolkien", "978-0261102385");
        Rivista r1 = new Rivista("National Geographic", LocalDate.of(2023, 5, 1), 100, false, true);
        Pubbliczione p1 = new Pubbliczione("Generica A", LocalDate.of(2025, 11, 20), 200, false);
        Pubbliczione p2 = new Pubbliczione("Generica B", LocalDate.of(2024, 6, 15), 50, false);

        biblioteca.aggiungiPubbliczione(l1);
        biblioteca.aggiungiPubbliczione(r1);
        biblioteca.aggiungiPubbliczione(p1);
        biblioteca.aggiungiPubbliczione(p2);

        System.out.println("Esempio interattivo della biblioteca. Usa il menu per provare funzionalità.");

        Utente utentePredefinito = new Utente("Mario", "Rossi", "mario.rossi@example.com");

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1) Mostra biblioteca");
            System.out.println("2) Presta pubblicazione (usa utente di esempio)");
            System.out.println("3) Restituisci pubblicazione");
            System.out.println("4) Elenca solo disponibili");
            System.out.println("5) Esci");
            System.out.print("Scelta: ");
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1" -> stampaBiblioteca(biblioteca);
                case "2" -> {
                    System.out.print("Inserisci id della pubblicazione da prendere in prestito: ");
                    String idStr = in.nextLine().trim();
                    int id;
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Id non valido: " + idStr);
                        break;
                    }
                    Pubbliczione target = trovaPerId(biblioteca, id);
                    if (target == null) {
                        System.out.println("Id non trovato: " + id);
                    } else {
                        biblioteca.chiediPubblicazioneInPrestito(target.getId(), utentePredefinito);
                        System.out.println("Richiesta di prestito eseguita su: " + id);
                    }
                }
                case "3" -> {
                    System.out.print("Inserisci id della pubblicazione da restituire: ");
                    String idStr = in.nextLine().trim();
                    int id;
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Id non valido: " + idStr);
                        break;
                    }
                    Pubbliczione target = trovaPerId(biblioteca, id);
                    if (target == null) {
                        System.out.println("Id non trovato: " + id);
                    } else {
                        biblioteca.restituisciPubblicazione(target);
                        System.out.println("Restituzione eseguita su: " + id);
                    }
                }
                case "4" -> {
                    System.out.println("Pubblicazioni disponibili:");
                    for (Pubbliczione p : biblioteca.getBiblioteca()) {
                        if (!p.isBorrowed()) System.out.println(p);
                    }
                }
                case "5" -> {
                    running = false;
                    System.out.println("Uscita.");
                }
                default -> System.out.println("Scelta non valida.");
            }
        }

        in.close();
    }
}
