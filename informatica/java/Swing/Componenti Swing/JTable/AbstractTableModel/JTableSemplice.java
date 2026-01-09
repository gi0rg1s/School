import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;

public class JTableSemplice extends JFrame {
	
	TableModel dataModel = new AbstractTableModel() {
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

	JTable tabella = new JTable(dataModel);
	JScrollPane pannello = new JScrollPane(tabella);
	
	public JTableSemplice () {
		getContentPane().add(pannello);
	}
	
	public static void main(String args[]) {
		JTableSemplice frameTabella = new JTableSemplice();
		frameTabella.setSize(600, 250);
		frameTabella.show();	
	}
}	