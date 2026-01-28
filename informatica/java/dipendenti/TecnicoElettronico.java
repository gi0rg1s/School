import java.time.LocalDate;

public class TecnicoElettronico extends Tecnici{
    //constructor
    public TecnicoElettronico(String nome, String cognome, int annoAssunzione, int interno){
        super(nome, cognome, annoAssunzione, interno);
    }
    //methods
    @Override
    public double costoPerProgetto(int ore){
        int anniDiServizio = LocalDate.now().getYear() - this.getAnnoAssunzione();
        return ore * (60.0 + (anniDiServizio * this.getInterno()));
    }
    
}
