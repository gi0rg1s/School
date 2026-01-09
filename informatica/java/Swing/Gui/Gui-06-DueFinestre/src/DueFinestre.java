// Classi per la gestione delle componenti grafiche
import java.awt.*;
/**
 * Esempio di finestra con ascoltatore esterno
 * Tutte le impostazioni grafiche vengono effettuate dal costruttore
 * @author ITIS
 */
public class DueFinestre {
	
	/**
	 * Finestra principale dell'applicazione
	 */
	private Frame f1;
	private Frame f2;

	public DueFinestre() {		
		f1 = new Frame("Finestra Principale");
		f1.setSize(400,300);		//dimensionamento 
		f1.setLocation(200,200);	//posizionamento
		// Registriamo un ascoltatore per gli eventi
		f1.addWindowListener(new GestoreFinestraPrincipale()); 
		f1.setVisible(true);		//visualizzazione
		f1.setBackground(Color.cyan);
		
		f2 = new Frame("Finestra secondaria");
		f2.setSize(200,500);		//dimensionamento 
		f2.setLocation(100,400);	//posizionamento
		// Registriamo un ascoltatore per gli eventi
		f2.addWindowListener(new GestoreFinestraSecondaria()); 
		f2.setVisible(true);		//visualizzazione
		f2.setBackground(Color.green);		
	}

	public static void main(String args[]) {
		// Istanziazione di un oggetto della classe
		@SuppressWarnings("unused")
		DueFinestre esempio = new DueFinestre();
	}
}