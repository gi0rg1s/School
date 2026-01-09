import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;

public class JTableModelloLettura extends JFrame {
	
	TableModel modello = new AbstractTableModel() {
		public int getColumnCount() {
			return 10;
		}

		public int getRowCount() {
			return 10;
		}
		
		public Object getValueAt(int row, int col) {
			return new Integer(row * col); 
		}
	};

	JTable tabella = new JTable(modello);
	JScrollPane pannello = new JScrollPane(tabella);
	
	public JTableModelloLettura () {
		getContentPane().add(pannello);
	}
	
	public static void main(String args[]) {
		JTableModelloLettura frameTabella = new JTableModelloLettura();
		frameTabella.setSize(600, 240);
		frameTabella.show();	
	}
}	