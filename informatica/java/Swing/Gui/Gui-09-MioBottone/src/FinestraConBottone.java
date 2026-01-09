import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * Utilizzo della libreria Swing
 * La classe implementa l'interfaccia ActionListener per poter
 * gestire gli eventi del bottone
 * @author ITIS
 */
public class FinestraConBottone implements WindowListener, ActionListener{

	/** Finestra principale */
	private JFrame f;
	/** Label di esempio */
	private JLabel l;
	/** Button di cui gestiremo l'evento */
	private MioBottone b;

	public FinestraConBottone() {
		f = new JFrame("Finestra di esempio");
		f.setSize(400,300);		//dimensionamento
		f.setLocation(200,200);	//posizionamento
		// Registriamo un ascoltatore per gli eventi
		f.addWindowListener(this); 
        // Inserimento dei componenti grafici nella finestra
		l = new JLabel("Valore:");
		b = new MioBottone("Prova a cliccare");
		// Ci registriamo come ascoltatore degli eventi del bottone
		b.addActionListener(this);
		f.add(l,"North");
		f.add(b,"South");
		f.setVisible(true);		//visualizzazione
	}

	/**
	 * Unico metodo dell'interfaccia ActionListener
	 * Gestisce gli eventi sul bottone
	 */
	public void actionPerformed(ActionEvent e) {
		l.setText(l.getText()+"*");
	}

	public static void main(String args[]) {
		// Istanziazione di un oggetto della classe
		@SuppressWarnings("unused")
		FinestraConBottone esempio = new FinestraConBottone();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}