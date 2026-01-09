/* Gestione finestre multiple e passaggio valori da una finestra all'altra

*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class FinestraPrincipale extends JFrame implements ActionListener{
	public JLabel jl1 = new JLabel();
	public JLabel jl2 = new JLabel();
	public JLabel jl3 = new JLabel();
	JButton bottone = new JButton("Apri finestra configurazione");;
	JPanel panelConfigurazione = new JPanel();

	public FinestraPrincipale() {
		//configurazione pannello
	    panelConfigurazione.add(new JLabel("attributo 1: ", Label.RIGHT));
	    panelConfigurazione.add(jl1);
   	    panelConfigurazione.add(new JLabel("attributo 2: ", Label.RIGHT));
	    panelConfigurazione.add(jl2);
		panelConfigurazione.add(new JLabel("attributo 3: ", Label.RIGHT));
	    panelConfigurazione.add(jl3);
		
		// configurazione bottone
		bottone.addActionListener(this);
		
		// configurazione jframe
		setTitle("Gestione finestre multiple");
		setSize(300,150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(panelConfigurazione, "Center");
		add(bottone, "South");
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bottone) {
			FinestraConfigurazione f = new FinestraConfigurazione();
			f.j1 = jl1;
			f.j2 = jl2;
			f.j3 = jl3;
			f.setVisible(true);
		}
	}
	
	public static void main(String args[]) {
		FinestraPrincipale esempio = new FinestraPrincipale();
	}
}
