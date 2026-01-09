// Classi per i componenti grafici
import java.awt.Frame;
// Classi per la gestione degli eventi
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

/**
 * La classe implementa l'interfaccia WindowListener
 * Deve gestire gli eventi generati dalla finestra
 * @author ITIS
 */
public class FinestraCheSiAscolta implements WindowListener {
	/**
	 * Finestra principale dell'applicazione
	 */
	private Frame f;	
	
	public FinestraCheSiAscolta() {	
		f = new Frame("Finestra di esempio");
		f.setSize(400,300);		//dimensionamento
		f.setLocation(200,200);	//posizionamento
		// La classe stessa si registra come ascoltatore per gli eventi
		f.addWindowListener(this); 
		f.setVisible(true);		//visualizzazione
	}

// Dobbiamo gestire tutti gli eventi di una finestra	
	public void windowIconified(WindowEvent e){	}
	public void windowDeiconified(WindowEvent e){ }
	public void windowActivated(WindowEvent e){	}	
	public void windowDeactivated(WindowEvent e){ }
	public void windowOpened(WindowEvent e){ }
	public void windowClosed(WindowEvent e){ }
// Gestiamo per il momento solo l'evento di chiusura
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
	
	public static void main(String args[]) {
		// Istanziazione di un oggetto della classe
		@SuppressWarnings("unused")
		FinestraCheSiAscolta esempio = new FinestraCheSiAscolta();
	}
}