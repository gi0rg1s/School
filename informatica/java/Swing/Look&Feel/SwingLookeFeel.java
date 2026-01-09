import javax.swing.*;
import java.awt.event.*;

public class SwingLookeFeel extends JFrame {
    private JLabel etichetta = new JLabel(UIManager.getLookAndFeel().toString(), JLabel.CENTER);
    
    SwingLookeFeel () {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	GestoreBottoni gestoreBottoni = new GestoreBottoni();
        JButton bottone1 = new JButton("Cross Platform (Metal)");
        bottone1.addActionListener(gestoreBottoni);
        JButton bottone2 = new JButton("Windows");
        bottone2.addActionListener(gestoreBottoni);
        JButton bottone3 = new JButton("Metal");
        bottone3.addActionListener(gestoreBottoni);
        JButton bottone4 = new JButton("Motif");
        bottone4.addActionListener(gestoreBottoni);
        add(etichetta, "Center");
        add(bottone1, "North");
        add(bottone2, "East");
        add(bottone3, "West");
        add(bottone4, "South");
    }    
    	
    public static void main(String[] args) {
        SwingLookeFeel finestra = new SwingLookeFeel();
        finestra.setTitle("Finestra di prova Look&Feel");
        finestra.setSize(650,200);
        finestra.setVisible(true);
    }
    
    void cambiaLookAndFeel(String lef) {
    	try {
	        if (lef.equals("Cross Platform (Metal)"))
	         	// Settaggio Look&Feel indipendente dalla piattaforma
	         	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			if (lef.equals("Windows"))
	    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    	if (lef.equals("Metal"))
	    		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	    	if (lef.equals("Motif"))
	    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	    		
	    	etichetta.setText(UIManager.getLookAndFeel().toString());	
	    	SwingUtilities.updateComponentTreeUI(this);
	        }
	    	catch(Exception err) {
	    		System.out.print(err);
	    	}
    }
 
	private class GestoreBottoni implements ActionListener {
	     public void actionPerformed(ActionEvent e)
	     { 
	     	cambiaLookAndFeel(e.getActionCommand());
	     }
	}
}


