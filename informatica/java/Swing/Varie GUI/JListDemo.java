import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class JListDemo extends JFrame implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String inserisciString = "inserisci";
    private static final String cancellaString = "cancella";
    private JButton cancellaButton;
    private JTextField edit;

    public JListDemo() {
        super("ListDemo");

        listModel = new DefaultListModel();
        listModel.addElement("Pippo");
        listModel.addElement("Topolino");
        listModel.addElement("Eta Beta");
        listModel.addElement("Paperino");

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton inserisciButton = new JButton(inserisciString);
        inserisciButton.setActionCommand(inserisciString);
        inserisciButton.addActionListener(new inserisciListener());

        cancellaButton = new JButton(cancellaString);
        cancellaButton.setActionCommand(cancellaString);
        cancellaButton.addActionListener(new cancellaListener());

        edit = new JTextField(10);
        edit.addActionListener(new inserisciListener());
        String name = listModel.getElementAt(list.getSelectedIndex()).toString();
        edit.setText(name);

        JPanel buttonPane = new JPanel();
        buttonPane.add(edit);
        buttonPane.add(inserisciButton);
        buttonPane.add(cancellaButton);

        Container contentPane = getContentPane();
        contentPane.add(listScrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
    }

    class cancellaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // il metodo controlla se la voce selezionata da cancellare è valida
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) {
            //Nessuna voce da cancellare. Disabilita inserisci.
                cancellaButton.setEnabled(false);

            } else {
            // Aggiusta la selezione...
                if (index == listModel.getSize()) // se è l'ultima voce
                    index--;
                list.setSelectedIndex(index);   //riposiziona indice di selezione
            }
        }
    }

    // L'ascoltatore è condiviso dal text field e il bottone cancella
    class inserisciListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // Nessun carattere digitato
            if (edit.getText().equals("")) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            int index = list.getSelectedIndex();
            int size = listModel.getSize();

            // Se non c'è nessuna selezione o è selezionata l'ultima posizione
            // aggiungi in ultima posizione e selezionala.
            if (index == -1 || (index+1 == size)) {
                listModel.addElement(edit.getText());
                list.setSelectedIndex(size);

            //Altrimenti inserisci in posizione successiva alla corrente
            } else {
                listModel.insertElementAt(edit.getText(), index+1);
                list.setSelectedIndex(index+1);
            }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
            //No selezione, disabilita bottone aggiungi
                cancellaButton.setEnabled(false);
                edit.setText("");

            } else {
            //Con Selezione, aggiorna text field.
                cancellaButton.setEnabled(true);
                String name = list.getSelectedValue().toString();
                edit.setText(name);
            }
        }
    }

    public static void main(String s[]) {
        JFrame frame = new JListDemo();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
