/*
Layout Manager

Eseguono automaticamente dimensioni e disposizione delle componenti all'interno di un contenitore. 
Ogni contenitore (JPanel, JFrame) ha un layout manager.
I layout manager sono classi che implementano l'interfaccia Java AWT "LayoutManager".
Si potrebbe lavorare senza layout manager (setLayoutManager=null), ma dovendo fornire posizione assoluta (coordinate) 
di ogni componente all'interno del contenitore, con tutti i problemi legati al ridimensionamento causato dall'utente
o per l'adattamento ad uno schermo diverso a quello usato per la progettazione.

La super-classe Container ha i seguenti metodi, che percio' sono ereditati da tutte le classi di contenitori:

void setLayout(LayoutManager layout) -- stabilisce il layout manager
Component add(Component c) -- aggiunge un componente nel contenitore
Component add(Component c, int posizione) -- aggiunge un componente nel contenitore nella posizione specificata rispetto al layout manager
A seconda del tipo di layout manager presente, va usata l'una o l'altra forma del metodo "add".

FlowLayout: tutti in fila con andata a capo
BorderLayout: centro + 4 punti cardinali
BoxLayout: allineamento lungo l'asse scelto (X o Y)
GridLayout: griglia con righe e colonne, tutte le celle di uguale misura
GridBagLayout: griglia con righe e colonne dove un componente puo' occupare piu' righe e/o piu' colonne (non lo vediamo)
CardLayout: permette a due componenti di coesistere su un'area essendo visualizzati in alternativa uno all'altro (non lo vediamo)

Esempio che mostra vari tipi di layout.
Una finestra top-level (JFrame) con 5 etichette con sfondi di colori diversi.
Secondo il tipo di layout manager i 5 componenti vengono messi:
- in fila se FlowLayout
- a griglia 2x3 (con un posto libero) se GridLayout
- al centro e 4 punti cardinali se BorderLayout
- uno sotto l'altro se BoxLayout lungo l'asse Y con allineamento sx default

https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html

* @author zAlberto
*/

import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;

public class LayoutManagerDemo {
  static final String msg1 = "Bisogna specificare un layout.";
  static final String msg2 = "Layout possibili: Flow Grid Border";
  static final String msg3 = "Layout specificato non valido";

  public static void creaFinestra(String name)
  {
    JFrame f; // la finestra principale
    /* crea la finestra e le assegna un layout manager */
    f = new JFrame(name + " Layout");

    /* crea 5 controlli */
    JLabel  label = new JLabel("Etichetta");
    label.setAlignmentX(Label.CENTER);
    JButton button = new JButton("Bottone");
    button.setFont(new Font("courier new", Font.PLAIN, 25));
    JTextField textField = new JTextField("Campo testo");
    JCheckBox checkBox = new JCheckBox("Flag");
    JRadioButton radioButton = new JRadioButton("Radio");

    /* colora lo sfondo di colori diversi, bordo per label */
    label.setBorder(BorderFactory.createLineBorder(Color.black));
    button.setBackground(new Color(210,210,20));
    textField.setBackground(new Color(250,50,255));
    checkBox.setBackground(new Color(120,230,200));
    radioButton.setBackground(new Color(10,180,255));

    switch(name) {
      case("Border"):
        f.setLayout(new BorderLayout());/* Border necessita posizione */
        f.add(label, BorderLayout.CENTER);
        f.add(button, BorderLayout.NORTH);
        f.add(textField, BorderLayout.SOUTH);
        f.add(checkBox, BorderLayout.EAST);
        f.add(radioButton, BorderLayout.WEST);
        f.setLocation(20, 20);
        break;

      case("Grid"):
        f.setLayout(new GridLayout(2, 3, 3, 3));
        f.add(label);
        f.add(button);
        f.add(textField);
        f.add(checkBox);
        f.add(radioButton);
        f.setLocation(320, 80);
        break;

      case("Flow"):
        f.setLayout(new FlowLayout());
        f.add(label);
        f.add(button);
        f.add(textField);
        f.add(checkBox);
        f.add(radioButton);
        f.setLocation(640, 300);
        break;

      case("Box"):
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
        f.add(label);
        // senza setAlignmentX allinea gli altri componenti in modo strano
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        f.add(button);
        f.add(textField);
        f.add(checkBox);
        f.add(radioButton);
        f.setLocation(250, 220);
        break;
    }

    /* impacchetta e rende visibile la finestra */
    f.pack();
    f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    f.setVisible(true);
  }

  public static void main(String args[]) {
    creaFinestra("Flow");
    creaFinestra("Grid");
    creaFinestra("Border");
    creaFinestra("Box");
  }
}