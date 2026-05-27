public class PersonalComputer {
    protected String tipoDiProcessore;
    protected int dimensioneRAM;
    protected int dimensioneMedoriaDiMassa;
    protected String marca;
    protected String modello;
    protected String os;
//constructor
    public PersonalComputer(String tipoDiProcessore, int dimensioneRAM, int dimensioneMedoriaDiMassa, String marca, String modello, String os) {
        this.tipoDiProcessore = tipoDiProcessore;
        this.dimensioneRAM = dimensioneRAM;
        this.dimensioneMedoriaDiMassa = dimensioneMedoriaDiMassa;
        this.marca = marca;
        this.modello = modello;
        this.os = os;
    }

//getters
    public String getTipoDiProcessore() {
        return tipoDiProcessore;
    }

    public int getDimensioneRAM() {
        return dimensioneRAM;
    }

    public int getDimensioneMedoriaDiMassa() {
        return dimensioneMedoriaDiMassa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getOs() {
        return os;
    }
//setters
    public void setTipoDiProcessore(String tipoDiProcessore) {
        this.tipoDiProcessore = tipoDiProcessore;
    }

    public void setDimensioneRAM(int dimensioneRAM) {
        this.dimensioneRAM = dimensioneRAM;
    }

    public void setDimensioneMedoriaDiMassa(int dimensioneMedoriaDiMassa) {
        this.dimensioneMedoriaDiMassa = dimensioneMedoriaDiMassa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public void setOs(String os) {
        this.os = os;
    }

//methods
/**
 * @return (String) 
 */
    public String toString() {
        return "PersonalComputer{" +
                "tipoDiProcessore='" + tipoDiProcessore + '\'' +
                ", dimensioneRAM=" + dimensioneRAM +
                ", dimensioneMedoriaDiMassa=" + dimensioneMedoriaDiMassa +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", os='" + os + '\'' +
                '}';
    }

    /**
     * 
     * @param obj
     * @return (true/false)
     */
    public boolean equals(PersonalComputer obj){
        if(this.toString().equals(obj.toString())){
            return true;
        }
        return false;
    }
}