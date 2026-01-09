package com.mokabyte.mokabook.javaBeans.photoAlbum;

import com.mokabyte.mokabook.javaBeans.photoAlbum.*;
import java.beans.*;
import javax.swing.*;

public class PhotoAlbumTest {
public static void main(String argv[]) {
  JFrame f = new JFrame("Photo Album");
  PhotoAlbum p = new PhotoAlbum();
  f.getContentPane().add(p);
  p.addPropertyChangeListener(new PropertyChangeListener() {
    public void propertyChange(PropertyChangeEvent e) {
      System.out.println(e.getPropertyName()+": "+e.getNewValue());
    }
  });
  f.setSize(500,400);
  f.setVisible(true);

  while(true)
    for(int i=0;i<7;i++) {
      p.showNext();
      try {Thread.sleep(2000);}catch(Exception e) {}
    }
}
}