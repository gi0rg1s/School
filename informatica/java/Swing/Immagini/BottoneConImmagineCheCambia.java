/**
 * @(#)BottoneConImmagineCheCambia.java
 * @author Alberto Paganuzzi
 * @version 1.00 2015/04/09
 */

import javax.swing.*;
import java.awt.event.*;

public class BottoneConImmagineCheCambia implements ActionListener {
    private JFrame finestraPrincipale;
    private JButton bottone;

    public BottoneConImmagineCheCambia() {
    	finestraPrincipale = new JFrame("Bottone con Immagine che cambia");
    	finestraPrincipale.setLayout(new java.awt.FlowLayout());
    	finestraPrincipale.setSize(450, 450);
    	finestraPrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	bottone = new JButton("Fai click....");
    	bottone.setIcon(new ImageIcon("javaLogo.jpg"));
    	bottone.addActionListener(this);
    	finestraPrincipale.add(bottone);
    	finestraPrincipale.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bottone) {
	      
	      ImageIcon i = (ImageIcon) bottone.getIcon();
	      bottone.setIcon(new ImageIcon("javaLogo.png"));
	      
	      
	      /* NON E' POSSIBILE USARE RITARDO PERCHE' IL RENDERING DELLA GUI AVVIENE IN MODO ASINCRONO e DOPO ActionPerformed
	      try {
    		Thread.sleep(2000);
    	  } 
    	  catch (Exception err) {};
    	  */
    	
    	  JOptionPane.showMessageDialog(finestraPrincipale, "Java logo!", "Informazione", JOptionPane.INFORMATION_MESSAGE);
	      bottone.setIcon(i);
		}
	}

    public static void main(String[] args) {
        BottoneConImmagineCheCambia f = new BottoneConImmagineCheCambia();
    }
}