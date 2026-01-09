import javax.swing.*;
import java.awt.event.*;

class ProvaFileChooserEsterno {

	private JFrame f;
	private JLabel l;
	private JButton b;

	public ProvaFileChooserEsterno() {
		f = new JFrame("Finestra di esempio");
		f.setSize(400,300);		
		f.setLocation(200,200);
		l = new JLabel("Bottone non premuto");
		b = new JButton("Prova a cliccare");
		b.addActionListener(new GestoreBottone());
		f.add(l,"North");
		f.add(b,"South");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public static void main(String args[]) {
		ProvaFileChooserEsterno esempio = new ProvaFileChooserEsterno();
	}
	
	public class GestoreBottone implements ActionListener {
		public void actionPerformed(ActionEvent e){
			ScegliFile nome = new ScegliFile(f);
			l.setText(nome.nomeFile);
		}				
	}
}