import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Pubbliczione> biblioteca;
    private boolean isBorrowed;

//constructor
    public Biblioteca(boolean isBorrowed) {
        this.biblioteca = new ArrayList<Pubbliczione>();
        this.isBorrowed = isBorrowed;
    }

//getters
    public ArrayList<Pubbliczione> getBiblioteca() {
        return biblioteca;
    }
    public boolean isBorrowed() {
        return isBorrowed;
    }

//setters
    public void setBiblioteca(ArrayList<Pubbliczione> biblioteca) {
        this.biblioteca = biblioteca;
    }
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

//methods
/**
 * @param (Pubbliczione)
 * add a new pubblicazione
 */
    public void aggiungiPubbliczione(Pubbliczione p) {
        try {
            if (p == null) throw new IllegalArgumentException("Pubbliczione non valida");
            for (Pubbliczione pub : biblioteca) {
                if (pub.getId() == p.getId()) {
                    throw new IllegalArgumentException("Pubbliczione con ID duplicato");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        biblioteca.add(p);
    }
    /**
     * @param (int)
     * remove a pubbliczione by id
     */
    public void rimuoviPubbliczione(int id) {
        for (Pubbliczione p : biblioteca) {
            if(p.getId() == id)
                biblioteca.remove(p);
        }
    }
/**
 * @param (int)
 * borrow a pubbliczione
 */
    public void chiediPubblicazioneInPrestito(int id){
        int flag = 0;
        try {
            if(id == 0) throw new IllegalArgumentException("Pubblicazione non trovata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for(Pubbliczione p : biblioteca){
            if(p.getId() == id){
                flag = 1;
                if(!isBorrowed()){
                    p.setBorrowed(true);
                    if (p instanceof Rivista) p.setReturnDate(LocalDate.now().plusDays(30));
                    else p.setReturnDate(LocalDate.now().plusDays(60));
                }
                else{
                    System.out.println("Pubblicazione sarà disponibile a partire dal: " + p.getReturnDate());
                }
            }
        }
        try {
            if(flag==0) throw new IllegalArgumentException("Pubblicazione non trovata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * @param (Pubbliczione)
 * return a pubbliczione
 */
    public void restituisciPubblicazione(Pubbliczione p){
        try {
            if(!p.isBorrowed()) throw new IllegalArgumentException("Pubblicazione non in prestito");
            if(!biblioteca.contains(p)) throw new IllegalArgumentException("Pubblicazione non appartiene a questa biblioteca");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        p.setBorrowed(false);
    }

}
