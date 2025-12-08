import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Docente d1 = new Docente ("Marco Rossi", "M", LocalDate.of(2000, 3, 17), 1500.03, 26);
        Impiegato i1 = new Impiegato("Pina", "F", LocalDate.of(1898, 12, 12), 1000.00, "super mega avanzato");
        ImpiegatoStraordinari s1 = new ImpiegatoStraordinari("Giancarlo Gennarini", "M", LocalDate.of(1962, 8, 19), 300.00, "scarso", 40);

        System.out.println("*********DIPENDENTI DELLA SCUOLA***********");
        System.out.println(d1.toString());
        System.out.println(i1.toString());
        System.out.println(s1.toString());

        System.out.println("\nStipendio totale di " + s1.getNominativo() + "= " + s1.calcolaStipendioTotale() + "€");

    }
}
