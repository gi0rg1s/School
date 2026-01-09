import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 @author Alberto Paganuzzi 
 Implementa una semplice JList con array di stringhe passate al costruttore
 */

public class JListSemplicissima {
  public static void main(String args[]) {
    String labels[] = { "uno", "due", "tre"};
    JFrame frame = new JFrame("JList Semplicissima");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JList<String> jlist = new JList<String>(labels);
    JScrollPane scrollPane1 = new JScrollPane(jlist);
    frame.getContentPane().add(scrollPane1, BorderLayout.CENTER);

    frame.setSize(250, 200);
    frame.setVisible(true);
  }
}
