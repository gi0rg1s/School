import java.text.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class JTableXYModello extends AbstractTableModel {

  final String[] nomiColonne= {" X ", " Y "};
  Vector dati = null;

  public String getColumnName(int col) {
    return nomiColonne[col].toString();
  }

  public int getColumnCount() {
    return nomiColonne.length;
  }

  public void setDati(Vector dati) {
    this.dati = dati;
    fireTableChanged(new TableModelEvent(this));
  }

  public int getRowCount() {
    if (dati == null) 
      return 0;
    else 
      return dati.size();
  }

  public Object getValueAt(int row, int col) {
    DecimalFormat d = new DecimalFormat("#.000");
    return d.format(( ((Object []) (dati.elementAt(row)))[col]));
  }

  public boolean isCellEditable(int row, int col) {
    return true;
  }

  public void setValueAt(Object value, int row, int col) {
    Object d[] = (Object[]) dati.elementAt(row);
    d[col] = Double.valueOf(value.toString().replace(',', '.'));
    dati.setElementAt(d, row);
    fireTableCellUpdated(row, col);
  }

  public void appendi(Double x, Double y) {
    Object d[] = new Object[2];
    d[0] = x;
    d[1] = y;
    dati.addElement(d);
    fireTableChanged(new TableModelEvent(this));
  }

}