import java.awt.*;
import javax.swing.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;

public class JTableSemplice extends JFrame {

  public JTableSemplice() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    DefaultTableModel modello = new DefaultTableModel(
                              new String[][] {
                                {"1", "2", "3"},
                                {"4", "5", "6"} },
                              new String[] {"1ª colonna", "2ª colonna", "3ª colonna"});

    JTable tabella = new JTable(modello);

    JScrollPane pannello = new JScrollPane(tabella);
    getContentPane().add(pannello, BorderLayout.CENTER);
  }

  public static void main(String args[]) {
	JTableSemplice frameTabella = new JTableSemplice();
	frameTabella.setSize(600, 250);
	frameTabella.setVisible(true);
  }
}