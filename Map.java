import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;
import java.util.ArrayList;


public class Map {
	
	private BufferedImage mapImage;
	
	private BufferedImage inventoryImage;
	
	public Map(String map) {
		System.out.println("images/map/" + map + ".png");
		try {
			mapImage = ImageIO.read(new File("images/map/" + map + ".png"));
			inventoryImage = ImageIO.read(new File("images/UI/inventory.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
	}
	
	public void changeMap(String map) {
		try {
			mapImage = ImageIO.read(new File("images/map/" + map + ".png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");
		}
	}
	
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.drawImage(mapImage, -1280 + xDiff, -608 + yDiff, null); //1280 600
	}
	
	
}