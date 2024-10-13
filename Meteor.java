import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;
import java.awt.AlphaComposite;


public class Meteor {
	


	private int xTile;
	private int yTile;
	private int speed;
	private int x;
	private int y;
	private int shadowX;
	private int shadowY;
	private int distance;
	private int totalDistance;
	private int width;
	private int height;
	
	private BufferedImage meteorImage;
	
	private Color shadowColor = new Color(0, 0, 0, 190);
	
	public Meteor (int xTile, int yTile) {
		
		height = 32;
		width = 32;
		this.xTile = xTile;
		this.yTile = yTile;
		
		speed = 25;
		
		//takes 60*numSeconds frames to arrive, multiply that by speed because you want it to take that many frames, and going at that speed at that amount of frames it will have reached the ground!!
		totalDistance = 240*speed;
		distance = totalDistance;
		
		
		x = (xTile*32)-1280;
		y = (yTile*32)-608 - distance;

		shadowX = (xTile*32)-1280;
		shadowY = (yTile*32)-608;
		
		
		
		try {
			meteorImage = ImageIO.read(new File("images/particles/meteor.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
		
	}
	
	
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.drawImage(meteorImage, x+xDiff, y+yDiff+(totalDistance-distance)+30, null);
		g.setColor(shadowColor);
		double shadowSizeModifier = 1-((double)distance/(double)totalDistance);
		g.fillOval(shadowX+xDiff, shadowY+8+yDiff, (int)((double)32*shadowSizeModifier), (int)((double)16*shadowSizeModifier));
	}
	
	public void move() {
		distance -= speed;
	}
	
	public int getXTile() {
		return xTile;
	}
	
	public int getYTile() {
		return yTile;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public int getXDestination() {
		return x;
	}
	
	public int getYDestination() {
		return (yTile*32)-608;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}