import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Implementa l'interfaccia ActionListener
 * e' un ascoltatore degli eventi generati da un bottone
 * @author alberto
 *
 */
public class GestoreBottone implements ActionListener {
// 


	/**
	 * Per poter interagire con l'esterno devo avere un parametro
	 * che mi viene passato (in questo esempio e' una Label)
	 * definisco quindi un attributo locale a cui associare la Label
	 */
	private Label l_locale;

	public GestoreBottone(Label l_passato) {
		l_locale = l_passato;
	}				

	/**
	 * Risposta agli eventi sul bottone
	 */
	public void actionPerformed(ActionEvent e){
		String nomeBottoneChiamante;
		// l'evento e inviato mi permette di conoscere
		// quale bottone ha inviato il messaggio
		Button chiMiHaChimato;

		chiMiHaChimato = (Button) e.getSource();
		chiMiHaChimato.setLabel("*****");
		nomeBottoneChiamante = e.getActionCommand();
		l_locale.setText("Ciao "+nomeBottoneChiamante);
	}				
}