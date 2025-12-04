import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Pubbliczione> biblioteca;

//constructor
    public Biblioteca(boolean isBorrowed) {
        this.biblioteca = new ArrayList<Pubbliczione>();
    }

//getters
    public ArrayList<Pubbliczione> getBiblioteca() {
        return biblioteca;
    }
//setters
    public void setBiblioteca(ArrayList<Pubbliczione> biblioteca) {
        this.biblioteca = biblioteca;
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

    public Pubbliczione getElementByID(int id){
        for (Pubbliczione p : biblioteca) {
            if(id == p.getId()) return p;
        }
        return null;
    }
/**
 * @param (int)
 * borrow a pubbliczione
 */
    public String chiediPubblicazioneInPrestito(int id, Utente utente){
        boolean flag = false;
        try {
            if(id == 0) throw new IllegalArgumentException("Pubblicazione non trovata");
        } catch (Exception e) {
            return (e.getMessage());
        }
        for(Pubbliczione p : biblioteca){
            if(p.getId() == id){
                flag = true;
                if(p.getReturnDate() != null){
                    p.setReturnDate();
                    p.setBorrowed(true);
                    p.utente = utente;
                }
                else{
                    return ("Pubblicazione sarà disponibile a partire dal: " + p.getReturnDate());
                }
            }
        }
        if(!flag) return "Pubblicazione non trovata";
        Pubbliczione found = getElementByID(id);
        if (found == null) return "Pubblicazione non trovata";
        return ("Hai preso in prestito la pubblicazione! Dovrai restituirla entro: " + found.getReturnDate());
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
    }

    public ArrayList<Pubbliczione> cercaPerUtente(Utente utente){
        ArrayList<Pubbliczione> risultati = new ArrayList<>();
        for(Pubbliczione p : biblioteca){
            try{
                if(p.getUtente().equals(utente)) risultati.add(p);
            }catch(Exception e){
            }
        }
        return risultati;
    }

}
