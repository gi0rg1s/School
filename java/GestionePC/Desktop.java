public class Desktop extends Fissi{
    private String tipoSchedaViddeo;
    protected String tipoSchedaAudio;

//constructor
    public Desktop(String tipoDiProcessore, int dimensioneRAM, int dimensioneMedoriaDiMassa, String marca, String modello, String os, String tipoDiCase, String tipoSchedaViddeo, String tipoSchedaAudio) {
        super(tipoDiProcessore, dimensioneRAM, dimensioneMedoriaDiMassa, marca, modello, os, tipoDiCase);
        this.tipoSchedaViddeo = tipoSchedaViddeo;
        this.tipoSchedaAudio = tipoSchedaAudio;
    }
//getters
    public String getTipoSchedaViddeo() {
        return tipoSchedaViddeo;
    }
    public String getTipoSchedaAudio() {
        return tipoSchedaAudio;
    }
//setters
    public void setTipoSchedaViddeo(String tipoSchedaViddeo) {
        this.tipoSchedaViddeo = tipoSchedaViddeo;
    }
    public void setTipoSchedaAudio(String tipoSchedaAudio) {
        this.tipoSchedaAudio = tipoSchedaAudio;
    }
//methods
/**
 * @return (String) 
 */
    public String toString() {
        return "Desktop{" +
                "tipoDiProcessore='" + tipoDiProcessore + '\'' +
                ", dimensioneRAM=" + dimensioneRAM +
                ", dimensioneMedoriaDiMassa=" + dimensioneMedoriaDiMassa +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", os='" + os + '\'' +
                ", tipoDiCase='" + tipoDiCase + '\'' +
                ", tipoSchedaViddeo='" + tipoSchedaViddeo + '\'' +
                ", tipoSchedaAudio='" + tipoSchedaAudio + '\'' +
                '}';
    }
/**
 * 
 * @param d
 * @return (true/false)
 */
    public boolean equals(Desktop d){
        if(this.toString().equals(d.toString())){
            return true;
        }
        return false;
    }
}