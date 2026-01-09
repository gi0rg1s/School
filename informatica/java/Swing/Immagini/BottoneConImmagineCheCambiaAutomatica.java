/**
 * @(#)BottoneConImmagineCheCambiaAutomatica.java
 * @author Alberto Paganuzzi
 * @version 1.00 2015/04/09
 */

import javax.swing.*;
import java.awt.event.*;

public class BottoneConImmagineCheCambiaAutomatica implements ActionListener {
    private JFrame finestraPrincipale;
    private JButton bottone;
    private Timer timer = null;
    private ImageIcon icona1, icona2;

    public BottoneConImmagineCheCambiaAutomatica() {
    	finestraPrincipale = new JFrame("Bottone con immagine che cambia automaticamente");
    	finestraPrincipale.setLayout(new java.awt.FlowLayout());
    	finestraPrincipale.setSize(600, 450);
    	finestraPrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	bottone = new JButton("Fai click....");
    	icona1 = new ImageIcon("javaLogo.png");
    	icona2 = new ImageIcon("javaLogo.jpg");
    	bottone.setIcon(icona1);
    	bottone.addActionListener(this);
    	finestraPrincipale.add(bottone);
    	finestraPrincipale.setVisible(true);
    	
    	/***
    	 javax.swing.Timer è una classe dedicata alle Swing che permette di temporizzare operazioni di 
    	 aggiornamento dei componenti grafici GUI
    	*/
    	timer = new Timer(2000, new ActionListener() {
    		// L'evento si solleva automaticamente dopo il tempo in millisecondi indicato nel primo parametro
		    public void actionPerformed(ActionEvent evt) {
	            timer.stop();
	            // Si ripristima l'immagine iniziale
	          	bottone.setIcon(icona1);
		    }    
		});
    }

    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bottone) {
	      ImageIcon i = (ImageIcon) bottone.getIcon();
	      bottone.setIcon(icona2);
	      // Si avvia il timer che solleverà un evento automaticamente dopo tot millisecondi sostituendo l'icona del bottone
    	  timer.start();
		}
	}

    public static void main(String[] args) {
        BottoneConImmagineCheCambiaAutomatica f = new BottoneConImmagineCheCambiaAutomatica();
    }
}