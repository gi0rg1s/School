import java.time.LocalDate;

public class ImpiegatoStraordinari extends Impiegato{
    private int oreDiStraordinariMensili;
    private double retribuzionePerOraDiStraordinari = 20.00;

    //constructor
    public ImpiegatoStraordinari(String nominativo, String sesso, LocalDate dataDiNascita, double stipendioBaseMensile,
            String livello, int oreDiStraordinariMensili) {
        super(nominativo, sesso, dataDiNascita, stipendioBaseMensile, livello);
        this.oreDiStraordinariMensili = oreDiStraordinariMensili;
    }
//getters
    public int getOreDiStraordinariMensili() {
        return oreDiStraordinariMensili;
    }
    public double getRetribuzionePerOraDiStraordinari() {
        return retribuzionePerOraDiStraordinari;
    }
//setters
    public void setOreDiStraordinariMensili(int oreDiStraordinariMensili) {
        this.oreDiStraordinariMensili = oreDiStraordinariMensili;
    }
//method
    @Override
    public String toString() {
        return "ImpiegatoStraordinari [nominativo=" + getNominativo() + ", livello=" + getLivello()
                + ", ore di straordinari mensili=" + getOreDiStraordinariMensili() + ", sesso=" + getSesso()
                + ", retribuzione per ogni ora di straordinari=" + getRetribuzionePerOraDiStraordinari() + ", dataDiNascita="
                + dataDiNascita + ", stipendioBaseMensile=" + stipendioBaseMensile + "]";
    }

    /**
     * calculate the total pay
     * @return
     */
    public double calcolaStipendioTotale(){
        return (getStipendioBaseMensile() + (getOreDiStraordinariMensili() * getRetribuzionePerOraDiStraordinari()));
    }
}
