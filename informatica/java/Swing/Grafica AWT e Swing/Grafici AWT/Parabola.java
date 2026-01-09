class Parabola {
  private double a, b, c;

  public Parabola (double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public double valuta(double x) {
    double y;
    y = a*x*x + b*x + c;
    return y;
  }
} 