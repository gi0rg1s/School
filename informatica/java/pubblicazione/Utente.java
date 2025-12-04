public class Utente{
    private String nome;
    private String cognome;
    private String recapito;

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
//methods
    public String toString() {
        return "Utente [nome=" + nome + ", cognome=" + cognome + ", recapito=" + recapito + "]";
    }
}