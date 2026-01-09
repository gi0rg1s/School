import javax.swing.*;
import java.io.File;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.geom.*;
import java.awt.Polygon;
import java.awt.image.*;
import javax.imageio.*;

/**
 @author Alberto Paganuzzi
 Si eredita JPanel.
 Il metodo paintComponent viene richiamato automaticamente durante la creazione (istanza) o ridisegno del JPanel.
 La classe Graphics incapsula metodi per disegnare sui pixel del pannello
 */
 

public class JavaGraphicsEsempio extends JFrame {
	public JavaGraphicsEsempio() {
		setTitle("La classe Graphics nelle Java Swing");
        setSize(1000, 500);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        
        getContentPane().add(new PannelloGrafica1());
        
        getContentPane().add(new PannelloGrafica2());
        
        getContentPane().add(new PannelloGrafica3());
        
        getContentPane().add(new PannelloGrafica4());
        
        getContentPane().add(new PannelloGrafica5());
        
        getContentPane().add(new PannelloGrafica6());
        
        getContentPane().add(new PannelloGrafica7());

        getContentPane().add(new PannelloGrafica8());
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new JavaGraphicsEsempio();
    }
}

class PannelloGrafica1 extends JPanel {
    public PannelloGrafica1() {
        setPreferredSize(new Dimension(200, 150));
        setBackground(Color.magenta);
    }
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.blue);
        g2D.setFont(new Font("Helvetica", Font.BOLD, 24));
        g2D.drawString("Ciao a tutti", 25, 85);
    }
}

class PannelloGrafica2 extends JPanel {
    public PannelloGrafica2() {
        setPreferredSize(new Dimension(200, 150));
        setBackground(Color.yellow);
    }
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Point2D.Double corner1 = new Point2D.Double(10, 50);
        Point2D.Double corner2 = new Point2D.Double(50, 100);
        Point2D.Double corner3 = new Point2D.Double(100, 10);
        Line2D.Double side1 = new Line2D.Double(corner1, corner2);
        Line2D.Double side2 = new Line2D.Double(corner2, corner3);
        Line2D.Double side3 = new Line2D.Double(corner3, corner1);
        g2D.draw(side1);
        g2D.draw(side2);
        g2D.draw(side3);
    }
}

class PannelloGrafica3 extends JPanel {
    public PannelloGrafica3() {
        setPreferredSize(new Dimension(200, 150));
        setBackground(Color.cyan);  // panel color
    }
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Polygon triangle = new Polygon(new int[] {10, 50, 80},
                                       new int[] {10, 150, 100},
                                       3);
        g2D.draw(triangle); // produce triangolo
    }
}

class PannelloGrafica4 extends JPanel {
    public PannelloGrafica4() {
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.pink);
    }
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        Rectangle2D.Double rect1 = new Rectangle2D.Double(25,25,100,100);
        g2D.setColor(Color.black);
        g2D.draw(rect1);

        RoundRectangle2D.Double rect2 = new RoundRectangle2D.Double(50,50,100,100,80,30);
        g2D.setColor(Color.green);
        g2D.fill(rect2);

        Ellipse2D.Double rect3 = new Ellipse2D.Double(75,75,100,80);
        g2D.setColor(Color.blue);
        g2D.fill(rect3);
    }
}

class PannelloGrafica5 extends JPanel {
    public PannelloGrafica5() {
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.white);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        Arc2D.Double arc = 
           new Arc2D.Double(25,25,150,100,0,120,Arc2D.PIE);
        g2D.setColor(Color.black);
        g2D.fill(arc);

	    arc = new Arc2D.Double(25,25,150,100,120,120,Arc2D.PIE);
        g2D.setColor(Color.green);
        g2D.fill(arc);

	    arc = new Arc2D.Double(25,25,150,100,240,120,Arc2D.PIE);
        g2D.setColor(Color.orange);
        g2D.fill(arc);
    }
}
    
class PannelloGrafica6 extends JPanel {
    public PannelloGrafica6() {
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.lightGray);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        Arc2D.Double arc = new Arc2D.Double(25,25,150,100,0,120,Arc2D.CHORD);
        g2D.setColor(Color.black);
        g2D.fill(arc);

	    arc = new Arc2D.Double(25,25,150,100,120,120,Arc2D.CHORD);
        g2D.setColor(Color.green);
        g2D.fill(arc);

	    arc = new Arc2D.Double(25,25,150,100,240,120,Arc2D.CHORD);
        g2D.setColor(Color.orange);
        g2D.fill(arc);
    }
}

class PannelloGrafica7 extends JPanel {
    public PannelloGrafica7() {
        setPreferredSize(new Dimension(250, 250));
        setBackground(Color.green);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
		try {
			img = ImageIO.read(new File("java.png"));
		}
		catch(Exception err){};
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		g.drawImage(img, 0, 0, null);
    }
}

class PannelloGrafica8 extends JPanel {
    int conta;
    public PannelloGrafica8() {
        conta = 0;
        setPreferredSize(new Dimension(250, 250));
        setBackground(Color.white);
        addMouseListener( new java.awt.event.MouseAdapter() {   
            public void mouseClicked(java.awt.event.MouseEvent e) {
                conta++;
                repaint();
            }}
        );
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Helvetica", Font.BOLD, 24));
        g.drawString("Fai click", 2, 20);
        g.setColor(Color.red);
        switch (conta) {
            case 4:g.fillOval(140, 160, 50, 50);
            case 3:g.fillOval(20, 30, 50, 50);
            case 2:g.fillOval(60, 90, 50, 50);
            case 1:g.fillOval(100, 130, 50, 50);
        }
    }
}