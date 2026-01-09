import java.awt.Graphics;
import java.awt.Point;
import javax.swing.*;
/**
 @author Alberto Paganuzzi
 Eredita JPanel.
 Il metodo paintComponent viene richiamato automaticamente durante la creazione (istanza) o ridisegno del JPanel.
 La classe Graphics incapsula metodi per disegnare sui pixel del pannello
 */

class DisegnaGrafico extends JPanel {

  private Parabola parab = null;

  private final double xMax = 20.0;
  private final double xMin = -20.0;
  private final double yMax = 18.0;
  private double yMin;
  private double unita;
  private int w, h;

  public void paintComponent (Graphics g) {
  	super.paintComponent(g);
    // Calcola dimensioni area disegno
    w = getSize().width;
    h = getSize().height;

    // Calcola YMin e unità di misura
    unita = (double) w / (xMax - xMin);
    yMin = yMax - ((double) h / unita);

    // Disegna assi
    disegnaAssi(g);

    // Disegna parabola
    if (parab != null) {
      disegnaParabola(g);
    }
  }

  // Riceve come parametro le coordinate di un punto reale e
  // lo trasforma in un punto schermo

  private Point trasforma (double x, double y) {
    Point schermo = new Point();
    schermo.x = (int) Math.round((x - xMin) * unita);
    schermo.y = (int) Math.round((yMax - y) * unita);
    return schermo;
  }

  private void disegnaAssi(Graphics g) {
    Point p1, p2;

    g.setColor(java.awt.Color.black);
    g.drawRect(0, 0, w-1, h-1);

    p1 = trasforma(xMin, 0);
    p2 = trasforma(xMax, 0);
    g.drawLine(p1.x, p1.y, p2.x, p2.y);

    p1 = trasforma(0, yMin);
    p2 = trasforma(0, yMax);
    g.drawLine(p1.x, p1.y, p2.x, p2.y);
  }

  private void disegnaParabola(Graphics g) {
    double incremento = 0.01;
    double x;
    Point p1, p2;

    g.setColor(java.awt.Color.blue);

    p1 = trasforma(xMin, parab.valuta(xMin));

    for(x = xMin + incremento; x <= xMax; x += incremento) {
      p2 = trasforma(x, parab.valuta(x));
      // disegna solo se i punti sono compresi nell'area grafica
      if ((p1.y > 0 ) && (p1.y < h) && (p2.y > 0) && (p2.y < h)) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
      }
      p1.x = p2.x;
      p1.y = p2.y;
    }
  }

  /** Imposta una nuova parabola e ridisegna il grafico */
  public void setParabola(Parabola parab) {
    this.parab = parab;
    repaint();
  }
}