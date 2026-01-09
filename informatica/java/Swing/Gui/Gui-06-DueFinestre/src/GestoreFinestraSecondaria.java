import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GestoreFinestraSecondaria implements WindowListener {
// 
	public void windowIconified(WindowEvent e){	}
	public void windowDeiconified(WindowEvent e){ }
	public void windowActivated(WindowEvent e){	}	
	public void windowDeactivated(WindowEvent e){ }
	public void windowOpened(WindowEvent e){ }
	public void windowClosed(WindowEvent e){ }

	/**
	 * La finestra diventa invisibile
	 * param e utilizzato per recuperare il Frame chiamante
	 */
	public void windowClosing(WindowEvent e){
		Frame locale;
		locale = (Frame) e.getSource();
		locale.setVisible(false);
	}				
}