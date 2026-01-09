import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;

public class JTableSemplicissima extends JFrame {
	
	JTable tabella = new JTable(new String[][] { { "prima", "riga" },
	                                             { "seconda", "riga" },
	                                             { "terza", "riga" }
	                                           }, 
		                        new String[]   { "Colonna 1", "Colonna 2"}
		                        );
	JScrollPane pannello = new JScrollPane(tabella);
	
	public JTableSemplicissima () {
		getContentPane().add(pannello);
	}
	
	public static void main(String args[]) {
		JTableSemplicissima frameTabella = new JTableSemplicissima();
		frameTabella.setSize(600, 250);
		frameTabella.setVisible(true);	
	}
}	