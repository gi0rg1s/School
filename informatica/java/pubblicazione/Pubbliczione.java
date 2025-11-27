import java.time.LocalDate;

public class Pubbliczione {
    protected String titolo;
    protected int id;
    protected LocalDate dataPubblicazione;
    protected LocalDate returnDate;
    protected boolean isBorrowed;
    protected int numeroDiPagine;
    public static int num = 0;

    //constructor
        public Pubbliczione(String titolo, LocalDate dataPubblicazione, int numeroDiPagine, boolean isBorrowed) {
            this.titolo = titolo;
            num++;
            this.id = num;
            this.dataPubblicazione = dataPubblicazione;
            this.numeroDiPagine = numeroDiPagine;
            this.isBorrowed = isBorrowed;
        }

//getters
    public String getTitolo() {
        return titolo;
    }
    public int getId() {
        return id;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public int getNumeroDiPagine() {
        return numeroDiPagine;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public boolean isBorrowed() {
        return isBorrowed;
    }
//setters
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }
    public void setNumeroDiPagine(int numeroDiPagine) {
        this.numeroDiPagine = numeroDiPagine;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }
    
//methods
    public String toString() {
        return "Pubbliczione [titolo=" + titolo + ", id=" + id + ", dataPubblicazione=" + dataPubblicazione
                + ", numeroDiPagine=" + numeroDiPagine + ", isBorrowed=" + isBorrowed + ", returnDate=" + returnDate + "]";
    }
}