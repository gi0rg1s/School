import java.time.LocalDate;

public class Libro extends Pubbliczione {
    private String autore;
    private String ISBN;

//constructor
    public Libro(String titolo, LocalDate dataPubblicazione, int numeroDiPagine, boolean isBorrowed, String autore, String ISBN){
        super(titolo, dataPubblicazione, numeroDiPagine, isBorrowed);
        this.autore = autore;
        this.ISBN = ISBN;
    }

//getters
    public String getAutore() {
        return autore;
    }
    public String getISBN() {
        return ISBN;
    }

//setters
    public void setAutore(String autore) {
        this.autore = autore;
    }
    public void setISBN(String iSBN) {
        this.ISBN = iSBN;
    }

//methods
    public String toString() {
        return (super.toString() + ", autore=" + autore + ", ISBN=" + ISBN + "]");
    }
}
