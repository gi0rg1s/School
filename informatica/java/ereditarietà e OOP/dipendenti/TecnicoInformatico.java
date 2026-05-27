import java.time.LocalDate;

public class TecnicoInformatico extends Tecnici{
    //constructor
    public TecnicoInformatico(String nome, String cognome, int annoAssunzione, int interno){
        super(nome, cognome, annoAssunzione, interno);
    }
    //methods
    @Override
    public double costoPerProgetto(int ore){
        int anniDiServizio = LocalDate.now().getYear() - this.getAnnoAssunzione();
        return ore * (50.0 + (anniDiServizio * this.getInterno()));
    }
}
