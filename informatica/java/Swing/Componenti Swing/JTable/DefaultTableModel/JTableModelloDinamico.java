import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class JTableModelloDinamico extends JFrame {

  DefaultTableModel modello = null;
    private static final String addString = "Aggiungi";
    private static final String cancString = "Elimina";
    private JButton cancButton;
    private JButton insButton;
    private JTable tabella;
    private JScrollPane pannello;
  
  public JTableModelloDinamico() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    modello = new DefaultTableModel(
                              new String[][] {
                                {"1", "2", "3"},
                                {"4", "5", "6"} },
                              new String[] {"1ª colonna", "2ª colonna", "3ª colonna"});

    tabella = new JTable(modello);

    pannello = new JScrollPane(tabella);
    getContentPane().add(pannello, BorderLayout.CENTER);
    
    insButton = new JButton(addString);
    insButton.setActionCommand(addString);
    insButton.addActionListener(new AggiungiListener());

    cancButton = new JButton(cancString);
    cancButton.setActionCommand(cancString);
    cancButton.addActionListener(new EliminaListener());

    JPanel buttonPane = new JPanel();
    buttonPane.add(insButton);
    buttonPane.add(cancButton);
    
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
  }

  class EliminaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Questo metodo può essere invocato solo per una selezione valida
            int index = tabella.getSelectedRow();
            modello.removeRow(index);

            int size = modello.getRowCount();

            if (size == 0) {  // Non si elimina niente.
               cancButton.setEnabled(false);
            } 
            else { // Aggiusta la selezione.
                if (index == modello.getRowCount()) // Se rimossa voce in ultima posizione
                   index--; // seleziona la precedente
                tabella.setRowSelectionInterval(index, index); // o seleziona stessa voce dell'indice
            }
        }
    }

    //ascoltatore del bottone aggiungi
    class AggiungiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            modello.addRow(new Object[] {"ultima", "riga", "aggiunta"});
            tabella.setRowSelectionInterval(modello.getRowCount() - 1, modello.getRowCount() - 1);
        }
    }

  public static void main(String args[]) {
	JTableModelloDinamico frameTabella = new JTableModelloDinamico();
	frameTabella.setSize(500, 250);
	frameTabella.setVisible(true);
  }
}