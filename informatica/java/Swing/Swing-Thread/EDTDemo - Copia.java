import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 Serve per far eseguire il codice del run() (del Runnable) nel contesto del EDT, event-dispatch-thread. 
 L'architettura di Swing non è thread-safe. Salvo pochi casi peraltro ben documentati, agire sui componenti 
 di una interfaccia utente Swing (settare proprietà di componenti, layout ecc...) va fatto solo nel contesto del EDT.
*/

public class EDTDemo extends JFrame{
	int tempoAttesa = 10;
    JButton bottoneNormale, bottoneEDT;
    JFrame frame;

    public EDTDemo(){
    	frame = this;
        bottoneNormale = new JButton("Eventi Swing");
        bottoneNormale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                demoNormale();
            }
        });
        add(bottoneNormale, BorderLayout.NORTH);
        
        bottoneEDT = new JButton("Eventi Swing EDT");
        bottoneEDT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                demoEDT();
            }
        });
        add(bottoneEDT, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

	private void demoNormale(){
		try {
	        for (int i = 10; i > 0; i--) {
    	        final String countValue = String.valueOf(i);
                java.awt.Font f = new java.awt.Font("Arial", java.awt.Font.BOLD, 100);
                frame.getGraphics().setFont(f);
                frame.getGraphics().drawString(countValue, Integer.valueOf(countValue) * 10 + 50, Integer.valueOf(countValue) * 10 + 100);
            	Thread.sleep(tempoAttesa);
        	}
        	frame.getGraphics().clearRect(0, 50, 400, 350);
        }
        catch(Exception err){ };
		bottoneNormale.setEnabled(true);
    }
    
    private void demoEDT(){
        Thread contatore = new Thread(new Contatore());
        contatore.start();
        bottoneEDT.setEnabled(false);
    }
    
    public void paint(Graphics g) {
		g.fill3DRect(100, 100, 220, 220, true);
	}

    class Contatore implements Runnable{
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    final String countValue = String.valueOf(i);
                    // Inserisce il metodo run() nella coda degli eventi swing
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            java.awt.Font f = new java.awt.Font("Comic San Serif", java.awt.Font.BOLD, 100);
                            frame.getGraphics().setFont(f);
                            frame.getGraphics().drawString(countValue, Integer.valueOf(countValue) * 10 + 50, Integer.valueOf(countValue) * 10 + 100);
                        }
                    });
                    Thread.sleep(tempoAttesa);
                }
                
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	frame.getGraphics().clearRect(0, 50, 400, 350);
                        bottoneEDT.setEnabled(true);
                    }
                });
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                EDTDemo ex = new EDTDemo();
            }
        });
    }
}