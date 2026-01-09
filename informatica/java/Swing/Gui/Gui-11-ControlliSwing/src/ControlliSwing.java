import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javafx.scene.control.CheckBox;

import javax.swing.*; 

@SuppressWarnings("serial")
public class ControlliSwing extends JFrame implements ActionListener, ItemListener { 
  
	/** Bottone esci */
	private JButton bottone;
	/** Label che contiene il testo selezionato */
	private JLabel label;	
	/** Combo con lista animali */
	private JComboBox<String> comboAnimali;
	/** Nomi degli animali */
	private String[] stringheAnimali = { "Uccello", "Gatto", "Cane", "Coniglio", "Maiale" };
	/** Controllo per contenere radioButton che si escludono a vicenda */
	private ButtonGroup radioGroup;
	/** Radio Button di prova */
	private JRadioButton radioButton1, radioButton2, radioButton3;
	/** CheckBox */
	private JCheckBox checkBox;
	
	public ControlliSwing() {
		super();
		creaGUI();
	}
  
	private void creaGUI() {
	  	// Pannello a nord
		JPanel pannelloNord = new JPanel();
	  	pannelloNord.setBackground(Color.ORANGE);
	  	this.add(pannelloNord, BorderLayout.NORTH);
	  	
		bottone = new JButton("Chiudi"); 
	  	bottone.addActionListener(this);
	  	pannelloNord.add(bottone);

	  	// Pannello al centro
	  	JPanel pannelloCentro = new JPanel();
	  	pannelloCentro.setBackground(Color.CYAN);
	  	
		// Istanzia la comboBox e associa l'array di stringhe
        comboAnimali = new JComboBox<String>(stringheAnimali);
        comboAnimali.setPreferredSize(new Dimension(150, 40));
        comboAnimali.addActionListener(this);
        comboAnimali.addItemListener(this);
        pannelloCentro.add(comboAnimali);
        this.add(pannelloCentro, BorderLayout.CENTER);
        
        // Istanzia la label
        label = new JLabel("Etichetta");
        // Configura il font
        label.setFont(new Font("Helvetica", Font.BOLD, 16));
        // Configura il colore RGB + Trasparenza
        label.setForeground(new Color(00, 00, 255, 180));
        // JLabel si adatta automaticamente al contenuto. 
        // Per verificarlo ho forzato la lunghezza (SetPreferredSize)
        // e applicato un bordo azzurro in modo di poter applicare anche
        // l'allineamento centrato del contenuto rispetto all'ampiezza della label
        label.setPreferredSize(new Dimension(130, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        pannelloCentro.add(label);
        
	  	// Pannello a sud
        JPanel pannelloSud = new JPanel();
        // Converte un colore con valore esadecimale in stringa RGB in decimale
	  	pannelloSud.setBackground(Color.decode("#66A0B8"));
	  	this.add(pannelloSud, BorderLayout.SOUTH);
	  	
	  	radioGroup = new ButtonGroup();
	 // radioButton1
	    radioButton1 = new JRadioButton("uno");
	   	radioGroup.add(radioButton1);
	    pannelloSud.add(radioButton1);
	    
	    // radioButton2
	    radioButton2 = new JRadioButton("due");
	   	radioGroup.add(radioButton2);
	    pannelloSud.add(radioButton2);
	    
	    // radioButton3
	    radioButton3 = new JRadioButton("tre");
	   	radioGroup.add(radioButton3);
	    pannelloSud.add(radioButton3);
	    
	  	// Pannello a est
        JPanel pannelloEst = new JPanel();
        // Converte un colore con valore esadecimale in stringa RGB in decimale
	  	pannelloEst.setBackground(Color.decode("#AA3311"));
	  	this.add(pannelloEst, BorderLayout.EAST);
	  	
	 // checkBox
	    checkBox = new JCheckBox("Seleziona");
	    pannelloEst.add(checkBox);
        
        // Caratteristiche finestra
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Componenti Swing. Prova a cliccare...");
		setSize(400, 200);
		setVisible(true); 
    }
	
	public void itemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	  	  String nome = (String)comboAnimali.getSelectedItem();
	  	  label.setText(nome);
	    } 
	} 

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboAnimali) {
		  JComboBox<String> cb = (JComboBox<String>) e.getSource();
		  cb.addItem("riga aggiunta");
		}
		
		if (e.getSource() == bottone) {
			// un po' di operatori ternari per valutare selezioni di ChekBox e RadioButton
			String s = "checkBox è: " + (checkBox.isSelected() ? "flaggato" : "non flaggato") + "\n";
			s += "RadioButton selezionato num " + (radioButton1.isSelected() ? "1": "") + (radioButton2.isSelected() ? "2": "") + (radioButton3.isSelected() ? "3": ""); 
			JOptionPane.showMessageDialog(getContentPane(), s + "\n\nChiudo e me ne vado....", "Informazione", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
  
	public static void main(String[] args) {
		new ControlliSwing(); 
	}
} 