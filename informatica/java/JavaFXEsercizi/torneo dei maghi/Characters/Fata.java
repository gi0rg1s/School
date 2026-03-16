package Characters;

public abstract class Fata {

    protected String nome;
    protected String type;

    protected int hpMax;
    protected int manaMax;
    protected int hp;
    protected int mana;
    protected int potenzaMagica;
    protected int difesaMagica;
    protected int speed;

//constructor
    public Fata(String nome, String type) {
        this.nome = nome;
        this.type = type;
        this.hpMax = this.hp = (int) (Math.random() * 20) + 40;
        this.manaMax = (int) (Math.random() * 20) + 20;
        this.mana = this.manaMax;
        this.hp = this.hpMax;
        this.potenzaMagica = (int) (Math.random() * 20) + 50;
        this.difesaMagica = (int) (Math.random() * 20) + 50;
        this.speed = (int) (Math.random() * 20) + speed;
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
    public int getHpMax() {
        return hpMax;
    }
    public int getManaMax() {
        return manaMax;
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