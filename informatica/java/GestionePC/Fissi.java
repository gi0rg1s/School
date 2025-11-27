public class Fissi extends PersonalComputer {
    protected String tipoDiCase;
//constructor
    public Fissi(String tipoDiProcessore, int dimensioneRAM, int dimensioneMedoriaDiMassa, String marca, String modello, String os, String tipoDiCase) {
        super(tipoDiProcessore, dimensioneRAM, dimensioneMedoriaDiMassa, marca, modello, os);
        this.tipoDiCase = tipoDiCase;
    }
//getter
    public String getTipoDiCase() {
        return tipoDiCase;
    }

//setter
    public void setTipoDiCase(String tipoDiCase) {
        this.tipoDiCase = tipoDiCase;
    }
//methods
/**
 * @return (String) 
 */
    public String toString() {
        return "Fissi{" +
                "tipoDiProcessore='" + tipoDiProcessore + '\'' +
                ", dimensioneRAM=" + dimensioneRAM +
                ", dimensioneMedoriaDiMassa=" + dimensioneMedoriaDiMassa +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", os='" + os + '\'' +
                ", tipoDiCase='" + tipoDiCase + '\'' +
                '}';
    }

    /**
     * 
     * @param f
     * @return (true/false)
     */
    public boolean equals(Fissi f){
        if(this.toString().equals(f.toString())){
            return true;
        }
        return false;
    }
}