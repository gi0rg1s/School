import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*; 

public class LayoutManagerConJPanel extends JFrame implements ActionListener  { 
  
	/** Bottone esci */
	private JButton bottone;
	/** Label che contiene testo */
	private JLabel etichetta;	
	/** Array di JButton */
	private JButton[] bottoni;
	
	public LayoutManagerConJPanel() {
		super(); // Consigliato da Oracle
		creaGUI();
	}
  
	private void creaGUI() {
	  	//***** CONFIGURAZIONE PANNELLO NORD ********/
	  	
	  	//Inserisco un JPanel a nord. LayoutManager default (FlowLayout)
		JPanel pannelloNord = new JPanel();
	  	pannelloNord.setBackground(Color.ORANGE);
	  	this.add(pannelloNord, BorderLayout.NORTH);
	  	
	  	// Inserisco un JButton nel pannello nord 
		bottone = new JButton("Clicca qui"); 
	  	bottone.addActionListener(this);
	  	pannelloNord.add(bottone);

        // Inserisco JLabel a nord
        etichetta = new JLabel("Etichetta a nord");
        pannelloNord.add(etichetta);


	  	// ****** CONFIGURAZIONE PANNELLO CENTRO *******/
	  	
	  	// inserisco un pannello al centro. LayoutManager configurato con GridLayout
	  	JPanel pannelloCentro = new JPanel();
	  	pannelloCentro.setBackground(Color.CYAN);
	  	this.add(pannelloCentro, BorderLayout.CENTER);
	  	pannelloCentro.setLayout(new GridLayout(2, 3, 5, 5));  // 5, 5 spazio pixel tra celle

	  	// istanzio 6 JButton nell'array e dispongo immediatamente sul pannello centrale (griglia)
	  	bottoni = new JButton[6];
		for (int i = 0; i < 6; i++){
			bottoni[i] = new JButton("" + i); // Casting implicito
			pannelloCentro.add(bottoni[i]);
			bottoni[i].addActionListener(this);
		}

	  	// Pannello a sud
        JPanel pannelloSud = new JPanel();
        // Converte un colore con valore esadecimale in stringa RGB in decimale
	  	pannelloSud.setBackground(Color.decode("#66A0B8"));
	  	this.add(pannelloSud, BorderLayout.SOUTH);
	  	
	  	// Pannello a est
        JPanel pannelloEst = new JPanel();
        // Converte un colore con valore esadecimale in stringa RGB in decimale
	  	pannelloEst.setBackground(Color.decode("#AA3311"));
	  	this.add(pannelloEst, BorderLayout.EAST);

	    // Attributi finestra
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("JPanel demo...");
		setSize(600, 400);
		setVisible(true); 
    }
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bottone)
			etichetta.setText("Hai cliccato!");
		else
			etichetta.setText("Hai cliccato " + ((JButton)e.getSource()).getText()); // 
	}
  
	public static void main(String[] args) {
		new LayoutManagerConJPanel(); 
	}
} 