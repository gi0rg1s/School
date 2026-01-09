// Classi per la gestione delle componenti grafiche
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * @author ITIS
 */
public class FinestraSecondaria {
	private JFrame f2;
	private JLabel label;

	public FinestraSecondaria() {		
		f2 = new JFrame("Finestra Secondaria");
		f2.setSize(300,200);
		f2.setLocation(400,200);
		label = new JLabel("...........");
		f2.add(label, "Center");
		f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f2.setBackground(Color.green);
		f2.setVisible(true);
	}
	
	/**
	 * Metodo pubblico per popolare un componente 'label' del JFrame dall'esterno
	 * @param stringa
	 */
	
	public void setEtichetta(String stringa) {
		label.setText(stringa);
	}
}