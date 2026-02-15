package Characters;

import java.util.ArrayList;

public class WinxChars {
    private ArrayList<Winx> winxChars;

    //constructor
    public WinxChars() {
        winxChars = new ArrayList<>();
        winxChars.add(new Winx("Bloom", "Fata del fuoco", 100, 100, 80, 70, 60, "Magic Winx"));
        winxChars.add(new Winx("Stella", "Fata della luce", 90, 90, 70, 60, 50, "Charmix"));
        winxChars.add(new Winx("Flora", "Fata della Natura", 80, 80, 60, 50, 40, "Enchantix"));
        winxChars.add(new Winx("Musa", "Fata della Musica", 70, 70, 50, 40, 30, "Believix"));
        winxChars.add(new Winx("Tecna", "Fata della Tecnologia", 60, 60, 40, 30, 20, "Sirenix"));
        winxChars.add(new Winx("Aisha", "Fata dell'Acqua", 50, 50, 30, 20, 10, "Bloomix"));
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
