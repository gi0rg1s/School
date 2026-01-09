import java.awt.*;

class DisegnaGrafico extends Canvas {

  private Parabola parab = null;

  private final double XMax = 20.0;
  private final double XMin = -20.0;
  private final double YMax = 18.0;
  private double YMin;
  private double Unita;
  private int w, h;

  public void paint (Graphics g) {
    // Calcola dimensioni area disegno
    w = getSize().width;
    h = getSize().height;

    // Calcola YMin e unità di misura
    Unita = (double) w / (XMax - XMin);
    YMin = YMax - ((double) h / Unita);

    // Disegna assi
    disegnaAssi(g);

    // Disegna parabola
    if (parab != null) {
      disegnaParabola(g);
    }

  }

  // Riceve come parametro le coordinate di un punto reale e
  // lo trasforma in un punto schermo

  public Point trasforma (double x, double y) {
    Point schermo = new Point();
    schermo.x = (int) Math.round((x - XMin) * Unita);
    schermo.y = (int) Math.round((YMax - y) * Unita);
    return schermo;
  }

  public void disegnaAssi(Graphics g) {
    Point p1, p2;

    g.setColor(Color.black);
    g.drawRect(0, 0, w-1, h-1);

    p1 = trasforma(XMin, 0);
    p2 = trasforma(XMax, 0);
    g.drawLine(p1.x, p1.y, p2.x, p2.y);

    p1 = trasforma(0, YMin);
    p2 = trasforma(0, YMax);
    g.drawLine(p1.x, p1.y, p2.x, p2.y);
  }

  public void disegnaParabola(Graphics g) {
    double incremento = 0.01;
    double x;
    Point p1, p2;

    g.setColor(Color.blue);

    p1 = trasforma(XMin, parab.valuta(XMin));

    for(x = XMin + incremento; x <= XMax; x += incremento) {
      p2 = trasforma(x, parab.valuta(x));
      // disegna solo se i punti sono compresi nell'area grafica
      if ((p1.y > 0 ) && (p1.y < h) && (p2.y > 0) && (p2.y < h)) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
      }
      p1.x = p2.x;
      p1.y = p2.y;
    }
  }


  // imposta nuova parabola e ridisegna il grafico
  
  public void setParabola(Parabola parab) {
    this.parab = parab;
    repaint();
  }
}