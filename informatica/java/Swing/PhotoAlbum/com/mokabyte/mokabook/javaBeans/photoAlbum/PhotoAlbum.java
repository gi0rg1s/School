package com.mokabyte.mokabook.javaBeans.photoAlbum;

import java.awt.*;
import java.beans.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class PhotoAlbum extends JPanel {

  private Vector comments = new Vector();
  private int imageIndex;

  public PhotoAlbum() {
    super();
    setLayout(new BorderLayout());
    setupComments();
    imageIndex=0;
    showNext();
  }
  private void setupComments() {
    try { 
      URL indexUrl = getClass().getResource("images/"+"comments.txt");
      InputStream in = indexUrl.openStream();
      BufferedReader lineReader = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = lineReader.readLine())!=null) 
        comments.add(line);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public int getImageNumber() {
    return comments.size();
  }
  public int getImageIndex() {
    return imageIndex;
  }
  public String getImageComment() {
    return (String)comments.elementAt(imageIndex);
  }
  public void showNext() {
    int oldImageIndex = imageIndex;
    imageIndex = ((imageIndex+1)%comments.size());
    String imageName = "img"+Integer.toString(imageIndex)+".jpg";
    showImage(getClass().getResource("images/"+imageName));
    String oldImageComment = (String)comments.elementAt(oldImageIndex);
    String currentImageComment = (String)comments.elementAt(imageIndex);
    firePropertyChange("imageComment",oldImageComment,currentImageComment);
    firePropertyChange("imageIndex",oldImageIndex,imageIndex);
  }
  private void showImage(URL imageUrl) {
    ImageIcon img = new ImageIcon(imageUrl);
    JLabel picture = new JLabel(img);
    JScrollPane pictureScrollPane = new JScrollPane(picture);
    removeAll();
    add(BorderLayout.CENTER,pictureScrollPane); 
    validate();    
  }
}