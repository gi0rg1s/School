import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JSlider;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Demo uso JSlider 
 * @author ITIS
 */ 

public class JSliderDemo extends JFrame implements ChangeListener {
    
	/** Label che contiene il valore restituito da jSlider */
	private JLabel testo;	
	/** Slider */
	private JSlider jSlider;

    public JSliderDemo() {
        inizializzaComponenti();
    }

	private void inizializzaComponenti() {
    	// Istanzia il JSlider
        // Un Costruttore di JSlider: new JSlider(Orientamento Slider, valore minimo, valore massimo, valore iniziale)
        jSlider = new JSlider(JSlider.VERTICAL, 1, 10, 1);
        // Sposto la posizione dello slider al valore 5 (metà)
        jSlider.setValue(5);
        jSlider.addChangeListener(this);
        // Istanzia la label
        testo = new JLabel("Muovi lo slider...");
        // Posizionamento componenti
        this.add(jSlider, BorderLayout.CENTER);
        this.add(testo,BorderLayout.SOUTH);
        // Caratteristiche finestra
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    /** Ascoltatore oggetto jSlider quando l'utente muove il controllo. */
    public void stateChanged(ChangeEvent e) {
   		int valore = jSlider.getValue();
      	testo.setText("Valore slider: " + valore);
    }

    public static void main(String[] args) {
		JFrame finestra = new JSliderDemo();
    }
}
