package Characters;

import java.util.ArrayList;

public class TrixChars {
    private ArrayList<Trix> trixChars;

    //constructor
    public TrixChars() {
        trixChars = new ArrayList<>();
        trixChars.add(new Trix("Icy", "Fata del ghiaccio", 80, 80, 70, 60, 50, "Darkarix"));
        trixChars.add(new Trix("Darcy", "Fata della magia oscura", 70, 70, 60, 50, 40, "Darkarix Sirenix"));
        trixChars.add(new Trix("Stormy", "Fata della tempesta", 60, 60, 50, 40, 30, "Darkarix Bloomix"));
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
