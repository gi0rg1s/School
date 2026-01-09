import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;

public class JTableModelloScrittura extends JFrame {
	
	TableModel modello = new AbstractTableModel() {
		private String nomiColonne[] = {"Colonna uno", "Colonna due", "Colonna tre"};
		public double dati[][] = { 	{0.2, 12.34, 4.5},
									{0.4, 5.6, 8.9},
									{12.45, 67.8, 4.5}
								};

		public int getColumnCount() {
			return nomiColonne.length;
		}

		public int getRowCount() {
			return 3;
		}
		
		public boolean isCellEditable(int row, int col) {
			return true;
		}
  
		public void setValueAt(Object value, int row, int col) {
			dati[row][col] = Double.parseDouble(value.toString());
   		}
		
		public Object getValueAt(int row, int col) {
			return new Double(dati[row][col]);
		}
		
		public String getColumnName(int col) {
			return nomiColonne[col];
		}
	};

	JTable tabella = new JTable(modello);
	JScrollPane pannello = new JScrollPane(tabella);
	
	public JTableModelloScrittura () {
		getContentPane().add(pannello);
	}
	
	public static void main(String args[]) {
		JTableModelloScrittura frameTabella = new JTableModelloScrittura();
		frameTabella.setSize(600, 240);
		frameTabella.show();	
	}
}	