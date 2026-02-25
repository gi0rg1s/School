import java.time.LocalDate;

public class Funzionari extends Dipendente{
    //constructor
    public Funzionari(String nome, String cognome, int annoAssunzione){
        super(nome, cognome, annoAssunzione);
    }

    @Override
    public double costoPerProgetto(int ore){
        int anniDiServizio = LocalDate.now().getYear() - this.getAnnoAssunzione();
        if(anniDiServizio < 10) return ore * 75.0;
        return ore * 85.0;
    }
}
