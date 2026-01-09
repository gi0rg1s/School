/**
 * @(#)PannelloConImmagine.java
 *
 * @author Alberto Paganuzzi
 * @version 1.00 2015/04/09
 */

import java.awt.*; 
import javax.swing.*; 

public class PannelloConImmagine extends JFrame {
	
	private JPanel pannello = new FaiPannelloConImmagine();
	
	public PannelloConImmagine(String titolo) {
		super(titolo);
		this.add(pannello, "Center");
	}
	
	public static void main(String args[]) {
	  PannelloConImmagine frame = new PannelloConImmagine("Pannello con Immagine");
	  frame.setSize(400, 400);
	  frame.setVisible(true);
	}
}

// La classe java.awt.Toolkit fornisce svariati servizi di "utilità" legati in generale alla interfaccia grafica 

class FaiPannelloConImmagine extends JPanel { 
	private Image sfondo; 

 	public FaiPannelloConImmagine() { 
    	super(); 
    	Toolkit tk = getToolkit(); 
    	sfondo = tk.getImage("javaLogo.jpg");
    	sfondo = sfondo.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
    	
    	/*
    	 Se si vuole attendere il completamento della immagine è sufficiente usare la classe java.awt.MediaTracker.
    	 Si istanzia un MediaTracker, si aggiunge una immagine con uno dei metodi addImage e infine si attende il 
    	 completamento con uno dei metodi waitForXXX
    	 */
    	MediaTracker mt = new MediaTracker(this);
		mt.addImage(sfondo, 0);

		try { 
        	mt.waitForID(0); 
		} 

		catch (InterruptedException err) { 
        		System.out.println("Ho finito di caricare"); 
		} 
    	System.out.println(sfondo.getHeight(this));
 	} 
    
    public void paintComponent(Graphics g) { 
        g.drawImage(sfondo, 0, 0, this); 
    } 
}