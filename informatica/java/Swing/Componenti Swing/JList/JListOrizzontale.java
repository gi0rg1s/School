import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListOrizzontale extends JFrame implements ListSelectionListener {
  private JList<String> lista;
  private DefaultListModel<String> modelloLista;

  public JListOrizzontale(){
    modelloLista = new DefaultListModel<>();
    lista = new JList<>(modelloLista);
    for (int i = 0; i < 50; i++) {
      modelloLista.addElement("Num " + i);
    }

    lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    lista.setVisibleRowCount(-1);
    lista.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    lista.addListSelectionListener(this);
    setTitle("Dimostrazione JList orizzontale");
    add(new JScrollPane(lista), BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(400, 200);
    setVisible(true);
  }

  public void valueChanged(ListSelectionEvent e) {
    if (lista.getSelectedIndex() != -1) {
      JOptionPane.showMessageDialog(null, lista.getSelectedValue());
    }
  }

  public static void main(String[] args) {
    new JListOrizzontale();
  }
}