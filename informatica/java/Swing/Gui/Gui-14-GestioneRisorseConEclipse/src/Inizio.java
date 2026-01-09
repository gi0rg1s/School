import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Per organizzare i file di risorse (immagini, file testo, mp3, ecc..) in cartelle eclipse diversa
 * da quella della radice di progetto (default) bisogna fare clickDx sul nome progetto, 
 * Properties/JavaBuildPath/AddFolder/NomeCartella e includere il percorso davanti ai file di risorsa
 * es. setIcon("risorse/bottone.png")
 */
@SuppressWarnings("serial")
public class Inizio extends JFrame {

	public static void main(String[] args) {
		new Inizio();
	}

	public Inizio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gestione immagini/risorse");
		setBounds(100, 100, 350, 350);
		
		JButton btnBottone = new JButton("Bottone");
		btnBottone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(btnBottone, BorderLayout.CENTER);
		/*
		 * Class.getResource() permette di specificare un path relativo al package della classe attuale, 
		 * mentre ClassLoader.getResource() è sempre "absolute" path.
		 * In questo modo è garantito il caricamento di una risorsa anche all'interno di un JAR
		 * perché questi metodi rilevano la posizione delle risorse relativamente a quella delle loro classi 
		 * all'interno dello stesso JAR.
		 * Infatti se carichiamo una risorsa semplicemente così: setIcon("prova.jpg") la virtual machine java
		 * cerca nel file system del sistema operativo nello stesso percorso del JAR e NON all'interno del JAR 
		 * 
		 * Esempio:
		 * foo.bar.Baz.class.getResource("data.txt")
		 * è equivalente a:
		 * some.Other.class.getResource("/foo/bar/data.txt")
		 * e sono equivalenti a:
		 * some.Other.class.getClassLoader().getResource("foo/bar/data.txt")
		 */

		btnBottone.setIcon(new ImageIcon(getClass().getClassLoader().getResource("immagini/bottone.png")));

		String testo = leggiFileTestoConInputStream("file/testo.txt");
		JOptionPane.showMessageDialog(this, testo);
		setVisible(true);
	}
	
	/**
	 * Legge con getResource e InputStream
	 * @param nomeFile
	 * @return
	 * @throws IOException
	 */
	public String leggiFileTestoConInputStream(String nomeFile) {
		try {
		  InputStream is = getClass().getResourceAsStream(nomeFile);
		  InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		  BufferedReader br = new BufferedReader(isr);
		  StringBuffer sb = new StringBuffer();
		  String line;
		  while ((line = br.readLine()) != null) {
		     sb.append(line + "\n");
		  }
		  br.close();
		  isr.close();
		  is.close();
		  return sb.toString();
		}
		catch (IOException e) {
		   e.printStackTrace();
		}
		return "";
	}
}
