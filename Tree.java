import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;

public class Tree extends MapObject {
	
	private BufferedImage leavesImage;
	private BufferedImage trunkImage;
	
	public Tree(int xTile, int yTile) {
		super(xTile, yTile, 1, 1, "tree");
		
		
		try {
			
			trunkImage = ImageIO.read(new File("images/tree/trunk.png"));
			leavesImage = ImageIO.read(new File("images/tree/leaves.png"));
		} catch (IOException e) {
			System.out.println(e + " in " + this + ".java");System.out.println(e + " in " + this + ".java");
		}
	}
	
	

	
	@Override
	public void drawMe(Graphics g, int xDiff, int yDiff) {
		g.drawImage(trunkImage, x+xDiff-32, y+yDiff-32, null);
		g.drawImage(leavesImage, x+xDiff-32, y+yDiff-96, null);
	}
	


}