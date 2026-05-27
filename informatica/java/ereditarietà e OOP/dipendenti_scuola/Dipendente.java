import java.time.LocalDate;

public abstract class Dipendente{
    protected String nominativo;
    protected String sesso;
    protected LocalDate dataDiNascita;
    protected double stipendioBaseMensile;

//constructor
    public Dipendente(String nominativo, String sesso, LocalDate dataDiNascita, double stipendioBaseMensile) {
        this.nominativo = nominativo;
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.stipendioBaseMensile = stipendioBaseMensile;

    }
//getters
    public String getNominativo() {
        return nominativo;
    }
    public String getSesso() {
        return sesso;
    }
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
    public double getStipendioBaseMensile() {
        return stipendioBaseMensile;
    }
//setters
    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }
    public void setSesso(String sesso) {
        this.sesso = sesso;
    }
    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    public void setStipendioBaseMensile(double stipendioBaseMensile) {
        this.stipendioBaseMensile = stipendioBaseMensile;
    }
//method
    public abstract String toString();
}
