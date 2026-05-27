import java.time.LocalDate;

public class Impiegato extends Dipendente{
    protected String livello;

//constructor
    public Impiegato (String nominativo, String sesso, LocalDate dataDiNascita, double stipendioBaseMensile, String livello){
        super(nominativo, sesso, dataDiNascita, stipendioBaseMensile);
        this.livello = livello;
    }
//getter
    public String getLivello() {
        return livello;
    }
//setter
    public void setLivello(String livello){
        this.livello = livello;
    }
//method
    @Override
    public String toString() {
        return "Impiegato [ livello =" + getLivello() + ", nominativo =" + getNominativo() + ", sesso ="
                + getSesso() + ", data di nascita = " + getDataDiNascita() + ", stipendio base mensile = "
                + getStipendioBaseMensile() + "]";
    }
}