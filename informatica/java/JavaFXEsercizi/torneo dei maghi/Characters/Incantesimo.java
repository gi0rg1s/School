package Characters;

public class Incantesimo {
    private String nome;
    private String tipo;
    private int danno;
    private int manaCost;

    //constructor
    public Incantesimo(String nome, String tipo, int danno, int manaCost) {
        this.nome = nome;
        this.tipo = tipo;
        this.danno = danno;
        this.manaCost = manaCost;
    }

    //getters
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public int getDanno() {
        return danno;
    }
    public int getManaCost() {
        return manaCost;
    }

    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setDanno(int danno) {
        this.danno = danno;
    }
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

}
