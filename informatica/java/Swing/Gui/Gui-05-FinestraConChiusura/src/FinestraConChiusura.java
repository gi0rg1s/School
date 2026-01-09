import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Il metodo di gestione dell'evento di chiusura 
 * e' scritto direttamente nel momento in cui
 * si registra l'ascoltatore
 * @author ITIS
 */
class FinestraConChiusura {
	
	private Frame f;
	
	public FinestraConChiusura() {	
		f = new Frame("Finestra di esempio");
		f.setSize(400,300);		
		f.setLocation(200,200);	
		// Registriamo un ascoltatore per gli eventi
		// L'ascoltatore viene direttamente creato
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}); 
		f.setVisible(true);	
	}

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		FinestraConChiusura esempio = new FinestraConChiusura();
	}
}