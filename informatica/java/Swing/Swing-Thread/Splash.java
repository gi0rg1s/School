import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Splash extends JWindow {
	private int duration;		
	
	public Splash(int duration) {				
		this.duration = duration;				
		// qui crei il tuo splash screen attaccando le varie immagini		
		// label ecc. come quando crei un JFrame		
		getContentPane().add(new JLabel("This is a splash screen"));		
		((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));		
		pack();	
	}		
	
	public void splashWaitAndStop() {		
		// visualizza lo splash		
		SwingUtilities.invokeLater(new Runnable() {			
			public void run() {				
				setVisible(true);			
			}	
		});				
		// attendi per tot ms		
		try {			
			Thread.sleep(duration);		
		}		
		catch(InterruptedException e) {}						
		// nascondi lo splash		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setVisible(false);			
			}	
		});			
	}		
	
	public static void main(String[] arg) {		
		Splash p = new Splash(5000);		
		p.splashWaitAndStop();	
	}	
}

