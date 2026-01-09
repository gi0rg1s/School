import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class RadioButtonDemo extends JFrame {
	private JPanel radioPanel;
	private ButtonGroup radioGroup;
	private JRadioButton radioButton1, radioButton2, radioButton3;
	
	public RadioButtonDemo() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Esempio RadioButton");
		radioPanel = new JPanel();
	    radioGroup = new ButtonGroup(  );
	    
	    // radioButton1
	    radioButton1 = new JRadioButton("uno");
	   	radioButton1.setActionCommand("uno");
	   	radioGroup.add(radioButton1);
	    radioPanel.add(radioButton1);
	    
	    // radioButton2
	    radioButton2 = new JRadioButton("due");
	   	radioButton2.setActionCommand("due");
	   	radioGroup.add(radioButton2);
	    radioPanel.add(radioButton2);
	    
	    // radioButton3
	    radioButton3 = new JRadioButton("tre");
	   	radioButton3.setActionCommand("tre");
	   	radioGroup.add(radioButton3);
	    radioPanel.add(radioButton3);
	    
	    add(radioPanel, "Center");
	}
	
	public static void main(String[] args) {
	    RadioButtonDemo f = new RadioButtonDemo();
	    f.setSize(300, 150);
	    f.setLocation(200, 200);
	    f.setVisible(true);
  }
} 
