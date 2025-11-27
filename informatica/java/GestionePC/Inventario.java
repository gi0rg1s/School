import java.util.ArrayList;

public class Inventario {
    private ArrayList<PersonalComputer> pcList= new ArrayList<PersonalComputer>(1);

//methods
/**
 * add a pc to the arraylist
 * @param pc
 */
    public void aggiungiPC(PersonalComputer pc){
        try {
            if(pc == null) throw new IllegalArgumentException("pc is null");
            for (PersonalComputer existingPc : pcList) {
                if(existingPc.equals(pc)) throw new IllegalArgumentException("pc already exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        pcList.add(pc);
    }

    /**
     * remove a pc from the arraylist
     * @param pc
     * @return (true/false)
     */
    public boolean rimuoviPC(PersonalComputer pc){
        try {
            if(pc == null) throw new IllegalArgumentException("pc is null");
            int count = 0;
            for (PersonalComputer existingPc : pcList) {
                if(existingPc.equals(pc)) count++;
            }
            if(count == 0) throw new IllegalArgumentException("pc does not exist");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        pcList.remove(pc);
        return true;
    }

    /**
     * returns an arraylist with all portatili with th esame features
     * @param pesoMassimo
     * @param richiedeWifi
     * @return arrayList<PersonalComputer>
     */
    public ArrayList<PersonalComputer> cercaPortatili(double pesoMassimo, boolean richiedeWifi){
        ArrayList<PersonalComputer> pcCercati = new ArrayList<PersonalComputer>(1);
        for (PersonalComputer pc : pcList) {
            if (pc instanceof Portatili) {
                Portatili p = (Portatili) pc;
                if (p.getPeso() <= pesoMassimo) {
                    if (!richiedeWifi || p.isReteWireless()) {
                        pcCercati.add(p);
                    }
                    else if(richiedeWifi == p.isReteWireless())
                        pcCercati.add(p);
                }
            }
        }
        return pcCercati;
    }

    /**
     * find the pc with the most quantity of RAM
     * @return (PersonalComputer)
     */
    public PersonalComputer trovaPCconPiuRAM(){
        PersonalComputer p = pcList.get(0);
        for (PersonalComputer pc: pcList) {
            if(p.getDimensioneRAM() < pc.getDimensioneRAM()) p = pc;
        }
        return p;
    }

    /**
     * calculate the average amount of RAM
     * @return (double)
     */
    public double calcolaMediaRAM(){
        int media = 0;
        for (PersonalComputer pc: pcList) {
            media += pc.getDimensioneRAM();
        }
        return (double) media / pcList.size();
    }

    /**
     * find the numbeer of pc with wifi
     * @return (int)
     */
    public int contaPortatiliConWiFi(){
        int count = 0;
        for (PersonalComputer pc : pcList) {
            if (pc instanceof Portatili) {
                Portatili p = (Portatili) pc;
                if (p.isReteWireless())
                    count++;
            }
        }
        return count;
    }

    /**
     * count how many servers have RAID
     * @return (int)
     */
    public int contaServerConRAID(){
        int count = 0;
        for (PersonalComputer pc : pcList) {
            if (pc instanceof Server) {
                Server s = (Server) pc;
                if (s.isDischiRAID()) 
                    count++;
            }
        }
        return count;
    }

    /**
     * find the lighter notebook
     * @return (Notebook)
     */
    public Notebook trovaNotebookPiuLeggero(){
        Notebook lightest = null;
        for (PersonalComputer pc : pcList) {
            if (pc instanceof Notebook) {
                Notebook n = (Notebook) pc;
                if (lightest == null || n.getPeso() < lightest.getPeso()) {
                    lightest = n;
                }
            }
        }
        return lightest;
    }
}
