import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Calcolatrice binaria
 * gestisce gli eventi dei bottoni e dei textField
 * @author ITIS
 */
public class Calcolatrice extends JFrame implements ActionListener, KeyListener, WindowListener {

	private JTextField operando1;
	private JTextField operando2;
	private JLabel risultato;

	private JButton bottoneAND;
	private JButton bottoneOR;

	public Calcolatrice() {
		this.addWindowListener(this);
		
		this.setSize(400, 300);
		this.setLocation(200, 200);
		this.setBackground(Color.lightGray);
		
		operando1 = new JTextField("1");
		operando2 = new JTextField("0");
		risultato = new JLabel("risultato");
		bottoneAND = new JButton("AND");
		bottoneOR = new JButton("OR");

		bottoneAND.addActionListener(this);
		bottoneOR.addActionListener(this);

		operando2.addKeyListener(this);
		operando1.addKeyListener(this);
		
		operando1.setBackground(Color.YELLOW);
		operando2.setBackground(Color.CYAN);

		this.add(risultato, "South");
		this.add(bottoneAND, "West");
		this.add(bottoneOR, "East");
		this.add(operando1, "North");
		this.add(operando2, "Center");

		this.setVisible(true);
	}

	// *********** Gestione Button ************************
	public void actionPerformed(ActionEvent e) {
		JButton bottone;
		bottone = (JButton) e.getSource();
		if (bottone == bottoneAND) {
			if ((operando1.getText().equals("1"))
					&& (operando2.getText().equals("1")))
				risultato.setText("1");
			else
				risultato.setText("0");
		}
		if (bottone == bottoneOR) {
			if ((operando1.getText().equals("1"))
					|| (operando2.getText().equals("1")))
				risultato.setText("1");
			else
				risultato.setText("0");
		}		
	}
	// *****************************************************

	// ******* Gestione JTextField *************
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		JTextField operando;
		operando = (JTextField) e.getSource();
		char testo = e.getKeyChar();
		if (testo == '1')
			operando.setText("1");		
		else
			operando.setText("0");
	}
	// *****************************************	

	// ********* Gestione JFrame *******************
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	// *********************************************

}
