import java.util.ArrayList;

public class Utente{
    private String nome;
    private String cognome;
    private String recapito;
    private ArrayList<Pubbliczione> pubbliczioniInPrestito = new ArrayList<Pubbliczione>();

//constructor
    public Utente( String nome, String cognome, String recapito) {
        this.nome = nome;
        this.cognome = cognome;
        this.recapito = recapito;
    }
//getters
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getRecapito() {
        return recapito;
    }
    public ArrayList<Pubbliczione> getPubbliczioniInPrestito() {
        return pubbliczioniInPrestito;
    }
//setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setRecapito(String recapito) {
        this.recapito = recapito;
    }
    public void setPubbliczioniInPrestito(ArrayList<Pubbliczione> pubbliczioniInPrestito) {
        this.pubbliczioniInPrestito = pubbliczioniInPrestito;
    }
//methods
    public String toString() {
        return "Utente [nome=" + nome + ", cognome=" + cognome + ", recapito=" + recapito + ", pubbliczioniInPrestito=" + pubbliczioniInPrestito + "]";
    }
}