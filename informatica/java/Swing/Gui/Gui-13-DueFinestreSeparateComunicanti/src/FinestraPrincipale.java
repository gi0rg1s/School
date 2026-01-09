import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * Esempio di 2 finestre che si trasmettono un valore (stringa)
 * Nella seconda finestra JFrame è stato incapsulato un metodo pubblico che serve a popolare un JLabel dall'esterno della classe al click del bottone
 * Tutte le impostazioni grafiche vengono effettuate dal costruttore
 * @author ITIS
 */
public class FinestraPrincipale {
	
	/**
	 * Finestra principale dell'applicazione
	 */
	private JFrame finestraPrincipale;
	private JButton bottone;
	private JTextField campoTesto;
	FinestraSecondaria f2;

	public FinestraPrincipale() {
		super();
		finestraPrincipale = new JFrame("Finestra Principale");
		finestraPrincipale.setSize(300, 200);		//dimensionamento 
		finestraPrincipale.setLocation(100, 100);	//posizionamento
		bottone = new JButton("Trasmetti stringa");
		bottone.addActionListener(new AscoltaBottone());
		campoTesto = new JTextField("scrivi qualcosa qui");
		finestraPrincipale.add(bottone, "North");
		finestraPrincipale.add(campoTesto, "Center");
		finestraPrincipale.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		finestraPrincipale.addWindowListener(new AscoltaFinestra());
		finestraPrincipale.setBackground(Color.cyan);
		finestraPrincipale.setVisible(true);		//visualizzazione
		
		f2 = new FinestraSecondaria();
	}
	
	class AscoltaBottone implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			f2.setEtichetta(campoTesto.getText());
		}
	}

	public static void main(String args[]) {
		new FinestraPrincipale();
	}
	
	class AscoltaFinestra implements WindowListener {
		public void windowIconified(WindowEvent e){	}
		public void windowDeiconified(WindowEvent e){ }
		public void windowActivated(WindowEvent e){	}	
		public void windowDeactivated(WindowEvent e){ }
		public void windowOpened(WindowEvent e){ }
		public void windowClosed(WindowEvent e){ }
		// Gestiamo per il momento solo l'evento di chiusura
		public void windowClosing(WindowEvent e){
			JOptionPane.showMessageDialog(null, "Chiudo tutto e me ne vado....", "Saluti", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}				
	}
}