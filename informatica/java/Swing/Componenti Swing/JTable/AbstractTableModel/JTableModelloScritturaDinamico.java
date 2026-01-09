import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;
import java.util.*;

public class JTableModelloScritturaDinamico extends JFrame {
	
	public class Modello extends AbstractTableModel {
		private String[] nomiColonne = {"Colonna uno", "Colonna due", "Colonna tre"};
		
		public double[] arrayColonne = new double[3];
		
		public Vector dati = new Vector();
		
		public void aggiungiRiga(double[] colonna) {
			dati.add(colonna);
			this.fireTableDataChanged();
		}

		public int getColumnCount() {
			return nomiColonne.length;
		}

		public int getRowCount() {
			return dati.size();
		}
		
		public Object getValueAt(int row, int col) {
			return new Double( ((double[]) (dati.get(row)))[col]);
		}
		
		public String getColumnName(int col) {
			return nomiColonne[col];
		}
	};

	Modello modello = new Modello();
	JTable tabella = new JTable(modello);
	JScrollPane pannello = new JScrollPane(tabella);
	
	public JTableModelloScritturaDinamico () {
		getContentPane().add(pannello);
	}
	
	public static void main(String args[]) {
		JTableModelloScritturaDinamico frameTabella = new JTableModelloScritturaDinamico();
		frameTabella.setSize(600, 240);
		frameTabella.show();	
		double []a = {12.4, 45.6, 6.7};
		frameTabella.modello.aggiungiRiga(a);
		double []b = {0.4, 5.56, 7};
		frameTabella.modello.aggiungiRiga(b);
		double []c = {4.4, 56, 0.7};
		frameTabella.modello.aggiungiRiga(c);
	}
}	