import java.awt.Color;
import java.awt.Frame;

/**
 * Esempio di finestra con ascoltatore esterno
 * Tutte le impostazioni grafiche vengono effettuate dal costruttore
 * @author ITIS
 */
@SuppressWarnings("serial")
public class FinestraSemplice extends Frame {

	public FinestraSemplice() {		
		this.setName("hello");
		this.setSize(400,300);		//dimensionamento 
		this.setLocation(200,200);	//posizionamento
		// Registriamo un ascoltatore per gli eventi
		this.addWindowListener(new GestoreFinestra()); 
		this.setVisible(true);		//visualizzazione
		this.setBackground(Color.cyan);
	}

	public static void main(String args[]) {
		// Istanziazione di un oggetto della classe
		@SuppressWarnings("unused")
		FinestraSemplice esempio = new FinestraSemplice();
	}
}