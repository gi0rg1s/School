public class Palmare extends Portatili {
    private boolean bluetooth;
    private String tipoEspansioneMemoriaDiMassa;

//constructor
    public Palmare(String tipoDiProcessore, int dimensioneRAM, int dimensioneMedoriaDiMassa, String marca, String modello, String os,
                   double peso, double altezza, double larghezza, double profondita, double dimensioneSchermo, boolean reteWireless,
                   boolean bluetooth, String tipoEspansioneMemoriaDiMassa){
        super(tipoDiProcessore, dimensioneRAM, dimensioneMedoriaDiMassa, marca, modello, os,
              peso, altezza, larghezza, profondita, dimensioneSchermo, reteWireless);
        this.bluetooth = bluetooth;
        this.tipoEspansioneMemoriaDiMassa = tipoEspansioneMemoriaDiMassa;
    }

    // getters
    public boolean isBluetooth(){
        return bluetooth;
    }

    public String getTipoEspansioneMemoriaDiMassa(){
        return tipoEspansioneMemoriaDiMassa;
    }

    // setters
    public void setBluetooth(boolean bluetooth){
        this.bluetooth = bluetooth;
    }

    public void setTipoEspansioneMemoriaDiMassa(String tipoEspansioneMemoriaDiMassa){
        this.tipoEspansioneMemoriaDiMassa = tipoEspansioneMemoriaDiMassa;
    }
//methods
    /**
 * @return (String) 
 */
    public String toString(){
        return "Palmare{" +
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
                ", bluetooth=" + bluetooth +
                ", tipoEspansioneMemoriaDiMassa='" + tipoEspansioneMemoriaDiMassa + '\'' +
                '}';
    }

    /**
     * 
     * @param p
     * @return (true/false)
     */
    public boolean equals(Palmare p){
        if(this.toString().equals(p.toString())) return true;
        return false;
    }
}
