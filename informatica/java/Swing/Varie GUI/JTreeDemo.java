import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import javax.swing.event.*;
    import javax.swing.tree.*;
    import java.util.Vector;

    public class JTreeDemo {
        public static void main(String args[]) {
            JFrame frame = new JFrame("JTree Demo");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            JPanel panel1 = new JPanel();

            // Crea radice e nodi

            DefaultMutableTreeNode radice = new DefaultMutableTreeNode("Radice");

            DefaultMutableTreeNode uno = new DefaultMutableTreeNode("uno");
            uno.add(new DefaultMutableTreeNode("1.1"));
            uno.add(new DefaultMutableTreeNode("1.2"));

            DefaultMutableTreeNode due = new DefaultMutableTreeNode("due");
            due.add(new DefaultMutableTreeNode("2.1"));
            due.add(new DefaultMutableTreeNode("2.2"));

            DefaultMutableTreeNode tre = new DefaultMutableTreeNode("tre");
            Vector v = new Vector();
            for (int i = 1; i <= 25; i++)
                v.addElement("3." + i);
            JTree.DynamicUtilTreeNode.createChildren(tre, v);

            radice.add(uno);
            radice.add(due);
            radice.add(tre);

            // setta albero e suo scroller
            // colore selezione = rosso

            JTree jt = new JTree(radice);
            DefaultTreeCellRenderer tcr = (DefaultTreeCellRenderer)jt.getCellRenderer();
            tcr.setTextSelectionColor(Color.red);

            JScrollPane jsp = new JScrollPane(jt);
            jsp.setPreferredSize(new Dimension(200, 300));

            // il text field riproduce la selezione

            JPanel panel2 = new JPanel();
            final JTextField tf = new JTextField(25);
            panel2.add(tf);

            TreeSelectionListener listen;
            listen = new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {

                    // rileva il path selezionato

                    TreePath path = e.getPath();
                    int cnt = path.getPathCount();
                    StringBuffer sb = new StringBuffer();

                    // estrai il path

                    for (int i = 0; i < cnt; i++) {
                        String s =  path.getPathComponent(i).toString();
                        sb.append(s);
                        if (i + 1 != cnt)
                            sb.append("#");
                    }
                    tf.setText(sb.toString());
                }
            };
            jt.addTreeSelectionListener(listen);

            panel1.add(jsp);

            frame.getContentPane().add("North", panel1);
            frame.getContentPane().add("South", panel2);
            frame.setLocation(100, 100);
            frame.pack();
            frame.setVisible(true);
        }
    }
