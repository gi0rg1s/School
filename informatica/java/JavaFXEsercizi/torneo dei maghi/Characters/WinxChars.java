package Characters;
import java.util.ArrayList;

public class WinxChars {
    private ArrayList<Winx> winxChars;

    //constructor
    public WinxChars() {

        winxChars = new ArrayList<>();
        winxChars.add(new Winx("Bloom", "Fata del fuoco", new ArrayList<Incantesimo>(){{add(new Incantesimo("Palla di Fuoco", "Attacco", 15, 10)); add(new Incantesimo("Fiamma Rigenerante", "Cura", 20, 15));}}));
        winxChars.add(new Winx("Stella", "Fata della luce", new ArrayList<Incantesimo>(){{add(new Incantesimo("Luce Brillante", "Attacco", 15, 10)); add(new Incantesimo("Raggio Solare", "Cura", 20, 15));}}));
        winxChars.add(new Winx("Flora", "Fata della Natura", new ArrayList<Incantesimo>(){{add(new Incantesimo("Vita Rigenerante", "Attacco", 15, 10)); add(new Incantesimo("Fioritura", "Cura", 20, 15));}}));
        winxChars.add(new Winx("Musa", "Fata della Musica", new ArrayList<Incantesimo>(){{add(new Incantesimo("Melodia Guaritrice", "Attacco", 15, 10)); add(new Incantesimo("Armonia", "Cura", 20, 15));}}));
        winxChars.add(new Winx("Tecna", "Fata della Tecnologia", new ArrayList<Incantesimo>(){{add(new Incantesimo("Elettro Shock", "Attacco", 15, 10)); add(new Incantesimo("Circuiti di Protezione", "Cura", 20, 15));}}));
        winxChars.add(new Winx("Aisha", "Fata dell'Acqua", new ArrayList<Incantesimo>(){{add(new Incantesimo("Onda d'Acqua", "Attacco", 15, 10)); add(new Incantesimo("Mare Tranquillo", "Cura", 20, 15));}}));
    }

    //getters
    public ArrayList<Winx> getWinxChars() {
        return winxChars;
    }

    //setters
    public void addWinx(Winx winx) {
        winxChars.add(winx);
    }

    //methods
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Winx winx : winxChars) sb.append(winx.toString()).append("\n");
        return sb.toString();
    }
}
