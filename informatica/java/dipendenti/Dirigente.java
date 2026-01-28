public class Dirigente extends Dipendente {
    //constructor
    public Dirigente(String nome, String cognome, int annoAssunzione){
        super(nome, cognome, annoAssunzione);
    }

    @Override
    public double costoPerProgetto(int ore){
        return ore * 100.0;
    }

}
