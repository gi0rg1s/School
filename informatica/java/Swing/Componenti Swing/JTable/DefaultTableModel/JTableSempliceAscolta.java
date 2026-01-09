import java.awt.*;
import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;

public class JTableSempliceAscolta extends JFrame {
	private JTable tabella;
	
	public JTableSempliceAscolta() {
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	    DefaultTableModel modello = new DefaultTableModel(
		  new String[][] {
		    {"1", "2", "3"},
		    {"4", "5", "6"} },
		  new String[] {"1ª colonna", "2ª colonna", "3ª colonna"});
	
	    tabella = new JTable(modello);
	    tabella.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tabella.rowAtPoint(evt.getPoint());
		        int col = tabella.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0)
		            System.out.println("Cella: " + row + col );
		    }
		});
	
	    JScrollPane pannello = new JScrollPane(tabella);
	    getContentPane().add(pannello, BorderLayout.CENTER);
	}
	
	public static void main(String args[]) {
		JTableSempliceAscolta frameTabella = new JTableSempliceAscolta();
		frameTabella.setSize(600, 250);
		frameTabella.setVisible(true);
	}
}