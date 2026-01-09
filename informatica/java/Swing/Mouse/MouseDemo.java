

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
 
/**
 * Gestione eventi mouse
 */
public class MouseDemo extends JFrame
      implements MouseListener, MouseMotionListener {
 
   // Posizione (x, y) del punto di click del mouse
   private JTextField tfMouseClickX;
   private JTextField tfMouseClickY;
   // Posizione (x, y) del puntatore del mouse
   private JTextField tfMousePositionX;
   private JTextField tfMousePositionY;
 
   public MouseDemo() {
      setLayout(new FlowLayout());
 
      add(new Label("X-Click: "));
      tfMouseClickX = new JTextField(10);
      tfMouseClickX.setEditable(false);
      add(tfMouseClickX);
      add(new Label("Y-Click: "));
      tfMouseClickY = new JTextField(10);
      tfMouseClickY.setEditable(false);
      add(tfMouseClickY);
 
      add(new Label("X-Position: "));
      tfMousePositionX = new JTextField(10);
      tfMousePositionX.setEditable(false);
      add(tfMousePositionX);
      add(new Label("Y-Position: "));
      tfMousePositionY = new JTextField(10);
      tfMousePositionY.setEditable(false);
      add(tfMousePositionY);
 
      addMouseListener(this);		//Gestione click ...
      addMouseMotionListener(this);	//Gestione movimento puntatore
 
      setTitle("Mouse Demo"); 
      setSize(400, 120);       
      setVisible(true);   
   }
 
   /** MouseListener handlers */
   @Override
   public void mouseClicked(MouseEvent evt) {
      tfMouseClickX.setText(evt.getX() + "");
      tfMouseClickY.setText(evt.getY() + "");
   }
 
   // Non gestiti altri eventi
   @Override public void mousePressed(MouseEvent evt) { }
   @Override public void mouseReleased(MouseEvent evt) { }
   @Override public void mouseEntered(MouseEvent evt) { }
   @Override public void mouseExited(MouseEvent evt) { }
 
   /** MouseMotionEvent handlers */
   @Override
   public void mouseMoved(MouseEvent evt) {
      tfMousePositionX.setText(evt.getX() + "");
      tfMousePositionY.setText(evt.getY() + "");
   }
 
   // Non gestito
   @Override public void mouseDragged(MouseEvent evt) { }
}