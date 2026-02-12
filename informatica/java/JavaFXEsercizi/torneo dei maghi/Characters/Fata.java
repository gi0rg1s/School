package Characters;

public abstract class Fata {

    protected String nome;
    protected String type;

    protected int hp;
    protected int mana;
    protected int potenzaMagica;
    protected int difesaMagica;
    protected int speed;

//constructor
    public Fata(String nome, String type, int hp, int mana, int potenzaMagica, int difesaMagica, int speed) {
        this.nome = nome;
        this.type = type;
        this.hp = hp;
        this.mana = mana;
        this.potenzaMagica = potenzaMagica;
        this.difesaMagica = difesaMagica;
        this.speed = speed;
    }

//getters
    public String getNome() {
        return nome;
    }
    public String getType() {
        return type;
    }
    public int getHp() {
        return hp;
    }
    public int getMana() {
        return mana;
    }
    public int getPotenzaMagica() {
        return potenzaMagica;
    }
    public int getDifesaMagica() {
        return difesaMagica;
    }
    public int getSpeed() {
        return speed;
    }

//setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public void setPotenzaMagica(int potenzaMagica) {
        this.potenzaMagica = potenzaMagica;
    }
    public void setDifesaMagica(int difesaMagica) {
        this.difesaMagica = difesaMagica;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

//to string
    public abstract String toString();
}