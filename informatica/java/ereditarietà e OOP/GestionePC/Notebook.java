public class Notebook extends Portatili{
    private boolean webcam;
    private int risoluzioneWebcam;

//constructor
    public Notebook(String tipoDiProcessore, int dimensioneRAM, int dimensioneMedoriaDiMassa, String marca, String modello, String os,
                    double peso, double altezza, double larghezza, double profondita, double dimensioneSchermo, boolean reteWireless,
                    boolean webcam, int risoluzione){
                        super(tipoDiProcessore, dimensioneRAM, dimensioneMedoriaDiMassa, marca, modello, os,
                              peso, altezza, larghezza, profondita, dimensioneSchermo, reteWireless);
                        this.webcam = webcam;
                        this.risoluzioneWebcam = risoluzione;
    }
//getters
    public boolean isWebcam(){
        return webcam;
    }

    public int getRisoluzioneWebcam(){
        return risoluzioneWebcam;
    }

//setters
    public void setWebcam(boolean webcam){
        this.webcam = webcam;
    }

    public void setRisoluzioneWebcam(int risoluzioneWebcam){
        this.risoluzioneWebcam = risoluzioneWebcam;
    }
//methods
    /**
 * @return (String) 
 */
    public String toString(){
        return "Notebook{" +
                "tipoDiProcessore='" + getTipoDiProcessore() + '\'' +
                ", dimensioneRAM=" + getDimensioneRAM() +
                ", dimensioneMedoriaDiMassa=" + getDimensioneMedoriaDiMassa() +
                ", marca='" + getMarca() + '\'' +
                ", modello='" + getModello() + '\'' +
                ", os='" + getOs() + '\'' +
                ", peso=" + getPeso() +
                ", altezza=" + getAltezza() +
                ", larghezza=" + getLarghezza() +
                ", profondita=" + getProfondita() +
                ", dimensioneSchermo=" + getDimensioneSchermo() +
                ", reteWireless=" + isReteWireless() +
                ", webcam=" + webcam +
                ", risoluzioneWebcam=" + risoluzioneWebcam +
                '}';
    }

    /**
     * 
     * @param n
     * @return (true/false)
     */
    public boolean equals(Notebook n){
        if(this.toString().equals(n.toString())) return true;
        return false;
    }
}