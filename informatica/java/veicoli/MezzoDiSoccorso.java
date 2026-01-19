public class MezzoDiSoccorso implements Trasportatore, Motorizzato, Navigabile, Volante{
    private String mezzo;
    private double capacita;

    //constructor
    public MezzoDiSoccorso(String mezzo, double capacita) {
        this.mezzo = mezzo;
        this.capacita = capacita;
    }
    //getter
    public String getMezzo() {
        return mezzo;
    }
    public double getCapacita() {
        return capacita;
    }
    //setter
    public void setMezzo(String mezzo) {
        this.mezzo = mezzo;
    }
    public void setCapacita(double capacita) {
        this.capacita = capacita;
    }
    //methods

    @Override
    public void trasporta() {
        System.out.println("Il mezzo di soccorso " + mezzo + " sta trasportando "+ capacita + " kg di materiale medico.");
    }
    @Override
    public void accendiMotore() {
        System.out.println("Il motore del mezzo di soccorso " + mezzo + " è acceso.");
    }
    @Override
    public void naviga() {
        System.out.println("Il mezzo di soccorso " + mezzo + " sta navigando.");
    }
    @Override
    public void vola() {
        System.out.println("Il mezzo di soccorso " + mezzo + " sta volando.");
    }
}
