import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;

public class MapObject {
	
	int xTile, yTile, width, height, x, y;
	String type;
	
	public MapObject(int xTile, int yTile, int width, int height, String type) {
		this.xTile = xTile;
		this.yTile = yTile;
		this.width = width;
		this.height = height;
		this.type = type;
		
		x = -1280 + (xTile*32);
		y = -608 + (yTile*32);
	}
	
	
	public ArrayList<int[]> getProhibitedTiles() {
		ArrayList<int[]> prohibitedTiles = new ArrayList<int[]>();
		for(int r = yTile; r<yTile+height; r++) {
			for(int c = xTile; c<xTile+width; c++) {
				
				int[] coords = new int[]{r, c};
				prohibitedTiles.add(coords);
			}
		}
		
		return prohibitedTiles;
	}
	
	
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		
	}
	
	public int getXTile() {
		return xTile;
	}
	
	public int getYTile() {
		return yTile;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getType() {
		return type;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}