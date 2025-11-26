public class Server extends Fissi {
    private int numeroDiProcessori;
    private boolean dischiRAID;
//constructor
    public Server(String tipoDiProcessore, int dimensioneRAM, int dimensioneMedoriaDiMassa, String marca, String modello, String os, String tipoDiCase, int numeroDiProcessori, boolean dischiRAID) {
        super(tipoDiProcessore, dimensioneRAM, dimensioneMedoriaDiMassa, marca, modello, os, tipoDiCase);
        this.numeroDiProcessori = numeroDiProcessori;
        this.dischiRAID = dischiRAID;
    }
//getters
    public int getNumeroDiProcessori() {
        return numeroDiProcessori;
    }
    public boolean isDischiRAID() {
        return dischiRAID;
    }
//setters
    public void setNumeroDiProcessori(int numeroDiProcessori) {
        this.numeroDiProcessori = numeroDiProcessori;
    }
    public void setDischiRAID(boolean dischiRAID) {
        this.dischiRAID = dischiRAID;
    }
//methods
/**
 * @return (String) 
 */
    public String toString() {
        return "Server{" +
                "tipoDiProcessore='" + tipoDiProcessore + '\'' +
                ", dimensioneRAM=" + dimensioneRAM +
                ", dimensioneMedoriaDiMassa=" + dimensioneMedoriaDiMassa +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", os='" + os + '\'' +
                ", tipoDiCase='" + tipoDiCase + '\'' +
                ", numeroDiProcessori=" + numeroDiProcessori +
                ", dischiRAID=" + dischiRAID +
                '}';
    }
/**
 * 
 * @param s
 * @return (true/false)
 */
    public boolean equals(Server s){
        if(this.toString().equals(s.toString())){
            return true;
        }
        return false;
    }
}