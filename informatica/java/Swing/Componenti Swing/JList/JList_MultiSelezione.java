import javax.swing.*; 
import javax.swing.event.*; 
import java.awt.*;
/**
 @author Alberto Paganuzzi 
 Implementa una JList con multiselezione popolata con array passato al costruttore
 Implementa ascoltatore di selezione. Ad ogni click mostra le stringhe delle righe selezionate
 */

public class JList_MultiSelezione extends JFrame { 
	private JList<String> list; 
	private JTextArea output; 
	
	public JList_MultiSelezione() { 
		super("JList con MultiSelezione"); 
		setSize(170,220); 
		getContentPane().setLayout(new GridLayout(0,1)); 
		// Crea 20 elementi 
		String[] items = new String[20]; 
		for(int i=0; i<20; i++) 
		  items[i] = "Elemento numero " + String.valueOf(i); 
		// Inizializza una JList  
		list= new JList<String>(items); 
		list. setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
		ListSelectionListener selectionListener = new SelectionListener(); 
		list.addListSelectionListener(selectionListener); 
		// Crea la TextArea di output 
		output = new JTextArea(); 
		output.setEditable(false); 
		// assembla la GUI 
		getContentPane().add(new JScrollPane(list)); 
		getContentPane().add(new JScrollPane(output)); 
		setVisible(true);  
	} 
	
	class SelectionListener implements ListSelectionListener { 
		public void valueChanged(ListSelectionEvent e) { 
			if(!e.getValueIsAdjusting()) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>)e.getSource(); 
				output.setText(""); 
				for(String item: list.getSelectedValuesList()) 
					output.append(item + "\n"); 
			} 
		} 
	} 
	public static void main(String argv[]) {
		new JList_MultiSelezione(); 
	} 
}
