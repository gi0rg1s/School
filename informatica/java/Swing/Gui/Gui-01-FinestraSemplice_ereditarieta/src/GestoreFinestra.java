// Classi per la gestione degli eventi
import java.awt.event.*;
/**
 * GestoreFinestra implementa l'interfaccia WindowListener
 * E' un ascoltatore degli eventi generati da una finestra
 * Ha metodi per rispondere ai vari eventi generati
 * @author ITIS
 */
public class GestoreFinestra implements WindowListener {
// 
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
}