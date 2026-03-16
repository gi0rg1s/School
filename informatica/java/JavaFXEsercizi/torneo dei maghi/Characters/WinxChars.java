package Characters;
import java.util.ArrayList;

public class WinxChars {
    private ArrayList<Winx> winxChars;

    //constructor
    public WinxChars() {
        winxChars = new ArrayList<>();
        winxChars.add(new Winx("Bloom", "Fata del fuoco",  "Magic Winx"));
        winxChars.add(new Winx("Stella", "Fata della luce",  "Charmix"));
        winxChars.add(new Winx("Flora", "Fata della Natura",  "Enchantix"));
        winxChars.add(new Winx("Musa", "Fata della Musica", "Believix"));
        winxChars.add(new Winx("Tecna", "Fata della Tecnologia",  "Sirenix"));
        winxChars.add(new Winx("Aisha", "Fata dell'Acqua", "Bloomix"));
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
