import java.text.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class JTableXYModello extends AbstractTableModel {

  final String[] nomiColonne= {" X ", " Y "};
  private Vector x = new Vector();
  private Vector y = new Vector();

  public String getColumnName(int col) {
    return nomiColonne[col].toString();
  }

  public int getColumnCount() {
    return nomiColonne.length;
  }

  public void setDati(Vector x, Vector y) {
    this.x = x;
    this.y = y;
    fireTableChanged(new TableModelEvent(this));
  }

  public int getRowCount() {
    if (x == null) 
      return 0;
    else 
      return x.size();
  }

  public Object getValueAt(int row, int col) {
    DecimalFormat d = new DecimalFormat("#.000");
    Object val = null;
    switch (col) {
    	case 0:
    	  val = d.format(x.elementAt(row));
    	  break;
    	case 1:
    	  val = d.format(y.elementAt(row));
    	  break;
    }
    return val;
  }

  public boolean isCellEditable(int row, int col) {
    return true;
  }

  public void setValueAt(Object value, int row, int col) {
  	Double d = Double.valueOf(value.toString().replace(',', '.'));
  	switch (col) {
    	case 0:
    	  x.setElementAt(d, row);
    	  break;
    	case 1:
    	  y.setElementAt(d, row);
    	  break;
    }
    fireTableCellUpdated(row, col);
  }

  public void appendi(Double x, Double y) {
    this.x.addElement(x);
    this.y.addElement(y);
    fireTableChanged(new TableModelEvent(this));
  }

}