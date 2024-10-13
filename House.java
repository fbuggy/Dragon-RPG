import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;

public class House extends MapObject {
	
	private BufferedImage houseImage;
	private int typeNumber;
	
	
	public House(int xTile, int yTile, int typeNumber) {
		super(xTile, yTile, 1, 1, "house");
		
		this.typeNumber = typeNumber;
		
		if(typeNumber==1) {
			setWidth(5);
			setHeight(8);
		} else if (typeNumber == 2) {
			setWidth(7);
			setHeight(8);
		} else if (typeNumber == 3) {
			setWidth(9);
			setHeight(3);
		}
		
		try {
			
			houseImage = ImageIO.read(new File("images/house/house" + typeNumber + ".png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");System.out.println(e + " in " + this + ".java");
		}
	}
	
	

	
	@Override
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		switch(typeNumber) {
			case 1:
				g.drawImage(houseImage, x+xDiff-32, y+yDiff-64, null);
				break;
			case 2:
				g.drawImage(houseImage, x+xDiff-32, y+yDiff-64, null);
				break;
			case 3:
				g.drawImage(houseImage, x+xDiff-32, y+yDiff-160, null);
				break;
		}
	}
	
	@Override
	public ArrayList<int[]> getProhibitedTiles() {
		ArrayList<int[]> prohibitedTiles = new ArrayList<int[]>();
		for(int r = yTile; r<yTile+height; r++) {
			for(int c = xTile; c<xTile+width; c++) {
				
				int[] coords = new int[]{r, c};
				prohibitedTiles.add(coords);
			}
		}
		
		if(typeNumber == 1) {
			int[] coords = new int[] {yTile+3, xTile+5};
			prohibitedTiles.add(coords);
			coords[0] = yTile+4;
			prohibitedTiles.add(coords);
			coords[0] = yTile+5;
			prohibitedTiles.add(coords);
		} else if (typeNumber == 2) {
			int[] coords = new int[] {yTile+4, xTile + 7};
			prohibitedTiles.add(coords);
			coords[0] = yTile+5;
			prohibitedTiles.add(coords);
			coords[0] = yTile+6;
			prohibitedTiles.add(coords);
		}
		
		
		return prohibitedTiles;
	}

}