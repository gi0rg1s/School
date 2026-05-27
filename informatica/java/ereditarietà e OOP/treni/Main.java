public class Main {
    public static void main(String[] args) {
        
        Treno treno = new Treno();
        try{
            Merci vagoneMerci = new Merci(1, 2000, "Fiat", 2010, 50, 30000);
            vagoneMerci.setPesoCarico(15000);

            Passeggeri vagonePasseggeri = new Passeggeri(2, 1500, "Alstom", 2015, "Prima Classe", 100);
            vagonePasseggeri.setPostiOccupati(80);

            treno.aggiungiVagone(vagoneMerci);
            treno.aggiungiVagone(vagonePasseggeri);
            treno.aggiungiVagone(vagoneMerci);

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        System.out.println(treno.toString());

        System.out.println("Peso totale del treno: " + treno.getPesoTotaleVagoni() + " kg");
        
        
    }
}
