package Characters;

import java.util.ArrayList;

public class TrixChars {
    private ArrayList<Trix> trixChars;

    //constructor
    public TrixChars() {
        trixChars = new ArrayList<>();
        trixChars.add(new Trix("Icy", "Fata del ghiaccio", "Darkarix"));
        trixChars.add(new Trix("Darcy", "Fata della magia oscura",  "Darkarix Sirenix"));
        trixChars.add(new Trix("Stormy", "Fata della tempesta",  "Darkarix Bloomix"));
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
