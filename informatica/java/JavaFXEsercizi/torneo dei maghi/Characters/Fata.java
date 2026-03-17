package Characters;

import java.util.ArrayList;

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
    protected Incantesimo incantesimo;
    protected ArrayList<Incantesimo> incantesimi = new ArrayList<Incantesimo>();

//constructor
    public Fata(String nome, String type, ArrayList<Incantesimo> incantesimi) {
        this.nome = nome;
        this.type = type;
        this.incantesimi = incantesimi;
        this.hpMax = this.hp = (int) (Math.random() * 20) + 40;
        this.manaMax = (int) (Math.random() * 20) + 20;
        this.mana = this.manaMax;
        this.hp = this.hpMax;
        this.potenzaMagica = (int) (Math.random() * 5) + 5;
        this.difesaMagica = (int) (Math.random() * 5) + 3;
        this.speed = (int) (Math.random() * 9) + 1;
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
    public ArrayList<Incantesimo> getIncantesimi() {
        return incantesimi;
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
    public void setIncantesimi(ArrayList<Incantesimo> incantesimi) {
        this.incantesimi = incantesimi;
    }

//methods
    
    public boolean canCast(Incantesimo i) {
        return mana >= i.getManaCost();
    }

    public void takeDamage(int danno){
        setHp(getHp() - danno + getDifesaMagica());
        if(getHp() < 0) setHp(0);
    }

    public void heal(int cura){
        setHp(getHp() + cura);
        if(getHp() > getHpMax()) setHp(getHpMax());
    }

    public void castIncantesimo(Incantesimo i, Fata target) {
        if(canCast(i)){
            setMana(getMana() - i.getManaCost());
            if(i.getTipo().equals("Attacco")){
                int danno = i.getDanno() + getPotenzaMagica();
                target.takeDamage(danno);
            }
            else if(i.getTipo().equals(("Cura"))){
                int cura = i.getDanno() + getPotenzaMagica();
                heal(cura);
            }
        }
        
    }

//to string
    public abstract String toString();
}