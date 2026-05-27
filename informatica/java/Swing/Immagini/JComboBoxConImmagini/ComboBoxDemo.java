import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Demo uso ComboBox e immagini
 *   ComboBoxDemo.java usa le immagini:
 *   immagini/Bird.gif
 *   immagini/Cat.gif
 *   immagini/Dog.gif
 *   immagini/Rabbit.gif
 *   immagini/Pig.gif
 * @author ITIS
 */ 

public class ComboBoxDemo extends JFrame implements ActionListener {
    
	/** Label che contiene l'immagine selezionata */
	private JLabel immagine;	
	/** Combo con lista animali */
	private JComboBox listaAnimali;
	/** Nomi dei file senza estensione */
	private String[] stringheAnimali = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

    public ComboBoxDemo() {
        inizializzaComponenti();
    }

	private void inizializzaComponenti() {
    	// Istanzia la comboBox e associa l'array di stringhe
        listaAnimali = new JComboBox(stringheAnimali);
        // L'indice selezionato e' il 4 (ultimo)
        listaAnimali.setSelectedIndex(4);
        listaAnimali.addActionListener(this);

        // Istanzia la label
        immagine = new JLabel();
        // modifica alcune caratteristiche
        immagine.setFont(immagine.getFont().deriveFont(Font.ITALIC));
        immagine.setHorizontalAlignment(JLabel.CENTER);
        
        aggiornaLabel(stringheAnimali[listaAnimali.getSelectedIndex()]);
        immagine.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        // Il preferred size e' hard-coded (forzato direttamente nel codice) per essere
        // corrispondente ai pixel dell'immagine + bordo.
        // Ovviamente si dovrebbe calcolare in base all'immagine caricata.
        immagine.setPreferredSize(new Dimension(177, 122+10));
        //getContentPane().setOpaque(true);

        add(listaAnimali, BorderLayout.PAGE_START);
        add(immagine, BorderLayout.PAGE_END);
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    

    /** ascoltatore combo box. */
    public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String nome = (String)cb.getSelectedItem();
        aggiornaLabel(nome);
    }

    /**
     * Aggiorna la label associando una immagine
     * @param nome nome del file .gif nella sottocartella immagini
     */
    private void aggiornaLabel(String nome) {
        ImageIcon icona = creaImageIcon("immagini/" + nome + ".gif");
        immagine.setIcon(icona);
        immagine.setToolTipText("Immagine del " + nome.toLowerCase());
        if (icona != null) {
            immagine.setText(null);
        } else {
            immagine.setText("Immagine non trovata!");
        }
    }

    /**
     * Dato un path completo recupera ImageIcon
     * @param path nome completo del file che contiene l'immagine
     * @return ImageIcon associato al file
     */
    private static ImageIcon creaImageIcon(String path) {
        java.net.URL imgURL = ComboBoxDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Non trovo il file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
		JFrame finestra = new ComboBoxDemo();
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.pack();
		finestra.setVisible(true);
    }
}
