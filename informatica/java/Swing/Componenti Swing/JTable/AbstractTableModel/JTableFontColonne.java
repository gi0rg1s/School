import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;

public class JTableFontColonne extends JFrame {
	
	class CellRenderer extends DefaultTableCellRenderer { 
        CellRenderer() { 
            super(); 
        } 

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 

            if(value == null) 
                return this; 

            if (column == 0) {
            	setFont(new Font("sansserif", Font.BOLD, 28)); 
            	//setBackground(new Color(344));
            	this.set
        	}

            return this; 
        } 
    } 

	public class Modello extends AbstractTableModel {
		private String[] nomiColonne = {"Nomi", "voto 1", "voto 2", "voto 3"};
		private String[] arrayRigheNomi = {"Pippo", "Pluto", "Paperino"};
		private double[][] arrayRigheVoti = {
												{5, 7, 5.5},
												{10, 6, 7},
												{8, 5, 9},	
											};
		public int getColumnCount() {
			return nomiColonne.length;
		}

		public int getRowCount() {
			return arrayRigheNomi.length;
		}
		
		public Object getValueAt(int row, int col) {
			if (col == 0)
				return arrayRigheNomi[row];
			else
			  	return Double.valueOf(arrayRigheVoti[row][col-1]);
		}
		
		public String getColumnName(int col) {
			return nomiColonne[col];
		}
	};

	Modello modello = new Modello();
	JTable tabella = new JTable(modello);
	JScrollPane pannello = new JScrollPane(tabella);
	
	public JTableFontColonne () {
		getContentPane().add(pannello);
		tabella.setDefaultRenderer(Object.class, new CellRenderer());
		tabella.setRowHeight(28); 
	}
	
	public static void main(String args[]) {
		JTableFontColonne frameTabella = new JTableFontColonne();
		frameTabella.setSize(600, 200);
		frameTabella.setVisible(true);	
	}
}	