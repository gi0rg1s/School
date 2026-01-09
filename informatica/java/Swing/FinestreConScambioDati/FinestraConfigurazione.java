import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/* Per ritornare alla finestra chiamante i valori inseriti nella textfield si possono dichiarare 3 jlabel non istanziate: j1, j2, j3
   a cui verranno passati i reference (oggetti) di 3 jlabel della pagina chiamante. Sostituendo il valore dell'etichetta del testo
   appena prima di distruggere questa finestra (dispose) si potranno così passare i valori
*/

public class FinestraConfigurazione extends JFrame implements ActionListener {

	JPanel panelConfigurazione = new JPanel();
	JPanel panelComandi = new JPanel();
	JTextField jtf1 = new JTextField(5);
	JTextField jtf2 = new JTextField(5);
	JTextField jtf3 = new JTextField(5);
	JButton bottone = new JButton("OK");
	JLabel j1, j2, j3;

	public FinestraConfigurazione() {
	    panelConfigurazione.add(new JLabel("attributo 1: ", Label.RIGHT));
	    panelConfigurazione.add(jtf1);
   	    panelConfigurazione.add(new JLabel("attributo 2: ", Label.RIGHT));
	    panelConfigurazione.add(jtf2);
		panelConfigurazione.add(new JLabel("attributo 3: ", Label.RIGHT));
	    panelConfigurazione.add(jtf3);
	    
	    panelComandi.add(new JLabel("Conferma ed esci: ", Label.RIGHT));
	    panelComandi.add(bottone);
	    bottone.addActionListener(this);
	    
	    add(panelComandi, "South");
	    add(panelConfigurazione, "Center");
	    add(new JLabel("Inserisci i valori: ", Label.RIGHT), "North");
	    setSize(200, 300);
	    setTitle("Configurazione");
  	}

  	public void actionPerformed(ActionEvent e) {
  		if (e.getActionCommand().compareTo("OK") == 0) {
  			j1.setText(jtf1.getText());
  			j2.setText(jtf2.getText());
  			j3.setText(jtf3.getText());
    		dispose();
  		}
  	}
}
