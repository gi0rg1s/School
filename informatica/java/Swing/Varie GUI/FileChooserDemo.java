import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

// filtro per gif/jpg/jpeg files
class FiltroImmagini extends FileFilter {
  
  // Metodo accept ritorna true se viene selezionato un dato file
  public boolean accept(File f) {
    if (f.isDirectory())
      return true;
   
    String path = f.getPath().toLowerCase();
    
    if (path.endsWith(".gif"))
      return true;
    
    if (path.endsWith(".jpg"))
      return true;
    
    if (path.endsWith(".jpeg"))
      return true;
    
    return false;
  }
  
  // ritorna la descrizione dei files
  public String getDescription() {
    return "Image file (*.gif,*.jpg,*.jpeg)";
  }
}

public class FileChooserDemo {

  public static void main(String args[]) {
    JFrame frame = new JFrame("FileChooser demo");
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    // etichetta in cui attaccare l'immagine
    final JLabel label = new JLabel("", SwingConstants.CENTER);

    JPanel panel1 = new JPanel();
    // FileChooser parte dalla directory corrente
    String cwd = System.getProperty("user.dir");
    final JFileChooser fc = new JFileChooser(cwd);
    fc.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // se premuto OK setta l'icona della label come immagine corrente
        String state = (String)e.getActionCommand();
        if (!state.equals(JFileChooser.APPROVE_SELECTION))
          return;
        File f = fc.getSelectedFile();
        if (f == null || !f.isFile())
          return;
        ImageIcon icon = new ImageIcon(f.getPath());
        label.setIcon(icon);
      }
    });
    // setta il filtro per FileChooser
    fc.setFileFilter(new FiltroImmagini());
    panel1.add(fc);

    JPanel panel2 = new JPanel();
    label.setPreferredSize(new Dimension(500, 300));
    panel2.add(label);

    frame.getContentPane().add("North", panel1);
    frame.getContentPane().add("South", panel2);
    frame.pack();
    frame.setVisible(true);
  }
}
