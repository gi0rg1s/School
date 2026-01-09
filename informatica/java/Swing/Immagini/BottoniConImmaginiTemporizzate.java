/**
 * @(#)BottoniConImmaginiTemporizzate.java
 *
 * @author Alberto Paganuzzi
 * @version 1.00 2015/04/09
 */

import java.awt.*; 
import javax.swing.*; 

public class BottoniConImmaginiTemporizzate {
    private JFrame finestraPrincipale;
    private JButton bottone1;
    private JButton bottone2;

    public BottoniConImmaginiTemporizzate() {
    	finestraPrincipale = new JFrame("Bottoni con Immagini Temporizzate");
    	finestraPrincipale.setLayout(new FlowLayout());
    	finestraPrincipale.setSize(1000, 500);
    	finestraPrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	bottone1 = new JButton("bottone 1");
    	bottone2 = new JButton("bottone 2");
    	finestraPrincipale.add(bottone1);
    	finestraPrincipale.add(bottone2);
    	finestraPrincipale.setVisible(true);
    }
    
    private void temporizzaImmagini() {
   		/* 
   		 *
   		 *L'immagine caricata con Toolkit è di tipo Image, mentre
    	* il metodo setIcon del JButton richiede un'ImageIcon.
    	* ImageIcon ha un costruttore con Image come parametro, 
    	* quindi la conversione risulta molto semplice.
    	* (il passaggio inverso si può fare utilizzando il metodo getImage di ImageIcon)
    	*/
    	Toolkit tk;
    	//prende il Toolkit della finestra, necessario per caricare un'immagine
    	tk = finestraPrincipale.getToolkit();
    	//carica l'immagine
    	String [] arrayFileImmagini = {"javaLogo.png", "javaLogo.jpg"};
    	int indice = 1;
    	while(true) {
    		indice = (indice == 0 ? 1 : 0);
    		
    		Image img = tk.getImage(arrayFileImmagini[indice]);
    		try {
    			Thread.sleep(2000);
    		}
    		catch (Exception err){};
    		ImageIcon immagineBottone = new ImageIcon(img);
    		bottone1.setIcon(immagineBottone);
    		
    		img = tk.getImage(arrayFileImmagini[indice]);
    		try {
    			Thread.sleep(2000);
    		}
    		catch (Exception err){};
    		immagineBottone = new ImageIcon(img);
    		bottone2.setIcon(immagineBottone);
    	}
    }
    
    public static void main(String[] args) {
        BottoniConImmaginiTemporizzate f = new BottoniConImmaginiTemporizzate();
        f.temporizzaImmagini();
    }
}
