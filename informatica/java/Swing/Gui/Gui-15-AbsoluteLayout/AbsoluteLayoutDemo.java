import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

public class AbsoluteLayoutDemo extends JFrame {
    private static final long serialVersionUID = 1L;

    public AbsoluteLayoutDemo() {
        setLayout(null);
        setTitle("Absolute Layout demo");

        JButton b1 = new JButton("uno");
        JButton b2 = new JButton("due");
        JLabel l1 = new JLabel("___tre___");

        add(b1);
        add(b2);
        add(l1);

        b1.setBounds(25, 5, 90, 50);
        b2.setBounds(155, 100, 70, 40);
        l1.setBounds(150, 35, 130, 35);
        l1.setBackground(new Color(00, 00, 255, 180));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AbsoluteLayoutDemo();
    }
}
