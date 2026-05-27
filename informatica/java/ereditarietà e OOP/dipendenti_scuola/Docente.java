import java.time.LocalDate;

public class Docente extends Dipendente{
    private int oreDiDocenza;

    //constructor
    public Docente(String nominativo, String sesso, LocalDate dataDiNascita, double stipendioBaseMensile, int oreDiDocenza){
        super(nominativo, sesso, dataDiNascita, stipendioBaseMensile);
        this.oreDiDocenza = oreDiDocenza;
    }
//getter
    public int getOreDiDocenza() {
        return oreDiDocenza;
    }
//setter
    public void setOreDiDocenza(int oreDiDocenza) {
        this.oreDiDocenza = oreDiDocenza;
    }
//method
    @Override
    public String toString() {
        return "Docente [ore di docenza =" + getOreDiDocenza() + ", nominativo =" + getNominativo() + ", sesso =" + getSesso() + ", data di nascita ="
                + getDataDiNascita() + ", stipendio base mensile =" + getStipendioBaseMensile() + "]";
    }
}