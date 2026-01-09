import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JTableXYDemo extends JFrame implements ActionListener {
  JTableXYModello modello = new JTableXYModello();
  private JPanel jPanelBottoni = new JPanel();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JButton jButtonAggiungi = new JButton();
  private JLabel jLabel1 = new JLabel();
  private JScrollPane jScrollPane = new JScrollPane();
  private JTable tabella = new JTable();
  private Vector x = new Vector();
  private Vector y = new Vector();

  public JTableXYDemo() {
    jPanelBottoni.setBackground(new Color(150, 200, 180));
    jTextField2.setPreferredSize(new Dimension(50, 20));
    jTextField1.setPreferredSize(new Dimension(50, 20));
    jLabel2.setText("Y");
    jLabel1.setText("X");
    jButtonAggiungi.setText("Aggiungi Riga");
    jButtonAggiungi.addActionListener(this);

    
    getContentPane().add(jPanelBottoni, BorderLayout.NORTH);
    jPanelBottoni.add(jLabel1, null);
    jPanelBottoni.add(jTextField1, null);
    jPanelBottoni.add(jLabel2, null);
    jPanelBottoni.add(jTextField2, null);
    jPanelBottoni.add(jButtonAggiungi, null);
    getContentPane().add(jScrollPane, BorderLayout.CENTER);
    jScrollPane.getViewport().add(tabella, null);
    
    tabella.setModel(modello);
    popolaVectorTabella();
    modello.setDati(x, y);
  }

  private void popolaVectorTabella() {
    double d = 1.2;
    
    for (int i = 0; i < 10; i++) {
      x.addElement(new Double(d));
      y.addElement(new Double(2 * d * d + 1.2 * d + 2.13));
      d += 0.1;
    }
  }

  public void actionPerformed(ActionEvent e) {
    modello.appendi(Double.valueOf(jTextField1.getText().replace(',', '.')),
                    Double.valueOf(jTextField2.getText().replace(',', '.'))
                   );
  }		

 public static void main(String s[]) {
   JFrame tabella = new JTableXYDemo();
   tabella.setSize(480, 340);
   tabella.pack();
   tabella.setVisible(true);
  }
}
