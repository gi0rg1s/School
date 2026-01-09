import javax.swing.*;
import java.awt.event.*;
/**
 @author Alberto Paganuzzi
 Implementa interfaccia JFrame per inserire un JPanel centrale su cui disegnare grafici con classe Graphics
 */
class ClassGrafica extends JFrame implements ActionListener {

  private JPanel pannelloComandi = new JPanel();
  private DisegnaGrafico disegnaGrafico = new DisegnaGrafico();
  private JTextField a = new JTextField(5);
  private JTextField b = new JTextField(5);
  private JTextField c = new JTextField(5);
  private JButton bottoneDisegna = new JButton("Disegna");

  public ClassGrafica() {
    super("Disegna Grafico Parabola");

    pannelloComandi.add(new JLabel("A= ", JLabel.RIGHT));
    pannelloComandi.add(a);

    pannelloComandi.add(new JLabel("B= ", JLabel.RIGHT));
    pannelloComandi.add(b);

    pannelloComandi.add(new JLabel("C= ", JLabel.RIGHT));
    pannelloComandi.add(c);

    pannelloComandi.add(bottoneDisegna);
    pannelloComandi.setBackground(java.awt.Color.white);

    setBackground(java.awt.Color.lightGray);

    add(pannelloComandi, "North");

    add(disegnaGrafico, "Center");
    bottoneDisegna.addActionListener(this);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == bottoneDisegna) {
    	Parabola p = null;
		try {
			p = new Parabola (
				Double.valueOf(a.getText()).doubleValue(),
				Double.valueOf(b.getText()).doubleValue(),
				Double.valueOf(c.getText()).doubleValue()
			);
		}
		catch (Exception exc) {}
		disegnaGrafico.setParabola(p);
    }
  }

}

