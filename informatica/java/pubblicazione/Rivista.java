import java.time.LocalDate;

public class Rivista extends Pubbliczione {
    private boolean isPatinata;

//constructor
    public Rivista(String titolo, LocalDate dataPubblicazione, int numeroDiPagine, boolean isPatinata, boolean isBorrowed, Utente utente){ 
        super(titolo, dataPubblicazione, numeroDiPagine, isBorrowed, utente);
        this.isPatinata = isPatinata;
    }

//getters
    public boolean isPatinata() {
        return isPatinata;
    }

//setters
    public void setPatinata(boolean isPatinata) {
        this.isPatinata = isPatinata;
    }
    @Override
    public void setReturnDate() {
        this.returnDate = LocalDate.now().plusDays(30);
    }
//methods
    public String toString() {
        return (super.toString() + ", isPatinata=" + isPatinata + "]");
    }
}
