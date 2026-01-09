
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * Utilizzo della libreria Swing
 * La classe implementa l'interfaccia ActionListener per poter
 * gestire gli eventi del bottone
 * @author ITIS
 */
public class FinestraConBottone implements ActionListener{

	/** Finestra principale */
	public JFrame f;
	
	/** Label di esempio */
	public JLabel l;
	
	/** Button di cui gestiremo l'evento */
	public JButton b;

	public FinestraConBottone() {
		f = new JFrame("Finestra di esempio");
		f.setSize(400,300);		//dimensionamento
		f.setLocation(200,200);	//posizionamento
		// Registriamo un ascoltatore per gli eventi
		f.addWindowListener(new GestoreFinestra()); 
		
        // Inserimento dei componenti grafici nella finestra
		l = new JLabel("Bottone non premuto");
		
		
		b = new JButton("Prova a cliccare");
		// Ci registriamo come ascoltatore degli eventi del bottone
		b.addActionListener(this);
		f.add(l,"East");
		
		//
		f.setLayout(new BorderLayout());
		f.add(l, BorderLayout.CENTER);
		//
		f.add(b,"South");
		f.setVisible(true);		//visualizzazione
	}

	/**
	 * Unico metodo dell'interfaccia ActionListener
	 * Gestisce gli eventi sul bottone
	 */
	public void actionPerformed(ActionEvent e) {
		l.setText("Bottone premuto");
	}

	public static void main(String args[]) {
		// Istanziazione di un oggetto della classe
		@SuppressWarnings("unused")
		FinestraConBottone esempio = new FinestraConBottone();
	}
}
