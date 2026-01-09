import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 @author Alberto Paganuzzi 
 Implementa una JList con dati associati a un'oggetto di DefaultListModel
 Implementa ascoltatore di selezione. Ad ogni click mostra la stringa della riga selezionata
 2 bottoni permette di aggiungere e cancellare voci alla lista (cioè al modello)
 Diostrazione di alcuni metodi sulla classe DefaultListModel
 */

public class JListCompleta extends JFrame implements ListSelectionListener {
    private JList<String> lista;
    private DefaultListModel<String> modelloLista;

    private static final String addString = "Aggiungi";
    private static final String cancString = "Elimina";
    private JButton cancButton;
    private JButton insButton;
    private JTextField testoSelezione;

    public JListCompleta() {
        super("Dimostrazione JList");

        modelloLista = new DefaultListModel<String>();
        modelloLista.addElement("Prima Voce");
        modelloLista.addElement("Seconda Voce");
        modelloLista.addElement("Terza Voce");
        modelloLista.addElement("Quarta Voce");

        // Crea la lista e la mette in uno scrollPane
        lista = new JList<String>(modelloLista);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setSelectedIndex(0);
        lista.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(lista);

        insButton = new JButton(addString);
        insButton.setActionCommand(addString);
        insButton.addActionListener(new AggiungiListener());

        cancButton = new JButton(cancString);
        cancButton.setActionCommand(cancString);
        cancButton.addActionListener(new EliminaListener());

        testoSelezione = new JTextField(10);
        testoSelezione.addActionListener(new AggiungiListener());

        // Crea un Pannello che usa FlowLayout (default).
        JPanel buttonPane = new JPanel();
        buttonPane.add(testoSelezione);
        buttonPane.add(insButton);
        buttonPane.add(cancButton);

        getContentPane().add(listScrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    class EliminaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Questo metodo può essere invocato solo per una selezione valida
            int index = lista.getSelectedIndex();
            modelloLista.remove(index);

            int size = modelloLista.getSize();

            if (size == 0) {  //Non si elimina niente.
               cancButton.setEnabled(false);
            } 
            else { //Aggiusta la selezione.
                if (index == modelloLista.getSize())//Rimuovi voce in ultima posizione
                   index--;
                lista.setSelectedIndex(index); //o seleziona stessa voce dell'indice
            }
        }
    }

    // 
    class AggiungiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //L'utente non ha digitato niente...
            if (testoSelezione.getText().equals("")) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            int index = lista.getSelectedIndex();
            int size = modelloLista.getSize();

            // Se non c'è nessuna selezione o diversa dall'ultima voce aggiungi nuova voce in ultima posizione con selezione.
            if (index == -1 || (index+1 == size)) {
                modelloLista.addElement(testoSelezione.getText());
                lista.setSelectedIndex(size);

            //Altrimenti inserisci una nuova voce dopo la corrente,
            } else {
                modelloLista.insertElementAt(testoSelezione.getText(), index+1);
                lista.setSelectedIndex(index+1);
            }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
    	// ritorna se l'evento è uno di una serie su un dato in via di modifica. Se false si evitano casini sui dati
        if (e.getValueIsAdjusting() == false) {

            if (lista.getSelectedIndex() == -1) {
            //No selezione, disabilita Elimina.
                cancButton.setEnabled(false);
                testoSelezione.setText("");

            } else {
            //Con selezione abilita.
                cancButton.setEnabled(true);
                String nome = lista.getSelectedValue().toString();
                testoSelezione.setText(nome);
            }
        }
    }

    public static void main(String s[]) {
        JFrame frame = new JListCompleta();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
