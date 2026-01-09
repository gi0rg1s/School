/**
 * BottoneConImmagine
 * Mostra come istanziare ImageIcon con immagini jpg,gif e png per applicarle come sfondi di JButton
 * @author ITIS
 */
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*; 

public class BottoneConImmagine extends JFrame implements ActionListener { 
  
  public JButton bottone1;
  public JButton bottone2;
  public JButton bottone3;
  
  public BottoneConImmagine() {
	// getContentePane() restituisce il container del JFrame per poterlo popolare di controlli
	// il Layout default è BorderLayout che eventualmente si può modificare così:
	getContentPane().setLayout(new FlowLayout());
	  
  	ImageIcon icona1 = new ImageIcon("java.png"); 
  	bottone1 = new JButton("png", icona1); 
  	bottone1.addActionListener(this);
  	getContentPane().add(bottone1);
  	
  	ImageIcon icona2 = new ImageIcon("java.jpg"); 
  	bottone2 = new JButton("jpg", icona2); 
  	bottone2.addActionListener(this);
  	getContentPane().add(bottone2);
  	
  	ImageIcon icona3 = new ImageIcon("java.gif"); 
  	bottone3 = new JButton("gif", icona3); 
  	bottone3.addActionListener(this);
  	getContentPane().add(bottone3);
  }
  
  public void actionPerformed(ActionEvent e) {
	// 2 forme diverse per intercettare il controllo (bottone) che ha sollevato l'evento
    String controllo = e.getActionCommand();
    if (controllo.equals("png")) 
      JOptionPane.showMessageDialog(getContentPane(), "Immagine png", "Informazione", JOptionPane.INFORMATION_MESSAGE);
    if (e.getSource() == bottone2) 
      JOptionPane.showMessageDialog(getContentPane(), "Immagine jpg", "Informazione", JOptionPane.INFORMATION_MESSAGE);
    if (e.getSource() == bottone3) 
      JOptionPane.showMessageDialog(getContentPane(), "Immagine gif", "Informazione", JOptionPane.INFORMATION_MESSAGE);
  }
  
  public static void main(String[] args) {
    BottoneConImmagine frame = new BottoneConImmagine(); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Bottoni con immagini. Prova a cliccare...");
    frame.pack();
    frame.setVisible(true); 
  } 
} 