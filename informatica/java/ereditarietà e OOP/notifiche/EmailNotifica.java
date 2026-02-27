public class EmailNotifica implements Notifica {
	@Override
	public boolean invia(String messaggio) {
        try {
            if(messaggio.isEmpty()) throw new Exception("Errore: messaggio vuoto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
            System.out.println("Invio email con messaggio: " + messaggio);
            return true;
	}
}