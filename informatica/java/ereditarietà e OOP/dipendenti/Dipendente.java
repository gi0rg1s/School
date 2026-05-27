abstract class Dipendente {

    public static int n = 0;
    protected String codice;
    protected String nome;
    protected String cognome;
    protected int annoAssunzione;

    // Constructor
    public Dipendente(String nome, String cognome, int annoAssunzione) {
        n++;
        this.codice = "DIP" + n;
        this.nome = nome;
        this.cognome = cognome;
        this.annoAssunzione = annoAssunzione;
    }

//getters
    public String getCodice() {
        return codice;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public int getAnnoAssunzione() {
        return annoAssunzione;
    }

//setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setAnnoAssunzione(int annoAssunzione) {
        this.annoAssunzione = annoAssunzione;
    }

    public abstract double costoPerProgetto(int ore);
    
} 