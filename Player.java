import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;
import java.util.ArrayList;
import java.awt.AlphaComposite;

public class Player{
	
	
	private BufferedImage characterImage;
	private int animationCounter;
	private String animationFrame;
	private int x;
	private int y;
	private int width;
	private int height;
	private Color shadowColor = new Color(0, 0, 0, 100);
	
	
	public Player(int x, int y) {
		
		this.width = 64;
		this.height = 64;
		this.x = x;
		this.y = y;
		
		try {
			
			characterImage = ImageIO.read(new File("images/character/characterDown2.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
	}
    
	public void drawMe(Graphics g) {
		g.setColor(shadowColor);
		g.fillOval(x+7, y+52, 48, 16);
		g.drawImage(characterImage, x, y, null);
	}
	
	public boolean checkCollision(Item item, int xDiff, int yDiff) {
		
		int pX = x;
		int pY = y;
		int pWidth = width;
		int pHeight = height;
		
		int tX = item.getX()+xDiff+(item.getWidth()/2);
		int tY = item.getY()+yDiff+(item.getHeight()/2);
		int tWidth = item.getWidth();
		int tHeight = item.getHeight();
		
		
		if( pX+pWidth >= tX && pX <= tX + tWidth  &&  
			pY+pHeight >= tY && pY <= tY + tHeight )
		{
			return true;
			
		}
			
		
		
		
		return false;
		
	}
	
	public boolean checkCollision(Fireball fireball, int xDiff, int yDiff) {
		int pX = x;
		int pY = y;
		int pWidth = width;
		int pHeight = height;
		
		int tX = fireball.getX()+xDiff+(fireball.getWidth()/2);
		int tY = fireball.getY()+yDiff+(fireball.getHeight()/2);
		int tWidth = fireball.getWidth();
		int tHeight = fireball.getHeight();
		
		
		if( pX+pWidth >= tX && pX <= tX + tWidth  &&  
			pY+pHeight >= tY && pY <= tY + tHeight )
		{
			return true;
			
		}
			
		
		
		
		return false;
	}
	
	public boolean checkCollision(Meteor meteor, int xDiff, int yDiff) {
		int pX = x;
		int pY = y;
		int pWidth = width;
		int pHeight = height;
		
		int tX = meteor.getXDestination()+xDiff+(meteor.getWidth()/2);
		int tY = meteor.getYDestination()+yDiff+(meteor.getHeight()/2);
		int tWidth = meteor.getWidth();
		int tHeight = meteor.getHeight();
		
		
		if( pX+pWidth >= tX && pX <= tX + tWidth  &&  
			pY+pHeight >= tY && pY <= tY + tHeight )
		{
			return true;
			
		}
			
		
		
		
		return false;
	}
	
	
	public void startWalkAnimation(String direction) {
		int animationFactor = 11;
		animationCounter++;
		if(animationCounter>animationFactor*4-1) {
			animationCounter = 0;
		}
		
		animationFrame = "2";
		
		if(animationCounter>=0 && animationCounter<animationFactor) {
			animationFrame = "2";
		} else if (animationCounter>=animationFactor && animationCounter<animationFactor*2) {
			animationFrame = "3";
		} else if (animationCounter >=animationFactor*2 && animationCounter < animationFactor*3) {
			animationFrame = "2";
		} else if (animationCounter >=animationFactor*3 && animationCounter < animationFactor*4) {
			animationFrame = "1";
		}

		try {
			characterImage = ImageIO.read(new File("images/character/character" + direction+ animationFrame + ".png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}

		
	}
	
	public void stopWalkAnimation(String direction) {
		try {
			characterImage = ImageIO.read(new File("images/character/character" + direction+"2.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
	} 
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
}