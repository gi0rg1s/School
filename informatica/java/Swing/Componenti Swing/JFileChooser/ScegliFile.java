// Notare che non c'e' bisogno di istanziare un JFrame
import javax.swing.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import java.io.File;

public class ScegliFile {
	public String nomeFile;
	
	public ScegliFile(JFrame parent) {
		String cwd = System.getProperty("user.dir");
		
		JFileChooser chooser = new JFileChooser(cwd);
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("file mp3", "mp3"));
		int returnVal = chooser.showOpenDialog(parent);
		if ( returnVal == JFileChooser.APPROVE_OPTION ) {
		  File f = chooser.getSelectedFile();
		  nomeFile = f.getName();
		}
	}
}

