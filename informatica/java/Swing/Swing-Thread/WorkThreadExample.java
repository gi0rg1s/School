import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WorkThreadExample extends JFrame{

    JButton btnGo;
    JLabel output;

    public WorkThreadExample(){
        btnGo = new JButton("GO");
        btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                go();
            }
        });
        add(btnGo, BorderLayout.NORTH);

        output = new JLabel();
        add(output, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,200);
        setVisible(true);
    }

    /** called from button listener */
    private void go(){
        // start up a thread for our task and let the event queue get back to business
        Thread counter = new Thread(new CounterTask());
        counter.start();
        btnGo.setEnabled(false);
    }

    /** Small work task that we want to start from a UI event */
    class CounterTask implements Runnable{
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    final String countValue = String.valueOf(i);
                    // use this to put UI update tasks in the event queue
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            output.setText(countValue);
                            output.getParent().getGraphics().drawString("pippo", 100, 1000);
                        }
                    });
                    Thread.sleep(1000);
                }
                
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        output.setText("Done");
                        btnGo.setEnabled(true);
                    }
                });
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WorkThreadExample ex = new WorkThreadExample();
            }
        });
    }
}