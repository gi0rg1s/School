public class ProdottoSingolo implements Pagabile {
    private String nome;
    private double prezzo;

    //constructor
    public ProdottoSingolo(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }
    //getters
    public String getNome() {
        return nome;
    }
    public double getPrezzo() {
        return prezzo;
    }
    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    //methods
    public double calcolaTotale() {
        return getPrezzo();
    }
}
