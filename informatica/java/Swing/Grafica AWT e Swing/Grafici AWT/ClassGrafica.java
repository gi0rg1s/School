import java.awt.*;
import java.awt.event.*;

class ClassGrafica extends Frame implements ActionListener {

  Panel comandi = new Panel();
  public DisegnaGrafico p = new DisegnaGrafico();
  public TextField a = new TextField(5);
  public TextField b = new TextField(5);
  public TextField c = new TextField(5);
  Button disegna = new Button("Disegna");

  public ClassGrafica() {
    super("Disegna Grafico Parabola");

    comandi.add(new Label("A= ", Label.RIGHT));
    comandi.add(a);

    comandi.add(new Label("B= ", Label.RIGHT));
    comandi.add(b);

    comandi.add(new Label("C= ", Label.RIGHT));
    comandi.add(c);

    comandi.add(disegna);
    comandi.setBackground(Color.white);

    setBackground(Color.lightGray);
    setLayout(new BorderLayout());

    add(comandi, "North");

    add(p, "Center");
    addWindowListener(new GestoreFinestra());
    disegna.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    String bottone = e.getActionCommand();
    String numero;
    double ValA, ValB, ValC;
    Parabola parab;

    if (bottone.equals("Disegna")) {
      try {
        numero = a.getText();
        ValA = Double.valueOf(numero).doubleValue();

        numero = b.getText();
        ValB = Double.valueOf(numero).doubleValue();

        numero = c.getText();
        ValC = Double.valueOf(numero).doubleValue();
      }
      catch (Exception exc) {
        parab = null;
        p.setParabola(parab);
        return;
      }

      parab = new Parabola(ValA, ValB, ValC);
      p.setParabola(parab);
    }
  }

}

