/**
 * @(#)DragDrop.java
 *
 * @author zalberto
 * @version 1.0 10/02/2014
 */
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragDrop extends JFrame {
	public DragDrop() {
    	JButton bottone = new JButton("Testo del Bottone");
    	JTextField textField = new JTextField("Testo di JTextField");
    	JLabel label = new JLabel("Trascina il testo del bottone sul campo di testo in alto");
    	
		// Attiva un gestore di dragDrop testuale (dal sorgente al destinatario si muoverà del testo)
		TransferHandler transfer = new TransferHandler("text");

		// Il testo del bottone può essere draggato col mouse altrove
		bottone.setTransferHandler(transfer);
		
		// Configura l'ascoltatore del click di mouse sul bottone per iniziare il Drag
		bottone.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                JButton b = (JButton)e.getSource();
                TransferHandler handle = b.getTransferHandler();
                handle.exportAsDrag(b, e, TransferHandler.COPY);
            }
        });

		this.add(bottone, "Center");
		this.add(textField, "North");
		this.add(label, "South");

		setTitle("Drag and Drop");
		setSize(400, 400);
		setLocation(200, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		DragDrop frame = new DragDrop();	
	}
}