import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 @author Alberto Paganuzzi 
 Implementa una JList con dati associati a un'oggetto di DefaultListModel
 Implementa ascoltatore di selezione. Ad ogni click mostra la stringa della riga selezionata
 Un bottone permette di aggiungere una voce alla lista (cioè al modello)
 */

public class JListSemplice extends JFrame implements ListSelectionListener {
    private JList<String> lista;
    private DefaultListModel<String> modelloLista;
    private JButton insButton;
    private JTextField testoSelezione;

    public JListSemplice() {
        super("Dimostrazione semplice di JList");
        modelloLista = new DefaultListModel<String>();

        modelloLista.addElement("Uno");
        modelloLista.addElement("Due");
        modelloLista.addElement("Tre");

        // Crea la lista e la mette in uno scrollPane
        lista = new JList<String>(modelloLista);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setSelectedIndex(0);
  
        // JList ascolta gli eventi
        lista.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(lista);

        insButton = new JButton("Aggiungi");
        insButton.addActionListener(new AscoltatoreBottone());

        testoSelezione = new JTextField(10);
        testoSelezione.setText("prova inserimento");

        // Crea un Pannello che usa FlowLayout (default).
        JPanel buttonPane = new JPanel();
        buttonPane.add(testoSelezione);
        buttonPane.add(insButton);

        getContentPane().add(listScrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    // Ascoltatore per il bottone che aggiunge una voce alla lista
    class AscoltatoreBottone implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //L'utente non ha digitato niente...
            if (testoSelezione.getText().equals("")) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            modelloLista.addElement(testoSelezione.getText());
        }
    }
	
	// Metodo dell'interfaccia ListSelectionListener
	// Viene invocato ogni volta che viene cambiata la selezione
    public void valueChanged(ListSelectionEvent e) {
       if (lista.getSelectedIndex() != -1) {
       	  JOptionPane.showMessageDialog(null, lista.getSelectedValue());
       }
    }

    public static void main(String s[]) {
        JFrame lista = new JListSemplice();
        lista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lista.pack();
        lista.setVisible(true);
    }
}
