package Characters;
import java.util.ArrayList;

public class Winx extends Fata {

    public Winx(String nome, String type, ArrayList<Incantesimo> incantesimi) {
        super(nome, type, incantesimi);
    }

    @Override
    public String toString() {
        return "Winx{" +
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
