abstract class Tecnici extends Dipendente{
    protected int interno;

//constructor
    public Tecnici(String nome, String cognome, int annoAssunzione, int interno){
        super(nome, cognome, annoAssunzione);
        this.interno = interno;
    }

//getter
    public int getInterno(){
        return this.interno;
    }

//setter
    public void setInterno(int interno){
        this.interno = interno;
    }

//methods
    public abstract double costoPerProgetto(int ore);
}
