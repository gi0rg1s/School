import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Demo uso ComboBox 
 * @author ITIS
 */ 

public class ComboBoxSemplice extends JFrame implements ActionListener {
    
	/** Label che contiene il testo selezionato */
	private JLabel testo;	
	/** Combo con lista animali */
	private JComboBox listaAnimali;
	/** Nomi degli animali */
	private String[] stringheAnimali = { "Uccello", "Gatto", "Cane", "Coniglio", "Maiale" };

    public ComboBoxSemplice() {
        inizializzaComponenti();
    }

	private void inizializzaComponenti() {
    	// Istanzia la comboBox e associa l'array di stringhe
        listaAnimali = new JComboBox(stringheAnimali);
        listaAnimali.addActionListener(this);
        // Istanzia la label
        testo = new JLabel();
        // Posizionamento componenti
        this.add(listaAnimali, BorderLayout.NORTH);
        this.add(testo,BorderLayout.SOUTH);
        // Caratteristiche finestra
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    /** ascoltatore combo box. */
    public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String nome = (String)cb.getSelectedItem();
        testo.setText(nome);
    }

    public static void main(String[] args) {
		JFrame finestra = new ComboBoxSemplice();
    }
}
