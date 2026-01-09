import java.awt.*;
    import java.awt.datatransfer.*;
    import java.awt.event.*;
    import javax.swing.*;

    public class CopiaIncolla {
        public static void main(String args[]) {

            JFrame frame = new JFrame("Clipboard demo");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            final JTextArea textArea = new JTextArea(10, 40);


            JPanel  buttonPanel = new JPanel();
            JButton copyButton  = new JButton("Copia");
            JButton pasteButton = new JButton("Incolla");
            JButton exitButton  = new JButton("Esci");
            
            copyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                    String s = textArea.getText();
                    StringSelection contents = new StringSelection(s);
                    cb.setContents(contents, null);
                    textArea.setText("");
                }
            });
            pasteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable content = cb.getContents(this);
                    try {
                        String s = (String)content.getTransferData(DataFlavor.stringFlavor);
                        textArea.setText(s);
                    }
                    catch (Throwable exc) {
                        System.err.println(e);
                    }
                }
            });
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            buttonPanel.add(copyButton);
            buttonPanel.add(pasteButton);
            buttonPanel.add(exitButton);

            panel.add("North", textArea);
            panel.add("South", buttonPanel);

            frame.getContentPane().add("Center", panel);
            frame.pack();
            frame.setVisible(true);
        }
    }