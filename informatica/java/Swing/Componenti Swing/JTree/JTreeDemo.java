import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

// La classe JTreeDemo implementa i metodi di interfaccia per rispondere
// agli eventi scatenati con la navigazione dell'albero
public class JTreeDemo extends JFrame implements TreeSelectionListener,
												 TreeWillExpandListener,
												 TreeExpansionListener {
  
  private JTree albero;
      
  public JTreeDemo() {
  	// DefaultMutableTreeNode è l'unica classe che implementa per default 
  	// l'interfaccia TreeNode
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Radice");
    // Appende nuovi nodi a radice
    root.add(new DefaultMutableTreeNode("Primo"));
    root.add(new DefaultMutableTreeNode("Secondo"));
    root.add(new DefaultMutableTreeNode("Terzo"));
    
    albero = new JTree(root);
    albero.addTreeSelectionListener(this);
    albero.addTreeWillExpandListener(this);
    albero.addTreeExpansionListener(this);
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(new JScrollPane(albero), BorderLayout.CENTER);
    setVisible(true);
  }

  // Invocato ogni volta che si seleziona un nodo. Il metodo cattura il percorso 
  // attuale selezionato con la classe specializzata TreeSelectionEvent
  // Costruisce il percorso completo e ne aggiunge 3 sottoNodi nuovi
  public void valueChanged(TreeSelectionEvent evt) {
	TreePath path = evt.getPath();
    DefaultMutableTreeNode nodoSelezionato = (DefaultMutableTreeNode) (path.getLastPathComponent());
    String percorsoCompleto = path.getParentPath().toString() + path.getLastPathComponent().toString();
	JOptionPane.showMessageDialog(getContentPane(), "evento: valueChanged in " + percorsoCompleto);
	
	try {
  		nodoSelezionato.removeAllChildren();
    	for(int i = 0; i < 3; i++) 
      	  nodoSelezionato.add(new DefaultMutableTreeNode(Integer.toString(i)));
    }
	catch (Exception e) {}
  }	


// Invocato prima di expansion
  public void treeWillExpand(TreeExpansionEvent evt) throws ExpandVetoException {
  	JTree tree = (JTree)evt.getSource();
    
     // Acquisisce il Path da espandere
     TreePath path = evt.getPath();
     
     JOptionPane.showMessageDialog(getContentPane(), "evento: treeWillExpand");
    
    // Si puo annullare l'operazione
    boolean annullare = false;
    if (annullare)
      throw new ExpandVetoException(evt);
  
  }
    
   // Invocato prima di Collapse 
   public void treeWillCollapse(TreeExpansionEvent evt) throws ExpandVetoException {
     JTree tree = (JTree)evt.getSource();
    
     // Acquisisce il Path da espandere
     TreePath path = evt.getPath();
     
     JOptionPane.showMessageDialog(getContentPane(), "evento: treeWillCollapse");
    
    // Si puo annullare l'operazione
    boolean annullare = false;
    if (annullare)
      throw new ExpandVetoException(evt);
   }


  // Invocato Dopo expansion
   public void treeExpanded(TreeExpansionEvent evt) {
     JTree tree = (JTree)evt.getSource();
     TreePath path = evt.getPath();
     
     //tree.expandPath(path);
     JOptionPane.showMessageDialog(getContentPane(), "evento: treeExpanded");
   }
    
   
   // Invocato dopo Collapsed
   public void treeCollapsed(TreeExpansionEvent evt) {
     JTree tree = (JTree)evt.getSource();
    
     JOptionPane.showInternalMessageDialog(getContentPane(), "evento: treeCollapsed");
    
     TreePath path = evt.getPath();
   }
   
  public static void main(String[] args) {
    JTreeDemo albero = new JTreeDemo();
    albero.setSize(800, 600);
    albero.setVisible(true);
  }
}
