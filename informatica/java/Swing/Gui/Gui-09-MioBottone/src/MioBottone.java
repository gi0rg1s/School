import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Bottone che si ascolta e diventa rosso
 * @author ITIS
 */
@SuppressWarnings("serial")
public class MioBottone extends JButton implements ActionListener {
	
	/**
	 * controlla se il bottone è premuto
	 */
	private boolean premuto;
	
	public MioBottone() {
		super();
		premuto = false;
		this.setBackground(Color.GREEN);
		this.addActionListener(this);
	}

	public MioBottone(Action a) {
		super(a);
	}

	public MioBottone(Icon icon) {
		super(icon);
	}

	public MioBottone(String text, Icon icon) {
		super(text, icon);
	}

	public MioBottone(String name) {
		super(name);
		premuto = false;
		this.setBackground(Color.GREEN);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!premuto)
			this.setBackground(Color.RED);
		else
			this.setBackground(Color.GREEN);
		premuto = !premuto;
	}

}
