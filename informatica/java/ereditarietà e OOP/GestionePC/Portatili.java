public class Portatili extends PersonalComputer {
    protected double peso;
    protected double altezza;
    protected double larghezza;
    protected double profondita;
    protected double dimensioneSchermo;
    protected boolean reteWireless;

//constructor
    public Portatili(String tipoDiProcessore, int dimensioneRAM, int dimensioneMedoriaDiMassa, String marca, String modello, String os,
                     double peso, double altezza, double larghezza, double profondita, double dimensioneSchermo, boolean reteWireless){
                        super(tipoDiProcessore, dimensioneRAM, dimensioneMedoriaDiMassa, marca, modello, os);
                        this.peso = peso;
                        this.altezza = altezza;
                        this.larghezza = larghezza;
                        this.profondita = profondita;
                        this.dimensioneSchermo = dimensioneSchermo;
                        this.reteWireless = reteWireless;
    }
//getters
    public double getPeso(){
        return peso;
    }
    public double getAltezza(){
        return altezza;
    }

    public double getLarghezza(){
        return larghezza;
    }

    public double getProfondita(){
        return profondita;
    }

    public double getDimensioneSchermo(){
        return dimensioneSchermo;
    }

    public boolean isReteWireless(){
        return reteWireless;
    }

// setters
    public void setPeso(double peso){
        this.peso = peso;
    }

    public void setAltezza(double altezza){
        this.altezza = altezza;
    }

    public void setLarghezza(double larghezza){
        this.larghezza = larghezza;
    }

    public void setProfondita(double profondita){
        this.profondita = profondita;
    }

    public void setDimensioneSchermo(double dimensioneSchermo){
        this.dimensioneSchermo = dimensioneSchermo;
    }

    public void setReteWireless(boolean reteWireless){
        this.reteWireless = reteWireless;
    }
//methods
/**
 * @return (String) 
 */
    public String toString() {
        return "Portatili{" +
                "tipoDiProcessore='" + getTipoDiProcessore() + '\'' +
                ", dimensioneRAM=" + getDimensioneRAM() +
                ", dimensioneMedoriaDiMassa=" + getDimensioneMedoriaDiMassa() +
                ", marca='" + getMarca() + '\'' +
                ", modello='" + getModello() + '\'' +
                ", os='" + getOs() + '\'' +
                ", peso=" + peso +
                ", altezza=" + altezza +
                ", larghezza=" + larghezza +
                ", profondita=" + profondita +
                ", dimensioneSchermo=" + dimensioneSchermo +
                ", reteWireless=" + reteWireless +
                '}';
    }
    /**
     * 
     * @param p
     * @return (true/false)
     */
    public boolean equals(Portatili p){
        if(this.toString().equals(p.toString()))
            return true;
        return false;
    }
}