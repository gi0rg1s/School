import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class BottoneConImmagine extends JFrame implements ActionListener { 
  
  private JButton bottone1;
  private JButton bottone2;
  
  public BottoneConImmagine(String titolo) {
  	super(titolo);
  	// Con JDK 1.5 si possono inserire immagini anche tipo PNG
  	ImageIcon icona1 = new ImageIcon("javaLogo.png"); 
  	bottone1 = new JButton("Cliccami ...", icona1); 
  	//bottone1.setSize(icona1.getIconWidth(), icona1.getIconHeight());
  	bottone1.setHorizontalTextPosition(SwingConstants.CENTER);
  	bottone1.setVerticalTextPosition(SwingConstants.NORTH);
  	bottone1.addActionListener(this);
  	getContentPane().add(bottone1);
  	
  	ImageIcon icona2 = new ImageIcon("javaLogo.jpg"); 
  	bottone2 = new JButton("Cliccami ...", icona2); 
  	bottone2.setHorizontalTextPosition(SwingConstants.CENTER);
  	bottone2.setVerticalTextPosition(SwingConstants.NORTH);
  	bottone2.addActionListener(this);
  	getContentPane().add(bottone2);
  }
  
  public void actionPerformed(ActionEvent e) {
    String controllo = e.getActionCommand();
    if (e.getSource() == bottone1) 
      JOptionPane.showInternalMessageDialog(getContentPane(), "Icona Java 1!", "Informazione", JOptionPane.INFORMATION_MESSAGE);
      
    if (e.getSource() == bottone2) 
      JOptionPane.showInternalMessageDialog(getContentPane(), "Icona Java 2!", "Informazione", JOptionPane.INFORMATION_MESSAGE);  
  }
  
  public static void main(String[] args) { 
    BottoneConImmagine frame = new BottoneConImmagine("Prova immagini jpg e png");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    frame.setLayout(new FlowLayout());
    frame.setSize(1500, 800);
    frame.setVisible(true); 
  } 
} 