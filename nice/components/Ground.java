package components;

import utility.Resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Ground {
  
  private class GroundImage {
    BufferedImage image;
    int x;
  }
  private class SkyImage {
  }
  
  public static int GROUND_Y;
  public static int SKY_Y;
  
  private BufferedImage image;
  
  private ArrayList<GroundImage> groundImageSet;
  private ArrayList<SkyImage> skyImageSet;
  
  public Ground(int panelHeight) {
    GROUND_Y = (int)(panelHeight - 0.1 * panelHeight); //ground height
    
    try{
      image = new Resource().getResourceImage("../images/Ground.png");
    } 
    catch(Exception e) {e.printStackTrace();} //"theoretically, nothing should go wrong."
    
    groundImageSet = new ArrayList<GroundImage>();
    
    //okay
    for(int i = 0; i < 3; i++) {
      GroundImage obj = new GroundImage();
      obj.image = image;
      obj.x = 0;
      groundImageSet.add(obj);
    }
  }
  public void Sun(int panelHeight) {
	  SKY_Y = (int)(panelHeight + 0.5 * panelHeight);
	  
	  try {
		  image = new Resource().getResourceImage("../images/Sun.png");
	  }
	  catch(Exception e) {e.printStackTrace();}
	  
	  skyImageSet = new ArrayList<SkyImage>();
	  for(int i = 0; i < 3; i++) {
		  SkyImage obj = new SkyImage();
		  skyImageSet.add(obj);
	  }
  }
  
  public void update() {
    Iterator<GroundImage> looper = groundImageSet.iterator();
    GroundImage first = looper.next();
    
    first.x -= 10; // makes the ground trippy. 
    
    int previousX = first.x;
    while(looper.hasNext()) {
      GroundImage next = looper.next();
      next.x = previousX + image.getWidth();
      previousX = next.x;
    }
    
    if(first.x < -image.getWidth()) {
      groundImageSet.remove(first);
      first.x = previousX + image.getWidth();
      groundImageSet.add(first);
    }
    
  }
  
  public void create(Graphics g) {
		for(GroundImage img : groundImageSet) {
			g.drawImage(img.image, (int) img.x, GROUND_Y, null);
		}
	}
}