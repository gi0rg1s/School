import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class EventiTastiera extends JFrame implements KeyEventDispatcher {
	final static JLabel label = new JLabel();
	
	public EventiTastiera() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		KeyboardFocusManager kfm =KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(this);
	}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		label.setText("" + e.getKeyChar());
		return false; // non consumare l'evento
		// return true; // consuma l'evento
	}
	
	public final static void main(final String[] args) {
		JTextArea textarea = new JTextArea();
		EventiTastiera t = new EventiTastiera();
		t.getContentPane().add(textarea, BorderLayout.CENTER);
		t.getContentPane().add(label, BorderLayout.SOUTH);
		t.setSize(100, 100);
		t.setVisible(true);
	}
}


