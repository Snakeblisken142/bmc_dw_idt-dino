import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class UserInterface {
  JFrame mainWindow = new JFrame("April 23rd Test-Build");
  
  public static int WIDTH = 1000; //shift these values to adjust window size.
  public static int HEIGHT = 600; 
  
  public void createAndShowGUI() {
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Container container = mainWindow.getContentPane();
    
    GamePanel gamePanel = new GamePanel();
    gamePanel.addKeyListener(gamePanel);
    gamePanel.setFocusable(true);
    
    container.setLayout(new BorderLayout());
    container.add(gamePanel, BorderLayout.CENTER);
    
    mainWindow.setSize(WIDTH, HEIGHT);
    mainWindow.setResizable(true);
    mainWindow.setVisible(true);
  }
  
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new UserInterface().createAndShowGUI();
      }
    });
  }
}