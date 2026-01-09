/**
 * @(#)IconeRidimensionabili.java
 *
 * @author Fabio Paini
 * @version 1.00 2012/2/1
 */

import java.awt.*; 
import javax.swing.*; 

public class IconeRidimensionabili {
    private static final int dimensioneBottone = 100;
        
    private JFrame    finestraPrincipale;
    private JButton[] bottoni = new JButton[4];
        
    /**
     * Creates a new instance of <code>IconeRidimensionabili</code>.
     */
    public IconeRidimensionabili() {
    	Toolkit tk;
    	Image img;
    	
    	finestraPrincipale = new JFrame("Icone Ridimensionabili");
    	finestraPrincipale.setSize(600,400);
    	finestraPrincipale.setLocation(100,100);
    	finestraPrincipale.setLayout(null);
    	
    	//prende il Toolkit della finestra, necessario per caricare un'immagine
    	tk = finestraPrincipale.getToolkit();
    	//carica l'immagine
    	img = tk.getImage("javaLogo.jpg");
    	//scala l'immagine alle dimensioni volute (in questo caso quelle dei bottoni)
    	img = img.getScaledInstance(dimensioneBottone, dimensioneBottone, Image.SCALE_SMOOTH);
		
		/*
    	 *Opzioni per scalabilità delle immagini.

		//	SCALE_DEFAULT = Immagine-scaling algoritmo predefinito.
		//	
		//	SCALE_FAST = Algoritmo che dà maggiore priorità alla velocità di scala che la fluidità delle immagini in scala.
		//	 
		//	SCALE_SMOOTH = Algoritmo che dà maggiore priorità alla fluidità dell'immagine rispetto alla velocità di scala.
		//	 
		//	SCALE_REPLICATE = Algoritmo di scalatura delle immagini incorporato nella classe ReplicateScaleFilter. L'oggetto immagine è libero di sostituire un filtro diverso che esegue lo stesso algoritmo ma si integra in modo più efficiente con l'infrastruttura di imaging fornito dal toolkit.
		//	 
		//	SCALE_AREA_AVERAGING = L'oggetto immagine è libero di sostituire un filtro diverso che esegue lo stesso algoritmo ma si integra in modo più efficiente nelle infrastrutture immagine fornita dal toolkit.
    	 */
    	 
    	
    	for(int i=0; i<bottoni.length; i++) {
    		bottoni[i] = new JButton();
    		bottoni[i].setSize(dimensioneBottone,dimensioneBottone);
    		bottoni[i].setLocation(i*dimensioneBottone,0);
    		
    		/*
    		 * L'immagine precedentemente caricata è di tipo Image, mentre
    		 * il metodo setIcon del JButton richiede un'ImageIcon. Fortunatamente,
    		 * ImageIcon ha un costruttore con un Image come parametro, 
    		 * quindi la conversione risulta molto semplice.
    		 * (il passaggio inverso si può fare utilizzando il metodo getImage
    		 * di ImageIcon)
    		 */
    		ImageIcon immagineBottone = new ImageIcon(img);
    		bottoni[i].setIcon(immagineBottone);
    		
    		finestraPrincipale.add(bottoni[i]);
    	}
    	
    	finestraPrincipale.setVisible(true);
    	
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new IconeRidimensionabili();
    }
}
