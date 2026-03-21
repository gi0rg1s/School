package Characters;

import java.util.ArrayList;

public class TrixChars {
    private ArrayList<Trix> trixChars;

    //constructor
    public TrixChars() {
        trixChars = new ArrayList<>();

        trixChars.add(new Trix("Icy", "Fata del ghiaccio", new ArrayList<Incantesimo>(){{add(new Incantesimo("Ghiaccio Tagliente", "Attacco", 15, 10)); add(new Incantesimo("Tempesta di Neve", "Cura", 20, 15));}}));
        trixChars.add(new Trix("Darcy", "Fata della magia oscura", new ArrayList<Incantesimo>(){{add(new Incantesimo("Magia Oscura", "Cura", 20, 15)); add(new Incantesimo("Veleno Mortale", "Attacco", 25, 20));}}));
        trixChars.add(new Trix("Stormy", "Fata della tempesta", new ArrayList<Incantesimo>(){{add(new Incantesimo("Tempesta di Neve", "Attacco", 20, 15)); add(new Incantesimo("Storm", "Cura", 25, 20));}}));
    }

    //getters
    public ArrayList<Trix> getTrixChars() {
        return trixChars;
    }

    //setters
    public void addTrix(Trix trix) {
        trixChars.add(trix);
    }

    //methods
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Trix trix : trixChars) sb.append(trix.toString()).append("\n");
        return sb.toString();
    }
}
