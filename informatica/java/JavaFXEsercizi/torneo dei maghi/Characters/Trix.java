package Characters;

import java.util.ArrayList;

public class Trix extends Fata {

    public Trix(String nome, String type, ArrayList<Incantesimo> incantesimi) {
        super(nome, type, incantesimi);
    }

    @Override
    public String toString() {
        return "Trix{" +
                "nome='" + nome + '\'' +
                ", type='" + type + '\'' +
                ", hp=" + hp +
                ", mana=" + mana +
                ", potenzaMagica=" + potenzaMagica +
                ", difesaMagica=" + difesaMagica +
                ", speed=" + speed +
                ", incantesimi=" + incantesimi +
                '}';
    }
    
}
