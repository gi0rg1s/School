import javax.swing.*;
import java.awt.*;

public class ImageJListExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageJListExample().createAndShowGUI();
        });
    }

    // Metodo per creare e mostrare l'interfaccia grafica
    public void createAndShowGUI() {
        // Dati della lista
        ListItem[] data = {
            new ListItem("Cane", new ImageIcon("dog.png")),
            new ListItem("Gatto", new ImageIcon("cat.png")),
            new ListItem("Uccello", new ImageIcon("bird.png"))
        };

        // Creazione della JList
        JList<ListItem> list = new JList<>(data);
        list.setCellRenderer(new ListItemRenderer());
        list.setLayoutOrientation( JList.HORIZONTAL_WRAP );

        // Creazione della finestra
        JFrame frame = new JFrame("JList con Immagini");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(list));
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    // Classe per rappresentare un elemento con testo e immagine
    class ListItem {
        private final String text;
        private final Icon icon;

        public ListItem(String text, Icon icon) {
            this.text = text;
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public Icon getIcon() {
            return icon;
        }
    }

    // Renderer personalizzato per la JList
    class ListItemRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                // Configurazione del renderer di base
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof ListItem) {
                    ListItem item = (ListItem) value;
                    label.setText(item.getText());
                    label.setIcon(item.getIcon());
                }

                return label;
        }
    }
}
