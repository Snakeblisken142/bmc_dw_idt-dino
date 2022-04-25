import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import components.Ground;
import components.Dino;
import components.Obstacles;

class GamePanel extends JPanel implements KeyListener, Runnable {
  
  public static int WIDTH;
  public static int HEIGHT;
  private Thread animator;
  
  private boolean running = false;
  private boolean gameOver = false;
  
  Ground ground;
  Dino dino;
  Obstacles obstacles;

  private int score;
  private int highscore;
  private String HI = "HI";
 // private String GameOver = "Game Over. Hit ANY key to reset.";
  
  public GamePanel() {
    WIDTH = UserInterface.WIDTH;
    HEIGHT = UserInterface.HEIGHT;
    
    ground = new Ground(HEIGHT);
    dino = new Dino();
    obstacles = new Obstacles((int)(WIDTH * 1.5));

    score = 0;
    highscore = 0;
    
    setSize(WIDTH, HEIGHT);
    setVisible(true);
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    g.setFont(new Font("Comic Sans", Font.BOLD, 50));     //score
    g.drawString(Integer.toString(score), getWidth()/5 - 5, 100);
    g.drawString((HI), getWidth()/50 - 5, 100);
    g.drawString(Integer.toString(highscore), getWidth()/12 - 5, 100);
    ground.create(g);
    dino.create(g);
    obstacles.create(g);
  }
  
  public void run() {
    running = true;
    
    while(running) {
      updateGame();
      repaint();      //flickering
      try {
        Thread.sleep(50);
      } 
      catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void updateGame() {
    score += 1; //every thread-sleep tick give-or-take
    if (score > highscore) {
  	  highscore = score;
    }
    
    ground.update();
    // dino.update(); //dont ask. dont uncomment it either you'll crash your PC.
    obstacles.update();

    if(obstacles.hasCollided()) {       //THE PRINTS ARE ALL FOR CONSOLE DEBUGGING.
      dino.die();
      repaint();
      running = false;
      gameOver = true;
      System.out.println("ded");
      
      setFont(new Font("Comic Sans", Font.BOLD, 50));     //not score
      // drawString((GameOver), getWidth()/50 - 5, 100);
    }
  }

  public void reset() {
    score = 0;
    System.out.println("reset");
    obstacles.resume();
    gameOver = false;
  }
  
  public void keyTyped(KeyEvent e) { //again
    if(e.getKeyChar() == ' ') {    
      if(gameOver) reset();
      if (animator == null || !running) {
        System.out.println("mario-kart burnout");        
        animator = new Thread(this);
        animator.start();     
        dino.startRunning();   
      } 
      else {
        dino.jump();
      }
    }
  }

  public void keyPressed(KeyEvent e) {
	    // System.out.println("keyPressed: "+e);
	  }
  public void keyReleased(KeyEvent e) {
	    // System.out.println("keyReleased: "+e);
	  }
}