import java.io.*;

public class ListaRisorse {
    
  public static void visit(File f) {
    
  }
        
  public static void Lista(File f) {
    System.out.println(f);//visit(f);
    if (f.isDirectory()) {
      String list[] = f.list();
      for (int i = 0; i < list.length; i++)
        Lista(new File(f, list[i]));
    }
  }
        
  public static void main(String args[]) {
    File list[] = File.listRoots();
    for (int i = 0; i < list.length; i++) {
      if (list[i].exists())
        Lista(list[i]);
      else
        System.err.println("non accessibile: " + list[i]);
    }
  }
}
