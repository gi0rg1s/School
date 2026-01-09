// Classi per la gestione delle componenti grafiche
import java.awt.*;
/**
 * Esempio di finestra con ascoltatore esterno
 * Tutte le impostazioni grafiche vengono effettuate dal costruttore
 * @author ITIS
 */
public class FinestraSemplice {
	
	/**
	 * Finestra principale dell'applicazione
	 */
	private Frame f;

	public FinestraSemplice() {		
		f = new Frame("Finestra di esempio");
		f.setSize(400,300);		//dimensionamento 
		f.setLocation(200,200);	//posizionamento
		// Registriamo un ascoltatore per gli eventi
		f.addWindowListener(new GestoreFinestra()); 
		f.setVisible(true);		//visualizzazione
		f.setBackground(Color.WHITE);
	}

	public static void main(String args[]) {
		// Istanziazione di un oggetto della classe
		@SuppressWarnings("unused")
		FinestraSemplice esempio = new FinestraSemplice();
	}
}