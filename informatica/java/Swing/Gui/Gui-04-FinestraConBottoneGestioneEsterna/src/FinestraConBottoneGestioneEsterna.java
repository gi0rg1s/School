import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;


/**
 * La gestione degli eventi del Frame e del Button e' esterna
 * @author alberto
 *
 */
public class FinestraConBottoneGestioneEsterna {

	private Frame f;
	private Label l;
	private Button b;

	public FinestraConBottoneGestioneEsterna() {
		f = new Frame("Finestra di esempio");
		f.setSize(400,300);		
		f.setLocation(200,200);	
		// Registriamo un ascoltatore per gli eventi
		f.addWindowListener(new GestoreFinestra()); 
		l = new Label("Bottone non premuto");
		b = new Button("Prova a cliccare");
		// Registriamo un gestore per il bottone e passiamo la Label l
		b.addActionListener(new GestoreBottone(l));
		f.add(l,"North");
		f.add(b,"South");
		f.setVisible(true);		
	}

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		FinestraConBottoneGestioneEsterna esempio = new FinestraConBottoneGestioneEsterna();
	}
}