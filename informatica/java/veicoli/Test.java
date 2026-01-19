import java.util.ArrayList;

public class Test{
    public static void main(String[] args) {
        ArrayList<Motorizzato> veicoliMotorizzati = new ArrayList<>();
        veicoliMotorizzati.add(new MezzoDiSoccorso("ambulanza", 100.0));

        for (Motorizzato veicolo : veicoliMotorizzati) {
            veicolo.accendiMotore();
            ((Navigabile)veicolo).naviga();
            ((Trasportatore)veicolo).trasporta();
            ((Volante)veicolo).vola();
        }


    }
}